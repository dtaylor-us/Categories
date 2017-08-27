package catcollab;

import catcollab.model.Account;
import catcollab.model.Category;
import catcollab.repo.AccountRepository;
import catcollab.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

@SpringBootApplication
public class CatcollabApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatcollabApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository,
                           CategoryRepository categoryRepository) {
        return (evt) -> Arrays.asList(
                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
                .forEach(
                        username -> {
                            Account account = accountRepository.save(new Account(username, "password"));
                            categoryRepository.save(new Category(account, "Category-" + randomString(), "A description"));
                            categoryRepository.save(new Category(account, "Category-" + randomString(), "A description"));
                        });
    }

    private String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
