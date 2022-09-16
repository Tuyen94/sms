package tuyen.bui.sms.infrastructure.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.infrastructure.until.ModelMapperUntil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private long accountId;
    private String status;
    private long amount;
    private LocalDateTime createdTime;
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<ProductItemEntity> productItemEntityList;

    public Order toOrder() {
        return ModelMapperUntil.map(this, Order.class);
    }

    public static OrderEntity from(Order order) {
        OrderEntity orderEntity = ModelMapperUntil.map(order, OrderEntity.class);
        List<ProductItemEntity> productItemEntityList = order.getProductItemList().stream()
                .map(productItem -> ProductItemEntity.from(productItem, orderEntity)).toList();
        orderEntity.setProductItemEntityList(productItemEntityList);
        return orderEntity;
    }
}
