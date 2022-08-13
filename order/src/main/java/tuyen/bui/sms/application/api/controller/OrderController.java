package tuyen.bui.sms.application.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.repository.OrderRepository;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping(path = "/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping(path = "/orders/{id}")
    public Order getOrder(@PathVariable long id) {
        log.info("Get order " + id);
        return orderRepository.findById(id);
    }
}
