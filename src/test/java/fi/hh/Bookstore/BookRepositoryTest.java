package fi.hh.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.Bookstore.domain.Book;
import fi.hh.Bookstore.domain.BookRepository;
import fi.hh.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository brepository;

    @Test
    public void findByAuthor() {
        List<Book> books = brepository.findByAuthor("Author Authorson");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Book1");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("All About Me", "John Self", "984565", 2010, 50, new Category("Auto-Biography"));
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    
    @Test
    public void deleteBook() {
    	Book book = new Book("All About Me", "John Self", "984565", 2010, 50, new Category("Auto-Biography"));
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    	
        brepository.delete(book.getId());
        List<Book> books = brepository.findByAuthor("John Self");
        assertThat(books).hasSize(0);
    }

}