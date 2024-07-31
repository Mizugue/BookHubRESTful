package lib.app.library.service;

import lib.app.library.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> findAllPublishers();
    PublisherDTO getPublisherById(Long id);
    PublisherDTO createPublisher(PublisherDTO publisherDTO);
    String deletePublisher(Long id);
    PublisherDTO updatePublisher(PublisherDTO publisherDTO, Long id);

}
