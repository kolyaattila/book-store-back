package project.shop.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.shop.book.Entity.SellEntity;
import project.shop.book.Repository.SellRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("sell")
@CrossOrigin("*")
public class SellController {
    @Autowired
    SellRepository sellRepository;

    @PostMapping
    public SellEntity sell(@Valid @RequestBody SellEntity sellEntity){
        return this.sellRepository.save(sellEntity);
    }

}
