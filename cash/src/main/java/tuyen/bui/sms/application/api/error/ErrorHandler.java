package tuyen.bui.sms.application.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tuyen.bui.sms.application.api.response.BaseResponse;
import tuyen.bui.sms.domain.common.error.BaseException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({BaseException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleBaseException(BaseException exception) {
        return new BaseResponse(exception.getCode(), exception.getMessage());
    }
}
