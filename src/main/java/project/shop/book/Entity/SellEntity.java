package project.shop.book.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Sell")
public class SellEntity {

    @Id
    @GeneratedValue
    @Column(name = "sell_id")
    private Long sellId;


    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;



    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "sell")
    private List<InventoryEntity> inventoryEntityList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<InventoryEntity> getInventoryEntityList() {
        return inventoryEntityList;
    }

    public void setInventoryEntityList(List<InventoryEntity> inventoryEntityList) {
        this.inventoryEntityList = inventoryEntityList;
    }

    public Long getSellId() {
        return sellId;
    }

    @Override
    public String toString() {
        return "SellEntity{" +
                "sellId=" + sellId +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
