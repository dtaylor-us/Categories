package catcollab.controller;

import catcollab.model.Constants;
import catcollab.model.Item;
import catcollab.repo.AccountRepository;
import catcollab.repo.CategoryRepository;
import catcollab.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/{userName}/items")
public class ItemRestController {
    private final ItemRepository itemRepository;

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    ItemRestController(ItemRepository itemRepository,
                       AccountRepository accountRepository,
                       CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Item> readItemsByUser(@PathVariable String userName) {
        this.validateUser(userName);
        return this.itemRepository.findByAccountUsername(userName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/{categoryName}")
    Collection<Item> readItemsByCategory(@PathVariable String userName, @PathVariable String categoryName) {
        this.validateCategory(categoryName);
        return this.itemRepository.findByCategoryName(categoryName);
    }


    private void validateUser(String userName) {
        this.accountRepository.findByUsername(userName).orElseThrow(
                () -> new PropertyNotFoundException(userName, Constants.USER_TABLE));
    }

    private void validateCategory(String name) {
        this.categoryRepository.findByName(name).orElseThrow(
                () -> new PropertyNotFoundException(name, Constants.CATEGORY_TABLE));
    }

}
