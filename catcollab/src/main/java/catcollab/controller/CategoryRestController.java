package catcollab.controller;

import catcollab.model.Category;
import catcollab.model.Constants;
import catcollab.repo.AccountRepository;
import catcollab.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/{userName}/categories")
public class CategoryRestController {
    private final CategoryRepository categoryRepository;

    private final AccountRepository accountRepository;

    @Autowired
    CategoryRestController(CategoryRepository categoryRepository,
                           AccountRepository accountRepository) {
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Category> readCategories(@PathVariable String userName) {
        this.validateUser(userName);
        return this.categoryRepository.findByAccountUsername(userName);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userName, @RequestBody Category input) {
        this.validateUser(userName);

        return this.accountRepository
                .findByUsername(userName)
                .map(account -> {
                    Category result = categoryRepository.save(new Category(account, input.name, input.description));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
    Category readCategory(@PathVariable String userName, @PathVariable Long bookmarkId) {
        this.validateUser(userName);
        return this.categoryRepository.findOne(bookmarkId);
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
