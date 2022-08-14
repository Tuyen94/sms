package tuyen.bui.sms.domain.common.error;

public enum ErrorCode {
    SUCCESS("0", "Success!");

    public final String code;
    public final String message;
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
