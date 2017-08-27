package catcollab.repo;

import catcollab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Collection<Category> findByAccountUsername(String username);

}