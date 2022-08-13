package tuyen.bui.sms.infrastructure.persistance.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProductItemEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
    private long productId;
    private int quantity;
}
