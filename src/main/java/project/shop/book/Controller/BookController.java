package project.shop.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.shop.book.Entity.BookEntity;
import project.shop.book.Repository.BookRepository;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/books")
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    @RequestMapping("/categories")
    public  List<String> getCategory(){return this.bookRepository.getAllDistinctCategoriFromBook();}

    @RequestMapping("/{category}")
    public List<BookEntity> getSomeCategory(@PathVariable("category") String category ){
        return this.bookRepository.findByCategory(category);
    }

    @RequestMapping("/minPrice/{price}")
    public List<BookEntity> getBooksMinPrice(@PathVariable("price") double price){
        return this.bookRepository.getAllByPriceGreaterThan(price);
    }

    @RequestMapping("/PriceBetween/{minPrice}/{maxPrice}")
    public List<BookEntity> getBooksPriceBetween(@PathVariable("minPrice") double minPrice,@PathVariable("maxPrice") double maxPreice){
        return this.bookRepository.findAllByPriceBetween(minPrice, maxPreice);
    }

    @RequestMapping("/{minPrice}/{maxPrice}/{category}")
    public Set<BookEntity> getBooksPriceBetweenAndCategory(@PathVariable("minPrice") double minPrice,@PathVariable("maxPrice") double maxPreice,@PathVariable("category") String category){
        Set<BookEntity> temp2=new ManagedSet<>();
        this.bookRepository.findAllByPriceBetween(minPrice,maxPreice).forEach(bookEntity -> {
            if(bookEntity.getCategory().equals(category))
                temp2.add(bookEntity);
        });
        return temp2;
    }


}
