package lib.app.library.service;

import lib.app.library.dto.BookDTO;
import lib.app.library.dto.BookResponse;
import lib.app.library.model.Book;

import java.util.List;

public interface BookService {
    BookResponse findAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO book);
    String deleteBook(Long id);
    BookDTO updateBook(BookDTO book, Long id);


}
