package fi.hh.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.Bookstore.domain.CategoryRepository;
import fi.hh.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository crepository;
    
    @Test
    public void deleteBook() {
    	Category category = new Category("Auto-Biography");
    	crepository.save(category);
    	assertThat(category.getCategoryid()).isNotNull();
    	
        crepository.delete(category.getCategoryid());
        List<Category> categorys = crepository.findByName("Auto-Biography");
        assertThat(categorys).hasSize(0);
        //Janne's Method:
        assertThat(crepository.findByName("Auto-Biography")).isEmpty();
    }

}