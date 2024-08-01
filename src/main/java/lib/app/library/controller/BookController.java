package lib.app.library.controller;

import lib.app.library.config.AppConstants;
import lib.app.library.dto.BookDTO;
import lib.app.library.dto.BookResponse;
import lib.app.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<BookResponse> findAllBooks(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                             @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                             @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
                                                             @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        BookResponse bookResponse = bookService.findAllBooks(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        BookDTO bookDTO = bookService.getBookById(id);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<BookResponse> getBooksByCategory(@PathVariable Long id){
        BookResponse bookResponse = bookService.searchBookByCategory(id);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/keyword/{key}")
    public ResponseEntity<BookResponse> getBooksByKeyword(@PathVariable String key){
        BookResponse bookResponse = bookService.searchBookByKeyword(key);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }



    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBooksByIsbn(@PathVariable String isbn){
        BookDTO bookDTO = bookService.searchBookByIsbn(isbn);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }




    @PostMapping("")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book){
        BookDTO bookDTO = bookService.createBook(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long id, @RequestBody BookDTO bookDTO ){
        return new ResponseEntity<>(bookService.updateBook(bookDTO, id), HttpStatus.OK);
    }






}
