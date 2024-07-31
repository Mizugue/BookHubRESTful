package lib.app.library.service;

import lib.app.library.dto.BookDTO;
import lib.app.library.dto.BookResponse;
import lib.app.library.exception.APIException;
import lib.app.library.exception.ResourceNotFoundException;
import lib.app.library.model.Book;
import lib.app.library.model.Category;
import lib.app.library.repository.BookRepository;
import lib.app.library.repository.CategoryRepository;
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

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

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
        modelMapper.map(bookDTO, savedBook);
        Book updatedBook = bookRepository.save(savedBook);
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    @Override
    public BookResponse searchBookByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category"));
        List<Book> books = bookRepository.findByCategories(category);
        List<BookDTO> bookDTOS = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).toList();
        if (bookDTOS.isEmpty()){
            throw new APIException("Void category");
        }
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDTOS);
        return bookResponse;
    }


    @Override
    public BookResponse searchBookByKeyword(String keyword) {
        List<Book> books = bookRepository.findByNameLikeIgnoreCase('%' + keyword + '%');
        List<BookDTO> bookDTOS = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).toList();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDTOS);
        return bookResponse;
    }



    @Override
    public BookDTO searchBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).
                orElseThrow(() -> new ResourceNotFoundException("book"));
        return modelMapper.map(book, BookDTO.class);
    }

}
