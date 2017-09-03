package catcollab.repo;

import catcollab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Collection<Category> findByAccountUsername(String username);
    Optional<Category> findByName(String categoryName);

}