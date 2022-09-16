package tuyen.bui.sms.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.repository.OrderRepository;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
