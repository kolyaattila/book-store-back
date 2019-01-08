package project.shop.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.shop.book.Entity.BookEntity;
import project.shop.book.Entity.InventoryEntity;
import project.shop.book.Repository.BookRepository;
import project.shop.book.Repository.InventoryRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/disponible/{number}")
    boolean checkdisponibleBook(@RequestBody @Valid BookEntity book,@PathVariable("number") int numar){
        int NrDisponibile=0;
        BookEntity temp = this.bookRepository.findByBookId(book.getBookId());
        for (InventoryEntity inventoryEntity : temp.getInventoryEntityList()){
            if(inventoryEntity.getSell()==null){
                NrDisponibile++;
            }
        }
        if(NrDisponibile==numar){
            return true;
        }
        else{
            return false;
        }
    }



}
