package mate.academy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public record BookSearchParameters(String[] author, BigDecimal[] price) {
}
