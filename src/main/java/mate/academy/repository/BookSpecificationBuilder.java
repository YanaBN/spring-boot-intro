package mate.academy.repository;

import mate.academy.dto.BookSearchParameters;
import mate.academy.model.Book;
import mate.academy.repository.book.AuthorSpecification;
import mate.academy.repository.book.PriceSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.author() != null && searchParameters.author().length > 0) {
            spec = spec.and(new AuthorSpecification().getSpecification(searchParameters.author()));
        }
        if (searchParameters.price() != null && searchParameters.price().length > 0) {
            spec = spec.and(new PriceSpecification().getSpecification(searchParameters.price()));
        }
        return null;
    }
}
