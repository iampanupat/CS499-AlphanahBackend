import com.alphanah.alphanahbackend.model.enumerate.ECognitoField;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {com.alphanah.alphanahbackend.service.AccountService.class})
public class test {

    @Test
    void bababaw() {
        System.err.println(ECognitoField.get("sub"));
    }


}
