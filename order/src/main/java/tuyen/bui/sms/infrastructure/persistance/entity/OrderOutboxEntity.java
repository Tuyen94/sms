package tuyen.bui.sms.infrastructure.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuyen.bui.sms.common.Constant.*;
import tuyen.bui.sms.domain.order.model.Order;

import javax.persistence.*;

@Entity
@Table(name = "order_outbox")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderOutboxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long orderId;
    private String operation;
    private String status;

    public static OrderOutboxEntity from(Order order) {
        return OrderOutboxEntity.builder()
                .orderId(order.getOrderId())
                .operation(Operation.INSERT.toString())
                .status(OutboxStatus.NEW.toString())
                .build();
    }
}
