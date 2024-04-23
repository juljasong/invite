package test.invite.exception;

public class NotExistsUserException extends CommonException {
    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public NotExistsUserException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return ErrorCode.ALREADY_EXISTS_EMAIL_EXCEPTION.getCode();
    }
}
