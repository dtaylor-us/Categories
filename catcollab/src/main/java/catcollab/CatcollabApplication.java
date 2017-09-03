package catcollab;

import catcollab.model.Account;
import catcollab.model.Category;
import catcollab.model.Item;
import catcollab.repo.AccountRepository;
import catcollab.repo.CategoryRepository;
import catcollab.repo.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootApplication
public class CatcollabApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatcollabApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository,
                           CategoryRepository categoryRepository,
                           ItemRepository itemRepository) {
        return (evt) -> Arrays.asList(
                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
                .forEach(
                        username -> {
                            String[] suffixes = {randomString(), randomString()};
                            String s1 = Arrays.asList(suffixes).get(0);
                            String s2 = Arrays.asList(suffixes).get(1);

                            Account account = accountRepository.save(new Account(username, "password"));

                            Category cat1 = categoryRepository.save(new Category(account, "Category-" + s1, "A description for Category" + s1));
                            Category cat2 = categoryRepository.save(new Category(account, "Category-" + s2, "A description for Category" + s2));

                            Set<Item> itemSet = new HashSet<Item>();
                            for (int i = 0; i < 5; i++){
                                itemRepository.save(new Item(account, cat1, "Item-" + s1 + "-" + String.valueOf(i), "A description for item " + s1));
                                itemRepository.save(new Item(account, cat2, "Item-" + s2 + "-" + String.valueOf(i), "A description for item " + s2));
                            }

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
