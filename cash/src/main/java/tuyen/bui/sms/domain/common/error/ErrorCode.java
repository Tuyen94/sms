package tuyen.bui.sms.domain.common.error;

public enum ErrorCode {
    SUCCESS("0", "Success!"),
    GET_ORDER_ERROR("100", "Get order error!"),
    CREATE_ORDER_ERROR("100", "Create order error!");

    public final String code;
    public final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
