package tuyen.bui.sms.infrastructure.persistance.entity;

import lombok.*;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.model.ProductItem;

import javax.persistence.*;

@Entity
@Table(name = "product_item")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemEntity {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderEntity orderEntity;

    private long productId;

    private int quantity;

    public static ProductItemEntity from(ProductItem productItem, OrderEntity orderEntity) {
        return ProductItemEntity.builder()
                .orderEntity(orderEntity)
                .productId(productItem.getProductId())
                .quantity(productItem.getQuantity())
                .build();
    }
}
