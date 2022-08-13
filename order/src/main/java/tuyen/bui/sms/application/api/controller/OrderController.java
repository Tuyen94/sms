package tuyen.bui.sms.application.api.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import tuyen.bui.sms.domain.order.model.Order;

@RestController("/orders")
public class OrderController {

    @PutMapping()
    public Order createOrder(Order order) {
        return order;
    }
}
