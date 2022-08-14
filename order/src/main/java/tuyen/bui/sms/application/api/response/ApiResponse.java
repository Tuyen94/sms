package tuyen.bui.sms.application.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import tuyen.bui.sms.domain.common.error.ErrorCode;

@Data
@NoArgsConstructor
public class ApiResponse<T> extends BaseResponse {

    private T data;

    public ApiResponse(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public ApiResponse(ErrorCode errorCode, T data) {
        super(errorCode);
        this.data = data;
    }
}
