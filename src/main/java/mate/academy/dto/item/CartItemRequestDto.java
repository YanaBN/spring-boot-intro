package mate.academy.dto.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequestDto(@NotNull @Positive Long bookId,
                                 @Positive int quantity) {
}
