package lifestyle.bookmark.domain.member.exception;

import lifestyle.bookmark.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.MEMBER_NOT_FOUND;
    }
}
