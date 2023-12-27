package pl.bookshop.bookservice.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.auth.util.annotation.UserAuthority;
import pl.bookshop.auth.util.utils.AuthenticationUtils;
import pl.bookshop.bookservice.dto.response.ShoppingCartActionResp;
import pl.bookshop.bookservice.dto.response.ShoppingCartTotalItemsCountResp;
import pl.bookshop.bookservice.service.ShoppingCartService;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @UserAuthority
    @PostMapping("/{bookId}")
    public ResponseEntity<ShoppingCartActionResp> addToShoppingCart(
            @PathVariable long bookId,
            @RequestHeader(value = "amount", defaultValue = "1") Integer amount) {

        return ResponseEntity.ok(
                shoppingCartService.addToShoppingCart(AuthenticationUtils.getUserId(), bookId, amount));
    }

    @UserAuthority
    @PostMapping("/submit")
    public ResponseEntity<ShoppingCartActionResp> submitOrder(
            @RequestHeader(value = "currency", defaultValue = "USD") String currency) {

        return ResponseEntity.ok(
                shoppingCartService.submitOrder(AuthenticationUtils.getUserId(), currency));
    }

    @UserAuthority
    @PostMapping("/increase/{bookId}")
    public ResponseEntity<ShoppingCartActionResp> increaseAmountOfGivenBook(@PathVariable long bookId) {

        return ResponseEntity.ok(
                shoppingCartService.increaseAmountOfGivenBook(AuthenticationUtils.getUserId(), bookId));
    }

    @UserAuthority
    @GetMapping
    public ResponseEntity<ShoppingCartTotalItemsCountResp> getShoppingCartItemsNumber() {

        return ResponseEntity.ok(
                shoppingCartService.countShoppingCartItems(AuthenticationUtils.getUserId()));
    }

    @UserAuthority
    @DeleteMapping("/{bookId}")
    public ResponseEntity<ShoppingCartActionResp> removeFromShoppingCart(@PathVariable long bookId) {

        return ResponseEntity.ok(
                shoppingCartService.removeFromShoppingCart(AuthenticationUtils.getUserId(), bookId));
    }

    @UserAuthority
    @DeleteMapping("/decrease/{bookId}")
    public ResponseEntity<ShoppingCartActionResp> decreaseAmountOfGivenBook(@PathVariable long bookId) {

        return ResponseEntity.ok(
                shoppingCartService.decreaseAmountOfGivenBook(AuthenticationUtils.getUserId(), bookId));
    }

}