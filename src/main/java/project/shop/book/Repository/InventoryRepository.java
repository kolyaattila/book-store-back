package project.shop.book.Repository;

import project.shop.book.Entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {
    List<InventoryEntity> findByBook_BookId(long id);
}
