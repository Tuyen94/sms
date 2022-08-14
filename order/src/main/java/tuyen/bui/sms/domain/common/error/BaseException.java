package tuyen.bui.sms.domain.common.error;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException {

    private String code;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.message);
        this.code = errorCode.code;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }
}
