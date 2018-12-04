package project.shop.book.Controller;


import project.shop.book.Entity.UserEntity;
import project.shop.book.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    List<UserEntity> getUsers(){
        return userRepository.findAll();

    }

}
