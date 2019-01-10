package project.shop.book.Controller;

import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.shop.book.Entity.SellEntity;
import project.shop.book.Repository.SellRepository;
import project.shop.book.Repository.UserRepository;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("sell")
@CrossOrigin("*")
public class SellController {
    @Autowired
    SellRepository sellRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public SellEntity sell(@PathVariable("id") long id){
        SellEntity sellEntity= new SellEntity();
        sellEntity.setDate(new Date());
        try {
            sellEntity.setUser(this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Unable to get user " + id)));
        }
        catch (NotFoundException e){
          return null;
        }
        return this.sellRepository.save(sellEntity);
    }

}
