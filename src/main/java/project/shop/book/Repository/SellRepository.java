package project.shop.book.Repository;



import project.shop.book.Entity.SellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends JpaRepository<SellEntity,Long> {
}
