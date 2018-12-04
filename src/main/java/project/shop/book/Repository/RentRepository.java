package project.shop.book.Repository;


import project.shop.book.Entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentEntity,Long> {
}
