package mate.academy.spring_boot_intro;

import mate.academy.spring_boot_intro.model.Book;
import mate.academy.spring_boot_intro.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

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


