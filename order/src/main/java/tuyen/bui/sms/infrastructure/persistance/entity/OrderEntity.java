package tuyen.bui.sms.infrastructure.persistance.entity;

import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.infrastructure.until.ModelMapperUntil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private long orderId;
    private long accountId;
    private String status;
    private long amount;
    private LocalDateTime createdTime;
    @OneToMany(mappedBy = "orderEntity")
    private List<ProductItemEntity> productItemEntityList;

    public Order toOrder() {
        return ModelMapperUntil.map(this, Order.class);
    }
}
