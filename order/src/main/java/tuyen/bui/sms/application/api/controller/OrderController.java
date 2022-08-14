package tuyen.bui.sms.application.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tuyen.bui.sms.application.api.response.ApiResponse;
import tuyen.bui.sms.domain.order.model.Order;
import tuyen.bui.sms.domain.order.repository.OrderRepository;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping(path = "/orders")
    public ApiResponse createOrder(@RequestBody Order order) {
        return ApiResponse.successResponse(orderRepository.save(order));
    }

    @GetMapping(path = "/orders/{id}")
    public ApiResponse getOrder(@PathVariable long id) {
        log.info("Get order " + id);
        return ApiResponse.successResponse(orderRepository.findById(id));
    }
}
