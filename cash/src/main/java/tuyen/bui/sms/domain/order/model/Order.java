package tuyen.bui.sms.domain.order.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private long orderId;
    private long accountId;
    private OrderStatus status;
    private long amount;
    private LocalDateTime createdTime;
    private List<ProductItem> productItemList;
}
