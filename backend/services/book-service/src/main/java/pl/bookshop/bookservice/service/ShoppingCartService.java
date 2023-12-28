package pl.bookshop.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bookshop.bookservice.dto.response.*;
import pl.bookshop.bookservice.entity.*;
import pl.bookshop.bookservice.entity.composedKey.ShoppingCartId;
import pl.bookshop.bookservice.exception.AlreadyExistsException;
import pl.bookshop.bookservice.exception.NotFoundException;
import pl.bookshop.bookservice.exception.OrderSubmitFailException;
import pl.bookshop.bookservice.repository.facade.*;
import pl.bookshop.bookservice.utils.ShoppingCartActionConstants;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final BookRepository bookRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    private final CurrencyRepository currencyRepository;

    private final OrderRepository orderRepository;

    private final OrderLineRepository orderLineRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public ShoppingCartActionResp addToShoppingCart(
            final Long requestorId,
            final long bookId,
            final Integer amount) {

        ShoppingCartId shoppingCartId =
                ShoppingCartId.builder()
                        .userId(requestorId)
                        .bookId(bookId)
                        .build();

        if(!shoppingCartRepository.existsById(shoppingCartId)) {
            if (bookRepository.existsById(bookId)) {
                shoppingCartRepository.save(
                        ShoppingCartEntity.builder()
                                .id(shoppingCartId)
                                .amount(amount)
                                .build());

                return ShoppingCartActionResp.builder()
                        .action(ShoppingCartActionConstants.ADD_TO_SHOPPING_CART)
                        .message("Book added!")
                        .build();
            } else
                throw new NotFoundException(String.format("No book with bookId '%s' available", bookId));
        } else
            throw new AlreadyExistsException("The book is already present in shopping cart!");
    }

    @Transactional
    public ShoppingCartActionResp submitOrder(final Long requestorId, final String currency) {
        try {

            long currencyId = currencyRepository
                    .getCurrencyEntityByShortcut(currency).map(CurrencyEntity::getCurrencyId)
                    .orElseThrow(() -> new NotFoundException("No currency ID found for currency shortcut: " + currency));

            // Get all books from shopping cart
            List<OrderLineDto> orderLines =
                    shoppingCartRepository.getOrderLines(requestorId, currencyId);

            if(orderLines.isEmpty()) {
                return ShoppingCartActionResp.builder()
                        .action(ShoppingCartActionConstants.SHOPPING_CART_SUBMIT_ORDER)
                        .message("Shopping cart is empty")
                        .build();
            }
            // Create new order
            long orderId =
                orderRepository.save(OrderEntity.builder()
                        .userId(requestorId)
                        .shippingMethodId(rand(1, 8))   // temporary workaround (TODO -> change to real values)
                        .addressId(rand(1, 31))         // temporary workaround (TODO -> change to real values)
                        .build())
                    .getOrderId();

            // Create a corresponding order_line for each item in shopping cart
            orderLines.stream()
                    .map(orderLineDto -> OrderLineEntity.builder()
                            .orderId(orderId)
                            .bookId(orderLineDto.getBookId())
                            .amount(orderLineDto.getAmount())
                            .totalPrice(orderLineDto.getPrice().multiply(BigDecimal.valueOf(orderLineDto.getAmount())))
                            .currencyId(currencyId)
                            .build())
                    .forEach(orderLineRepository::save);

            // Add a record to order_history with status "Order Received"
            long statusId = orderStatusRepository
                    .getStatusIdByStatus("Order Received")
                    .orElseThrow(() -> new NotFoundException("Order status: 'Order Received' not found"));

            orderHistoryRepository.save(
                    OrderHistoryEntity.builder()
                            .orderId(orderId)
                            .statusId(statusId)
                            .build());

            // Empty user's shopping cart
            shoppingCartRepository.emptyShoppingCart(requestorId);

            return ShoppingCartActionResp.builder()
                    .action(ShoppingCartActionConstants.ADD_TO_SHOPPING_CART)
                    .message("Order submitted!")
                    .build();
        } catch (Exception e) {
            throw new OrderSubmitFailException("Failed to submit order", e);
        }
    }

    @Transactional
    public ShoppingCartActionResp increaseAmountOfGivenBook(final Long requestorId, final long bookId) {

        if(shoppingCartRepository.existsById(
                ShoppingCartId.builder()
                    .userId(requestorId)
                    .bookId(bookId)
                    .build())) {

            shoppingCartRepository.increaseAmountOfGivenBook(requestorId, bookId);

            return ShoppingCartActionResp.builder()
                    .action(ShoppingCartActionConstants.SHOPPING_CART_ITEM_INCREASE)
                    .message("Success")
                    .build();
        } else
            throw new AlreadyExistsException(String.format("No book with bookId '%s' in shopping cart", bookId));
    }

    public List<UserShoppingCartDto> getUserShoppingCart(final Long requestorId, String currency) {
        return shoppingCartRepository.getUserShoppingCart(requestorId, currency);
    }

    public ShoppingCartTotalItemsCountResp countShoppingCartItems(final Long requestorId) {

        return ShoppingCartTotalItemsCountResp.builder()
                .action(ShoppingCartActionConstants.COUNT_SHOPPING_CART_ITEMS)
                .result(shoppingCartRepository.countShoppingCartItems(requestorId))
                .build();
    }

    @Transactional
    public ShoppingCartActionResp removeFromShoppingCart(final Long requestorId, final long bookId) {

        ShoppingCartId shoppingCartId =
                ShoppingCartId.builder()
                        .userId(requestorId)
                        .bookId(bookId)
                        .build();

        if(shoppingCartRepository.existsById(shoppingCartId)) {
            shoppingCartRepository.deleteById(shoppingCartId);
            return ShoppingCartActionResp.builder()
                    .action(ShoppingCartActionConstants.REMOVE_FROM_SHOPPING_CART)
                    .message("Book Removed!")
                    .build();
        } else
            throw new NotFoundException(String.format("No book with bookId '%s' in shopping cart!", bookId));
    }

    @Transactional
    public ShoppingCartActionResp decreaseAmountOfGivenBook(final Long requestorId, final long bookId) {

        if(shoppingCartRepository.existsById(
                ShoppingCartId.builder()
                        .userId(requestorId)
                        .bookId(bookId)
                        .build())) {

            shoppingCartRepository.decreaseAmountOfGivenBook(requestorId, bookId);

            return ShoppingCartActionResp.builder()
                    .action(ShoppingCartActionConstants.SHOPPING_CART_ITEM_DECREASE)
                    .message("Success")
                    .build();
        } else
            throw new AlreadyExistsException(String.format("No book with bookId '%s' in shopping cart", bookId));
    }

    // TODO -> REMOVE (temporary workaround)
    private long rand(long min, long max) {
        return min + (long) (Math.random() * (max - min + 1));
    }

}
