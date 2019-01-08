package project.shop.book.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Books")
public class BookEntity {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long bookId;

    @Column(nullable = false)
    private String name;

    @Column(name = "publish_date",nullable = false)
    //in the past
    private Date dataPublicari;

    @Column(unique = true,nullable = false)
    private String ISBN;


    @Column(name = "price",nullable = false)
    private double price;


    @Column(name = "pages",nullable = false)
    private int pages;

    @Column(name = "description",nullable = false,length = 1000)
    private String description;

    @Column(name = "photo",nullable = false)
    private String photo;

    @Column(name = "category",nullable = false)
    private String category;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "bookEntities")
    @JsonIgnore
    private Set<AuthorEntity> autorEntities = new HashSet<AuthorEntity>();


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "book")
    @JsonIgnore
    private List<InventoryEntity> inventoryEntityList;

    public BookEntity() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDataPublicari() {
        return dataPublicari;
    }

    public void setDataPublicari(Date dataPublicari) {
        this.dataPublicari = dataPublicari;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getBookId() {
        return bookId;
    }


    public Set<AuthorEntity> getAutorEntities() {
        return autorEntities;
    }

    public void setAutorEntities(Set<AuthorEntity> autorEntities) {
        this.autorEntities = autorEntities;
    }

    public List<InventoryEntity> getInventoryEntityList() {
        return inventoryEntityList;
    }

    public void setInventoryEntityList(List<InventoryEntity> inventoryEntityList) {
        this.inventoryEntityList = inventoryEntityList;
    }
}
