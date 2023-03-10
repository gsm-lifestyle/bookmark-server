package lifestyle.bookmark.domain.member.facade;

import lifestyle.bookmark.domain.member.domain.Member;
import lifestyle.bookmark.domain.member.domain.repository.MemberRepository;
import lifestyle.bookmark.domain.member.exception.MemberNotFoundException;
import lifestyle.bookmark.domain.auth.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member getCurrentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));
    }

    public void checkPassword(Member member, String password) {
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
    }
    public Long getUserId() {
        return getCurrentMember().getMemberId();
    }

}
