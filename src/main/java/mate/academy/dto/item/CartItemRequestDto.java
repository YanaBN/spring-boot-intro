package mate.academy.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CartItemRequestDto(@NotBlank @Positive Long bookId,
                                 @Positive int quantity) {
}
