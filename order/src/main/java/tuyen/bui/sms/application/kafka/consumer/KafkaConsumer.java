package tuyen.bui.sms.application.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tuyen.bui.sms.application.api.dto.OrderDto;
import tuyen.bui.sms.domain.order.repository.OrderRepository;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "${app.kafka.order-cash-topic}")
    public void receiveOrderCash(String message) {
        log.info("Receive order cash {}", message);
        try {
            OrderDto orderDto = objectMapper.readValue(message, OrderDto.class);
            orderRepository.save(orderDto.toOrder());
        } catch (JsonProcessingException e) {
            log.error("Can not parse order cash", e);
        }
    }

    @KafkaListener(topics = "${app.kafka.warehouses.order}")
    public void receiveOrderWarehouses(String message) {
        log.info("Receive order warehouses {}", message);
        try {
            OrderDto orderDto = objectMapper.readValue(message, OrderDto.class);
            orderRepository.save(orderDto.toOrder());
        } catch (JsonProcessingException e) {
            log.error("Can not parse order warehouses", e);
        }
    }

    @KafkaListener(topics = "${app.kafka.order-deliver-topic}")
    public void receiveOrderDeliver(String message) {
        log.info("Receive order deliver {}", message);
        try {
            OrderDto orderDto = objectMapper.readValue(message, OrderDto.class);
            orderRepository.save(orderDto.toOrder());
        } catch (JsonProcessingException e) {
            log.error("Can not parse order deliver", e);
        }
    }
}
