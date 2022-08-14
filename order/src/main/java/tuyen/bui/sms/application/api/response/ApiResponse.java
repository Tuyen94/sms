package tuyen.bui.sms.application.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import tuyen.bui.sms.domain.common.error.ErrorCode;

@Data
@NoArgsConstructor
public class ApiResponse extends BaseResponse {

    private Object data;

    public ApiResponse(String code, String message, Object data) {
        super(code, message);
        this.data = data;
    }

    public ApiResponse(ErrorCode errorCode, Object data) {
        super(errorCode);
        this.data = data;
    }

    public static ApiResponse successResponse(Object data) {
        return new ApiResponse(ErrorCode.SUCCESS, data);
    }
}
