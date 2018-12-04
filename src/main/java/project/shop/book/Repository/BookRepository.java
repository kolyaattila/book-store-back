package project.shop.book.Repository;


import org.springframework.data.jpa.repository.Query;
import project.shop.book.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
    @Override
    List<BookEntity> findAll();
    BookEntity findByBookId(Long bookId);
    @Query("select distinct category  from BookEntity ")
    List<String> getAllDistinctCategoriFromBook();
    List<BookEntity> findByCategory(String category);
    List<BookEntity> findAllByPriceBetween(double priceMin,double priceMax);
    List<BookEntity> getAllByPriceGreaterThan(double price);
}
