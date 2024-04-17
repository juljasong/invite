package test.invite.exception;

public class ExpiredCodeException extends CommonException {
    private static final String MESSAGE = "유효하지 않은 코드 입니다.";

    public ExpiredCodeException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return ErrorCode.EXPIRED_CODE_EXCEPTION.getCode();
    }
}
