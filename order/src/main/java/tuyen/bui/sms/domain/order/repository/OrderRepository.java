package tuyen.bui.sms.domain.order.repository;

import tuyen.bui.sms.domain.order.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
