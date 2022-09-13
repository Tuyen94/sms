package tuyen.bui.sms.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.concurrent.ListenableFutureCallback;
import tuyen.bui.sms.application.api.dto.OrderDto;
import tuyen.bui.sms.application.kafka.producer.KafkaProducer;
import tuyen.bui.sms.application.properties.KafkaProperties;
import tuyen.bui.sms.domain.event.OrderApplicationEvent;
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
    private final KafkaProducer kafkaProducer;
    private final KafkaProperties kafkaProperties;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderDto createOrder(OrderDto orderDto) {
        try {
            Order order = orderDto.toOrder();
            if (order.getStatus() != OrderStatus.CREATED) {
                log.error("Status is not CREATED");
                throw OrderError.createOrderError();
            }
            order = orderRepository.save(order);
            OrderOutboxEntity orderOutbox = OrderOutboxEntity.from(order);
            outboxRepository.save(orderOutbox);
            OrderApplicationEvent event = new OrderApplicationEvent(orderDto, orderOutbox);
            applicationEventPublisher.publishEvent(event);
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

    @TransactionalEventListener
    public void sendOrderEvent(OrderApplicationEvent event) {
        log.info("sendOrderEvent {}", event);
        OrderDto order = (OrderDto) event.getSource();
        OrderOutboxEntity orderOutbox = event.getOrderOutbox();
        ListenableFutureCallback<Object> callback = new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                updateOrderOutboxStatus(orderOutbox, "E");
                log.error("Send orderOutbox error {}", orderOutbox, ex);
            }

            @Override
            public void onSuccess(Object result) {
                updateOrderOutboxStatus(orderOutbox, "S");
            }
        };
        kafkaProducer.sendMessage(kafkaProperties.getOrderTopic(), String.valueOf(order.getOrderId()), order, callback);
    }

    private void updateOrderOutboxStatus(OrderOutboxEntity orderOutbox, String status) {
        orderOutbox.setStatus(status);
        outboxRepository.save(orderOutbox);
    }
}
