package project.shop.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.shop.book.Entity.BookEntity;
import project.shop.book.Entity.MessageEntity;
import project.shop.book.Repository.MessageRepository;

import java.util.List;

@RestController
@RequestMapping("message")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("/messages")
    public List<MessageEntity> getAllMessages(){
       return this.messageRepository.findAll();
    }

    @PostMapping
    public MessageEntity newMessage(@RequestBody MessageEntity message){
        return this.messageRepository.save(message);
    }

    @GetMapping("/{id}")
    public MessageEntity getMessage(@PathVariable int id){
        return this.messageRepository.getById(id);
    }

    @PutMapping("/read")
    public MessageEntity setRead(@RequestBody int id){
        MessageEntity temp = this.messageRepository.getById(id);
        temp.setRead(true);
        return this.messageRepository.save(temp);
    }
}
