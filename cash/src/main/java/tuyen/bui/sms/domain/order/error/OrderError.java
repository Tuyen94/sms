package tuyen.bui.sms.domain.order.error;

import tuyen.bui.sms.domain.common.error.ErrorCode;
import tuyen.bui.sms.domain.common.error.BaseException;

public class OrderError {

    public static BaseException getOrderError() {
        return new BaseException(ErrorCode.GET_ORDER_ERROR);
    }

    public static CreateOrderError createOrderError() {
        return new CreateOrderError();
    }

    public static class CreateOrderError extends BaseException {
        public CreateOrderError() {
            super(ErrorCode.CREATE_ORDER_ERROR);
        }
    }
}
