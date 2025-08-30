package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.cart.ShoppingCartResponseDto;
import mate.academy.dto.item.CartItemRequestDto;
import mate.academy.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Add an Item", description = "Add an item to the shopping cart")
    public ShoppingCartResponseDto addItem(
            @AuthenticationPrincipal(expression = "id") Long userId,
            @Valid @RequestBody CartItemRequestDto requestDto) {
        return shoppingCartService.addItem(userId, requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get a cart", description = "Get a cart by the user's id")
    public ShoppingCartResponseDto getCard(
            @AuthenticationPrincipal(expression = "id") Long userId) {
        return shoppingCartService.getByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/items/{cartItemId}")
    @Operation(summary = "Update Quantity", description = "Change the amount of items in the cart")
    public ShoppingCartResponseDto updateQuantity(
            @AuthenticationPrincipal(expression = "id") Long userId,
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        return shoppingCartService.updateItemQuantity(userId, cartItemId, quantity);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/item/{cartItemId}")
    @Operation(summary = "Delete an item", description = "Delete an item from user's cart")
    public void removeItem(
            @AuthenticationPrincipal(expression = "id") Long userId,
            @PathVariable Long cartItemId) {
        shoppingCartService.removeItem(userId, cartItemId);
    }
}
