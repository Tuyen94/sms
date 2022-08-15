package tuyen.bui.sms.application.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tuyen.bui.sms.application.api.dto.OrderDto;
import tuyen.bui.sms.application.api.response.ApiResponse;
import tuyen.bui.sms.common.Transaction;
import tuyen.bui.sms.application.service.OrderAppService;
import tuyen.bui.sms.domain.common.error.ErrorCode;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderAppService orderAppService;

    @PostMapping(path = "/orders")
    public ApiResponse<OrderDto> createOrder(@RequestBody OrderDto order) {
        return new ApiResponse<>(ErrorCode.SUCCESS, Transaction.execute(orderAppService::createOrder, order));
    }

    @GetMapping(path = "/orders/{id}")
    public ApiResponse<OrderDto> getOrder(@PathVariable long id) {
        log.info("Get order " + id);
        return new ApiResponse<>(ErrorCode.SUCCESS, orderAppService.getOrder(id));
    }
}
