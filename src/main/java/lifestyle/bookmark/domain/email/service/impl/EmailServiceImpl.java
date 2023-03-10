package lifestyle.bookmark.domain.email.service.impl;

import lifestyle.bookmark.domain.email.domain.EmailAuth;
import lifestyle.bookmark.domain.email.domain.repository.EmailAuthRepository;
import lifestyle.bookmark.domain.email.exception.AuthCodeExpiredException;
import lifestyle.bookmark.domain.email.exception.ManyRequestEmailAuthException;
import lifestyle.bookmark.domain.email.exception.MisMatchAuthCodeException;
import lifestyle.bookmark.domain.email.presentation.dto.SendEmailRequest;
import lifestyle.bookmark.domain.email.service.EmailService;
import lifestyle.bookmark.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Random;

@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender mailSender;

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmail(SendEmailRequest request) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888) + 1111);

        sendAuthEmail(request.getEmail(),authKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkEmail(String email, String authKey) {
        EmailAuth emailAuthEntity = emailAuthRepository.findById(email)
                .orElseThrow(()-> new MemberNotFoundException("유저를 찾을 수 없습니다."));
        checkAuthKey(emailAuthEntity,authKey);
        emailAuthEntity.updateAuthentication(true);
        emailAuthRepository.save(emailAuthEntity);
    }

    private void sendAuthEmail(String email, String authKey) {
        String subject = "bookmark 인증번호";
        String text = "인증을 위한 인증번호는 <strong>" + authKey + "<strong /> 입니다. <br />";
        EmailAuth emailAuthEntity = emailAuthRepository.findById(email)
                .orElse(EmailAuth.builder()
                        .authentication(false)
                        .randomValue(authKey)
                        .attemptCount(0)
                        .email(email)
                        .build());
        if (emailAuthEntity.getAttemptCount() >= 10) {
            throw new ManyRequestEmailAuthException("발송 횟수 초과");
        }

        emailAuthEntity.updateRandomValue(authKey);
        emailAuthEntity.increaseAttemptCount();

        emailAuthRepository.save(emailAuthEntity);
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AuthCodeExpiredException("메일 발송에 실패했습니다");
        }
    }
    private void checkAuthKey(EmailAuth emailAuthEntity, String authKey) {
        if(!Objects.equals(emailAuthEntity.getRandomValue(), authKey)){
            throw new MisMatchAuthCodeException("인증번호가 일치하지 않습니다.");
        }
    }

}
