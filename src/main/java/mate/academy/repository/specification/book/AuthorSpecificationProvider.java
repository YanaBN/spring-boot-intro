package mate.academy.repository.specification.book;

import java.util.Arrays;
import mate.academy.model.Book;
import mate.academy.repository.specification.SpecificationKey;
import mate.academy.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return SpecificationKey.AUTHOR;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(SpecificationKey.AUTHOR)
                .in(Arrays.stream(params).toArray());
    }
}
