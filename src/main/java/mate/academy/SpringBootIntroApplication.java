package mate.academy;

import java.math.BigDecimal;
import mate.academy.model.Book;
import mate.academy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootIntroApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntroApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book onePiece = new Book();
            onePiece.setTitle("One Piece");
            onePiece.setAuthor("Oda");
            onePiece.setIsbn("8418061774");
            onePiece.setPrice(BigDecimal.valueOf(200));
            bookService.save(onePiece);
            System.out.println(bookService.getAll());
        };
    }
}
