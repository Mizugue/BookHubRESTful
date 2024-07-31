package lib.app.library.dto;

import lib.app.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {
    private Long id;
    private String name;
    private Set<Book> books = new HashSet<Book>();
}


