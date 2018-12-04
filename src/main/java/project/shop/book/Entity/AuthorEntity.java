package project.shop.book.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Authors")
public class AuthorEntity {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private long authorId;

    @Column(nullable = false,name = "first_name")
    private String firstName;

    @Column(nullable = false,name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "autor_id") })
    @JsonIgnore
    private Set<BookEntity> bookEntities = new HashSet<BookEntity>();

    public AuthorEntity() {
    }

    public void setBookEntities(Set<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<BookEntity> getBookEntities() {
        return bookEntities;
    }

}
