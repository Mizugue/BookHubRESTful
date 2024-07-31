package lib.app.library.service;

import lib.app.library.dto.AuthorDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {
    List<AuthorDTO> findAllAuthors();
    AuthorDTO getAuthorById(Long id);
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    String deleteAuthor(Long id);
    AuthorDTO updateAuthor(AuthorDTO authorDTO, Long id);

}
