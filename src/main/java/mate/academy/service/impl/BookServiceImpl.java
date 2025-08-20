package mate.academy.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.dto.UpdateBookRequestDto;
import mate.academy.exeptions.EntityNotFoundException;
import mate.academy.mapper.BookMapper;
import mate.academy.model.Book;
import mate.academy.repository.BookRepository;
import mate.academy.repository.specification.BookSpecificationBuilder;
import mate.academy.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't delete book by id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, UpdateBookRequestDto dto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't update book by id: " + id)
        );
        bookMapper.updateBookFromDto(dto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Page<BookDto> search(BookSearchParametersDto bookSearchParametersDto,
                                Pageable pageable) {
        Specification<Book> bookSpecification =
                bookSpecificationBuilder.build(bookSearchParametersDto);
        return bookRepository.findAll(bookSpecification, pageable)
                .map(bookMapper::toDto);
    }
}
