package project.shop.book.Entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name ="Rents")
public class RentEntity {

    @Id
    @GeneratedValue
    @Column(name = "rent_id")
    private long rentId;

    @NotNull
    @Column(name = "rent_date")
    private Date dataInchiriere;

    @NotNull
    @Column(name = "return_date")
    private Date dataReturnare;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private BookEntity bookEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private UserEntity userEntity;

    public RentEntity() {
    }

    public Date getDataInchiriere() {
        return dataInchiriere;
    }

    public void setDataInchiriere(Date dataInchiriere) {
        this.dataInchiriere = dataInchiriere;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public void setDataReturnare(Date dataReturnare) {
        this.dataReturnare = dataReturnare;
    }
}
