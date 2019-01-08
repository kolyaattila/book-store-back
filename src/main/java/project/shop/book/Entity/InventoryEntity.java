package project.shop.book.Entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private long inventoryId;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private BookEntity book;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private SellEntity sell;

    public long getInventoryId() {
        return inventoryId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public SellEntity getSell() {
        return sell;
    }

    public void setSell(SellEntity sell) {
        this.sell = sell;
    }
}
