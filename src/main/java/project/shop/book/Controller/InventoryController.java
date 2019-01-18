package project.shop.book.Controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.shop.book.Entity.BookEntity;
import project.shop.book.Entity.InventoryEntity;
import project.shop.book.Entity.SellEntity;
import project.shop.book.Repository.BookRepository;
import project.shop.book.Repository.InventoryRepository;
import project.shop.book.Repository.SellRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SellRepository sellRepository;

    @PostMapping("/addBook/{quantity}")
    List<InventoryEntity> addBook(@RequestBody @Valid BookEntity book,@PathVariable("quantity") int quantity){
        List<InventoryEntity> inventoryEntityList = new ArrayList<>();
        BookEntity bookEntity = this.bookRepository.findByBookId(book.getBookId());
        for(int i=0;i<quantity;i++){
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setSell(null);
            inventoryEntity.setBook(bookEntity);
            this.inventoryRepository.save(inventoryEntity);
            inventoryEntityList.add(inventoryEntity);
        }
        return inventoryEntityList;
    }

    @PostMapping("/disponible/{number}")
    boolean checkdisponibleBook(@RequestBody @Valid BookEntity book,@PathVariable("number") int numar){
        int NrDisponibile=0;
        BookEntity temp = this.bookRepository.findByBookId(book.getBookId());
        for (InventoryEntity inventoryEntity : temp.getInventoryEntityList()){
            if(inventoryEntity.getSell()==null){
                NrDisponibile++;
            }
        }
        if(NrDisponibile>=numar){
            return true;
        }
        else{
            return false;
        }
    }

    @PostMapping("/sell/{id}")
    synchronized InventoryEntity addSell(@RequestBody @Valid BookEntity book,@PathVariable("id") long id) {
        //System.out.println("out if"+id+"book id"+book.getBookId());
        List<InventoryEntity> inventoryEntityList = this.inventoryRepository.findAllByBook_BookId(book.getBookId());
        Optional<InventoryEntity> inventoryEntity = inventoryEntityList.stream()
                .filter(element -> element.getSell()==null)
                .findFirst();
        try{
            if(inventoryEntity.isPresent()){
                    inventoryEntity.get().setSell(this.sellRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Sell entity with id "+id)));
                    //System.out.println("in if"+inventoryEntity.get().getInventoryId());
                    return this.inventoryRepository.save(inventoryEntity.get());
            }
            else{
                throw new NotFoundException("Not found inventory Entity for book id "+book.getBookId());
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


}
