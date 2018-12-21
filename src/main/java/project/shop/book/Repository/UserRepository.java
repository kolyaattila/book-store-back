package project.shop.book.Repository;

import project.shop.book.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    UserEntity findByFirstName(String firstName);
    UserEntity findByLastName(String lastName);
    Optional<UserEntity> findByEmail(String email);

}
