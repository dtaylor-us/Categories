package catcollab.controller;

import catcollab.model.Category;
import catcollab.repo.AccountRepository;
import catcollab.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/{userId}/categories")
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
    Collection<Category> readCategories(@PathVariable String userId) {
        this.validateUser(userId);
        return this.categoryRepository.findByAccountUsername(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Category input) {
        this.validateUser(userId);

        return this.accountRepository
                .findByUsername(userId)
                .map(account -> {
                    Category result = categoryRepository.save(new Category(account,
                            input.name, input.description));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
    Category readCategory(@PathVariable String userId, @PathVariable Long bookmarkId) {
        this.validateUser(userId);
        return this.categoryRepository.findOne(bookmarkId);
    }

    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}
