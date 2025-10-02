package mate.academy.service;

import mate.academy.dto.cart.ShoppingCartResponseDto;
import mate.academy.dto.item.CartItemRequestDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto getByUserId(Long userId);

    ShoppingCartResponseDto addItem(Long userId,
                                    CartItemRequestDto requestDto);

    ShoppingCartResponseDto updateItemQuantity(Long userId,
                                               Long itemId,
                                               int quantity);

    void removeItem(Long userId, Long itemId);
}
