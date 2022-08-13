package tuyen.bui.sms.infrastructure.persistance.repository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.repository.OrderRepository;
import tuyen.bui.sms.infrastructure.persistance.entity.OrderEntity;
import tuyen.bui.sms.infrastructure.persistance.repository.jpa.OrderRepositoryJpa;
import tuyen.bui.sms.infrastructure.until.ModelMapperUntil;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepositoryJpa;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = ModelMapperUntil.map(order, OrderEntity.class);
        return orderRepositoryJpa.save(orderEntity).toOrder();
    }
}
