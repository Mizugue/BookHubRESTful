package lib.app.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 300, nullable = false)
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    @Column(name = "authors")
    private Set<Author> authors =new HashSet<Author>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @Column(name = "categories")
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_publishers",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    @Column(name = "publishers")
    private Set<Publisher> publishers = new HashSet<Publisher>();

    public void removePublisher(Publisher publisher){
        this.publishers.remove(publisher);
        publisher.getBooks().remove(this);
    }

    public void addPublisher(Publisher publisher){
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getBooks().remove(this);
    }


}

