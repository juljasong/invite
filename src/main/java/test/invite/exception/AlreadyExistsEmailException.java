package test.invite.exception;

public class AlreadyExistsEmailException extends CommonException {
    private static final String MESSAGE = "이미 등록된 이메일 입니다.";

    public AlreadyExistsEmailException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return ErrorCode.ALREADY_EXISTS_EMAIL_EXCEPTION.getCode();
    }
}
