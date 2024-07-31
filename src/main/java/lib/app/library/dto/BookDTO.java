package lib.app.library.dto;

import jakarta.persistence.*;
import lib.app.library.model.Author;
import lib.app.library.model.Category;
import lib.app.library.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String isbn;
    private String name;
    private String description;
    private Set<Author> authors =new HashSet<Author>();
    private Set<Category> categories = new HashSet<Category>();
    private Set<Publisher> publishers = new HashSet<Publisher>();



}
