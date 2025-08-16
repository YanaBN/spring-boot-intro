package mate.academy.mapper;

import mate.academy.config.MapperConfig;
import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.dto.UpdateBookRequestDto;
import mate.academy.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(UpdateBookRequestDto bookRequestDto, @MappingTarget Book book);
}
