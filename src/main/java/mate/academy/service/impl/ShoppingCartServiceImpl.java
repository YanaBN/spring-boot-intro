package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.cart.ShoppingCartResponseDto;
import mate.academy.dto.item.CartItemRequestDto;
import mate.academy.exeptions.EntityNotFoundException;
import mate.academy.mapper.CartItemMapper;
import mate.academy.mapper.ShoppingCartMapper;
import mate.academy.model.CartItem;
import mate.academy.model.ShoppingCart;
import mate.academy.repository.CartItemRepository;
import mate.academy.repository.ShoppingCartRepository;
import mate.academy.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartResponseDto getByUserId(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user by id: "
                        + userId));
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    public ShoppingCartResponseDto addItem(Long userId, CartItemRequestDto requestDto) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart not found for user by id " + userId));

        Optional<CartItem> existingItemOpt = cart.getCartItems().stream()
                .filter(i -> i.getBook().getId().equals(requestDto.bookId()))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + requestDto.quantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = cartItemMapper.toEntity(requestDto);
            newItem.setShoppingCart(cart);
            cartItemRepository.save(newItem);
            cart.getCartItems().add(newItem);
        }
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    public ShoppingCartResponseDto updateItemQuantity(Long userId, Long itemId, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Cart not found for user by id " + userId));
        CartItem item = cartItemRepository.findByIdAndShoppingCartUserId(itemId, cart.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Cart item not found with id " + itemId
                                        + " for user with id " + userId));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    public void removeItem(Long userId, Long itemId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart not found for user by id " + userId));
        CartItem item = cartItemRepository.findByIdAndShoppingCartUserId(itemId, cart.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart item not found with id " + itemId + " for user with id " + userId));
        cart.getCartItems().remove(item);
        cartItemRepository.delete(item);
    }
}
