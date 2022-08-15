package tuyen.bui.sms.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tuyen.bui.sms.application.api.dto.OrderDto;
import tuyen.bui.sms.domain.order.error.OrderError;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.model.OrderStatus;
import tuyen.bui.sms.domain.order.repository.OrderRepository;
import tuyen.bui.sms.infrastructure.persistance.entity.OrderOutboxEntity;
import tuyen.bui.sms.infrastructure.persistance.repository.jpa.OrderOutboxRepositoryJpa;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderAppService {

    private final OrderRepository orderRepository;
    private final OrderOutboxRepositoryJpa outboxRepository;

    public OrderDto createOrder(OrderDto orderDto) {
        try {
            Order order = orderDto.toOrder();
            if (order.getStatus() != OrderStatus.CREATED) {
                log.error("Status is not CREATED");
                throw OrderError.createOrderError();
            }
            order = orderRepository.save(order);
            outboxRepository.save(OrderOutboxEntity.from(order));
            return OrderDto.from(order);
        } catch (Exception e) {
            log.error("Create order error!", e);
            throw OrderError.createOrderError();
        }
    }

    public OrderDto getOrder(long id) {
        try {
            return OrderDto.from(orderRepository.findById(id));
        } catch (Exception e) {
            throw OrderError.getOrderError();
        }
    }
}
