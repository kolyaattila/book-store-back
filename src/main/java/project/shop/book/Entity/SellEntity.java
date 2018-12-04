package project.shop.book.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sell")
public class SellEntity {

    @Id
    @GeneratedValue
    private Long id;

}
