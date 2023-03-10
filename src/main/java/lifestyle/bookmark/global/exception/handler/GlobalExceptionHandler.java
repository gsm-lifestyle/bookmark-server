package lifestyle.bookmark.global.exception.handler;

import lifestyle.bookmark.domain.auth.exception.EmailExistsException;
import lifestyle.bookmark.domain.auth.exception.LoginIdExistsException;
import lifestyle.bookmark.domain.auth.exception.RefreshTokenNotFoundException;
import lifestyle.bookmark.domain.book.exception.AlreadyExistsBookException;
import lifestyle.bookmark.domain.book.exception.NotFoundBookException;
import lifestyle.bookmark.domain.book.exception.UnregisterBookException;
import lifestyle.bookmark.domain.email.exception.AuthCodeExpiredException;
import lifestyle.bookmark.domain.email.exception.ManyRequestEmailAuthException;
import lifestyle.bookmark.domain.email.exception.MisMatchAuthCodeException;
import lifestyle.bookmark.domain.email.exception.NotVerifyEmailException;
import lifestyle.bookmark.domain.member.exception.MemberNotFoundException;
import lifestyle.bookmark.domain.auth.exception.PasswordMismatchException;
import lifestyle.bookmark.domain.note.exception.NotFoundNoteException;
import lifestyle.bookmark.global.exception.ErrorMessage;
import lifestyle.bookmark.global.security.exception.TokenExpirationException;
import lifestyle.bookmark.global.security.exception.TokenNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleMemberNotFoundException(HttpServletRequest request , MemberNotFoundException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorMessage> handlePasswordMismatchException(HttpServletRequest request , PasswordMismatchException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(LoginIdExistsException.class)
    public ResponseEntity<ErrorMessage> handleLoginIdExistsException(HttpServletRequest request , LoginIdExistsException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ErrorMessage> handleEmailExistsException(HttpServletRequest request , EmailExistsException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenExpirationException.class)
    public ResponseEntity<ErrorMessage> handleTokenExpirationException(HttpServletRequest request , TokenExpirationException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorMessage> handleTokenNotValidException(HttpServletRequest request , TokenNotValidException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AuthCodeExpiredException.class)
    public ResponseEntity<ErrorMessage> handleAuthCodeExpiredException(HttpServletRequest request , AuthCodeExpiredException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyRequestEmailAuthException.class)
    public ResponseEntity<ErrorMessage> handleManyRequestEmailAuthException(HttpServletRequest request , ManyRequestEmailAuthException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MisMatchAuthCodeException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchAuthCodeException(HttpServletRequest request , MisMatchAuthCodeException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotVerifyEmailException.class)
    public ResponseEntity<ErrorMessage> handleNotVerifyEmailException(HttpServletRequest request , NotVerifyEmailException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRefreshTokenNotFoundException(HttpServletRequest request , RefreshTokenNotFoundException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AlreadyExistsBookException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExistsBookException(HttpServletRequest request , AlreadyExistsBookException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotFoundBookException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundBookException(HttpServletRequest request , NotFoundBookException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(UnregisterBookException.class)
    public ResponseEntity<ErrorMessage> handleUnregisterBookException(HttpServletRequest request , UnregisterBookException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotFoundNoteException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundNoteException(HttpServletRequest request , NotFoundNoteException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorResponse = new ErrorMessage(e.getErrorCode().getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
