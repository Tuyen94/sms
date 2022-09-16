package tuyen.bui.sms.application.api.dto;

import lombok.Data;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.model.ProductItem;
import tuyen.bui.sms.infrastructure.until.ModelMapperUntil;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private long orderId;
    private long accountId;
    private String status;
    private long amount;
    private List<ProductItem> productItemList;

    public static OrderDto from(Order order) {
        return ModelMapperUntil.map(order, OrderDto.class);
    }

    public Order toOrder() {
        Order order = ModelMapperUntil.map(this, Order.class);
        order.setCreatedTime(LocalDateTime.now());
        return order;
    }
}
