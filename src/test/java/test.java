import com.alphanah.alphanahbackend.entity.Category;
import com.alphanah.alphanahbackend.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {com.alphanah.alphanahbackend.service.AccountService.class})
public class test {

    @Autowired
    CategoryRepository repository;

    @Test
    void bababaw() {
        Category category = new Category();
//        category.setName("123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345");
        repository.save(category);

    }


}
