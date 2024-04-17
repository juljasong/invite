package test.invite.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    EXPIRED_CODE_EXCEPTION(400),
    ALREADY_EXISTS_EMAIL_EXCEPTION(400),
    INTERNAL_SERVER_ERROR(500),
    TOO_BIG_ID_ERROR(507),
    TOO_SMALL_ID_ERROR(507);

    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

}
