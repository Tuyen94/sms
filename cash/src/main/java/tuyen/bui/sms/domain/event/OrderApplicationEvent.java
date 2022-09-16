package tuyen.bui.sms.domain.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import tuyen.bui.sms.application.api.dto.OrderDto;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.infrastructure.persistance.entity.OrderOutboxEntity;

@Data
public class OrderApplicationEvent extends ApplicationEvent {

    private OrderOutboxEntity orderOutbox;
    public OrderApplicationEvent(OrderDto order, OrderOutboxEntity orderOutbox) {
        super(order);
        this.orderOutbox = orderOutbox;
    }
}
