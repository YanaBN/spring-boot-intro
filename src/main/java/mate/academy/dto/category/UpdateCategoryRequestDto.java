package mate.academy.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequestDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    private String description;
}
