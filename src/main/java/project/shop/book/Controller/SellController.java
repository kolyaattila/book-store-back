package project.shop.book.Controller;

import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.shop.book.Entity.SellEntity;
import project.shop.book.Repository.SellRepository;
import project.shop.book.Repository.UserRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("sell")
@CrossOrigin("*")
public class SellController {
    @Autowired
    SellRepository sellRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public SellEntity getSellEntity(@PathVariable("id") long id){
        try {
            return this.sellRepository.findById(id).orElseThrow(() -> new NotFoundException("Unable to get SelEntity " + id));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sales")
    public List<SellEntity> getSales(){
        return this.sellRepository.findAll();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/acceptCommand/{sellId}")
    public SellEntity acceptCommand(@PathVariable("sellId") long sellId) {
        SellEntity sellEntity;
        try {
            sellEntity = this.sellRepository.findById(sellId).orElseThrow(() -> new NotFoundException("Not found SellEntity with id : " + sellId));
        }
        catch(NotFoundException e){
            System.out.println(e);
            return null;
        }
        //sent email
        sellEntity.setSellAccepted(true);
        this.sellRepository.save(sellEntity);
        return sellEntity;
    }

}
