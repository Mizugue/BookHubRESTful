package lib.app.library.repository;

import lib.app.library.model.Book;
import lib.app.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategories(Category category);
    List<Book> findByNameLikeIgnoreCase(String s);
    Optional<Book> findByIsbn(String isbn);
}
