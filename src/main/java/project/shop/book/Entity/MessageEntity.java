package project.shop.book.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Messages")
public class MessageEntity {

    @Id
    @SequenceGenerator(name = "seq",sequenceName = "seq",initialValue = 4)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator ="seq" )
    @Column(name = "message_id")
    private long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "phone")
    private String phone;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = true,name = "date")
    private String date;

    @Column(nullable = false,name = "message")
    private String message;

    @Column(nullable = false,name = "read")
    private boolean read;

    public MessageEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public long getId() {
        return id;
    }
}
