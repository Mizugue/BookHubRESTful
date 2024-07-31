package lib.app.library.service;

import lib.app.library.dto.AuthorDTO;
import lib.app.library.exception.ResourceNotFoundException;
import lib.app.library.model.Author;
import lib.app.library.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDTO> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()){
            throw new ResourceNotFoundException("authors");
        }
        return authors.stream().
                map(author -> modelMapper.map(author, AuthorDTO.class)).toList();
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("author"));
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public String deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("author"));
        authorRepository.delete(author);
        return "Author deleted: " + author.getName();

    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO, Long id) {
        Author savedAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("author"));
        modelMapper.map(authorDTO, savedAuthor);
        Author updatedAuthor = authorRepository.save(savedAuthor);
        return modelMapper.map(updatedAuthor, AuthorDTO.class);
    }

}
