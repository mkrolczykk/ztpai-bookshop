package pl.bookshop.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bookshop.bookservice.dto.request.AssignEmployeeToOrderReq;
import pl.bookshop.bookservice.repository.facade.OrderRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void assignEmployeeToOrder(AssignEmployeeToOrderReq request) {
        orderRepository.assignEmployeeToOrder(request.getOrderId(), request.getEmployeeId());
    }

    @Transactional
    public void updateOrderStatusToPendingDelivery(final Long orderId) {
        orderRepository.updateOrderStatusToPendingDelivery(orderId);
    }

}
