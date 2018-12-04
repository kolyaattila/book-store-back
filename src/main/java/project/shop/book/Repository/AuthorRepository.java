package project.shop.book.Repository;

import project.shop.book.Entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {
    AuthorEntity findByLastName(String lastName);
    AuthorEntity findByAuthorId(Long authorId);


}
