package fi.hh.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.Bookstore.domain.User;
import fi.hh.Bookstore.domain.UserRepository;
import fi.hh.Bookstore.domain.Book;
import fi.hh.Bookstore.domain.BookRepository;
import fi.hh.Bookstore.domain.Category;
import fi.hh.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("saving a couple books");
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Non-Fiction"));
			crepository.save(new Category("Biography"));
			
			brepository.save(new Book("Book1", "Author Authorson", "219870", 1990, 20, crepository.findByName("Fiction").get(0)));
			brepository.save(new Book("Book2", "Writer McWriterguy", "561894", 1918, 15, crepository.findByName("Non-Fiction").get(0)));
			
			// Create users: admin/password user/password
			User user1 = new User("user", "$2a$06$/nLxth1w.1Hk9gn2O3hcYuA2LFOz/HvBT/C3HKQwTft.bzoQvgy9y", "user@user.com,", "USER");
			User user2 = new User("admin", "$2a$06$/nLxth1w.1Hk9gn2O3hcYuA2LFOz/HvBT/C3HKQwTft.bzoQvgy9y", "admin@admin.com,", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			
		};
	}
}
