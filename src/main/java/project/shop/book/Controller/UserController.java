package project.shop.book.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import project.shop.book.Entity.LoginDto;
import project.shop.book.Entity.TokenEntity;
import project.shop.book.Entity.UserEntity;
import project.shop.book.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public TokenEntity login( @RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        TokenEntity tokenEntity = new TokenEntity();
         tokenEntity.setToken(userService.signin(loginDto.getEmail(), loginDto.getPassword()).orElseThrow(()->
                new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed")));
         return tokenEntity;
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity signup(@RequestBody @Valid UserEntity userEntity){
        return userService.signup(userEntity).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }

}
