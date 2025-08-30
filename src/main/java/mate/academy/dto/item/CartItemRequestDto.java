package mate.academy.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CartItemRequestDto(@NotBlank Long bookId,
                                 @Min(1) int quantity) {
}
