package pl.bookshop.bookservice.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.auth.util.annotation.AdminAuthority;
import pl.bookshop.auth.util.annotation.EmployeeAuthority;
import pl.bookshop.bookservice.dto.request.AssignEmployeeToOrderReq;
import pl.bookshop.bookservice.dto.response.BookOrderDto;
import pl.bookshop.bookservice.service.OrderService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @AdminAuthority
    @PostMapping("/order/assign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignEmployeeToOrder(@RequestBody @Valid AssignEmployeeToOrderReq request) {

        orderService.assignEmployeeToOrder(request);
    }

    @EmployeeAuthority
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<BookOrderDto>> getOrders() {

        return ResponseEntity.ok(orderService.getOrders());
    }

    @EmployeeAuthority
    @PutMapping("/order/{orderId}/status/pending")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderStatusToPendingDelivery(@PathVariable long orderId) {

        orderService.updateOrderStatusToPendingDelivery(orderId);
    }

}
