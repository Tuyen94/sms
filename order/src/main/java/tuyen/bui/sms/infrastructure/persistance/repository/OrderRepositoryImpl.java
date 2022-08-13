package tuyen.bui.sms.infrastructure.persistance.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.repository.OrderRepository;
import tuyen.bui.sms.infrastructure.persistance.entity.OrderEntity;
import tuyen.bui.sms.infrastructure.persistance.repository.jpa.OrderRepositoryJpa;
import tuyen.bui.sms.infrastructure.until.ModelMapperUntil;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepositoryJpa;

    @Override
    public Order save(Order order) {
        log.info("save {}", order);
        OrderEntity orderEntity = OrderEntity.from(order);
        log.info("save orderEntity {}", orderEntity);
        return orderRepositoryJpa.save(orderEntity).toOrder();
    }

    @Override
    public Order findById(long id) {
        log.info(String.valueOf(orderRepositoryJpa.findById(id).orElse(null)));
        return orderRepositoryJpa.findById(id).map(OrderEntity::toOrder).orElse(null);
    }
}
