package project.shop.book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.shop.book.Entity.BookEntity;
import project.shop.book.Entity.MessageEntity;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<MessageEntity,Long> {
    MessageEntity getById(long id);
}
