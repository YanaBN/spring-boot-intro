package mate.academy.spring_boot_intro.service;

import mate.academy.spring_boot_intro.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    List<Book> getAll();
}
