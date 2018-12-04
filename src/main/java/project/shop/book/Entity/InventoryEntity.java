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
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private BookEntity bookEntity;

    @NotNull
    private boolean inchiriata;
}
