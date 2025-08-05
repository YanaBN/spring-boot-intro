package mate.academy.spring_boot_intro.repository;

import mate.academy.spring_boot_intro.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
