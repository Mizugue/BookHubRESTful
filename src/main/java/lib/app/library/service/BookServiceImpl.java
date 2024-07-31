package lib.app.library.service;

import lib.app.library.dto.BookDTO;
import lib.app.library.dto.BookResponse;
import lib.app.library.exception.ResourceNotFoundException;
import lib.app.library.model.Book;
import lib.app.library.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookResponse findAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Book> productPage = bookRepository.findAll(pageable);
        List<Book> books = productPage.getContent();
        List<BookDTO> booksDTOS = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).toList();
        if (books.isEmpty()){
            throw new ResourceNotFoundException("book");
        }
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(booksDTOS);
        bookResponse.setPageNumber(productPage.getNumber());
        bookResponse.setPageSize(productPage.getSize());
        bookResponse.setTotalElements(productPage.getTotalElements());
        bookResponse.setTotalPages(productPage.getTotalPages());
        bookResponse.setLastPage(productPage.isLast());
        bookResponse.setSort(productPage.getSort().toString());
        return bookResponse;
    }


    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("book"));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public String deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("book"));
        return "Book deleted: " + book.getName();

    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long id) {
        Book savedBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("book"));
        Book book = modelMapper.map(bookDTO, Book.class);
        savedBook.setAuthors(book.getAuthors());
        savedBook.setDescription(book.getDescription());
        savedBook.setName(book.getName());
        savedBook.setIsbn(book.getIsbn());
        savedBook.setCategories(book.getCategories());
        savedBook.setPublishers(book.getPublishers());
        Book saved = bookRepository.save(savedBook);
        return modelMapper.map(saved, BookDTO.class);

    }
}
