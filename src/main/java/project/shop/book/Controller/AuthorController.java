package project.shop.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.shop.book.Entity.AuthorEntity;
import project.shop.book.Entity.BookEntity;
import project.shop.book.Repository.AuthorRepository;
import project.shop.book.Repository.BookRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/author")
@CrossOrigin("*")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/{id}/books")
    public Set<BookEntity> getBooks(@PathVariable("id") long id){
        return this.authorRepository.findByAuthorId(id).getBookEntities();
    }

    @RequestMapping("/{id}/books/{category}")
    public Set<BookEntity> getBooksByCategory(@PathVariable("id") long id,@PathVariable("category") String category){
        Set<BookEntity> temp2=new ManagedSet<>();
        Set<BookEntity> temp = this.authorRepository.findByAuthorId(id).getBookEntities();
        temp.forEach(bookEntity ->{ if(bookEntity.getCategory().equals(category)){temp2.add(bookEntity);}});
        return temp2;
    }

    @RequestMapping("/{id}/books/{minPrice}/{maxPrice}")
    public Set<BookEntity> getBooksByPrice(@PathVariable("id") long id,@PathVariable("minPrice") int minPrice,@PathVariable("maxPrice") int maxPrice){
        Set<BookEntity> temp2=new ManagedSet<>();
        Set<BookEntity> temp = this.authorRepository.findByAuthorId(id).getBookEntities();
        temp.forEach(bookEntity ->{
            if(bookEntity.getPrice()>=minPrice && bookEntity.getPrice()<=maxPrice)
                temp2.add(bookEntity);
        });
        return temp2;
    }

    @RequestMapping("/{id}/books/{minPrice}/{maxPrice}/{category}")
    public Set<BookEntity> getBooksByPriceAndCategory(@PathVariable("id") long id,@PathVariable("minPrice") int minPrice,@PathVariable("maxPrice") int maxPrice,@PathVariable("category") String category){
        Set<BookEntity> temp2=new ManagedSet<>();
        this.authorRepository.findByAuthorId(id).getBookEntities().forEach(bookEntity ->{
            if(bookEntity.getPrice()>=minPrice && bookEntity.getPrice()<maxPrice && bookEntity.getCategory().equals(category))
                temp2.add(bookEntity);
        });
        return temp2;
    }


    @RequestMapping("/authors")
    public List<AuthorEntity> getAuthors(){
        return this.authorRepository.findAll();
    }

}
