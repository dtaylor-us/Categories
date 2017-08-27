package catcollab.repo;

import catcollab.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Collection<Item> findByCategoryName(String categoryName);
    Collection<Item> findByAccountUsername(String username);
}