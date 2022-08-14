package tuyen.bui.sms.application.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuyen.bui.sms.domain.common.error.ErrorCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    protected String code;
    protected String message;
    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.code;
        this.message = errorCode.message;
    }
}
