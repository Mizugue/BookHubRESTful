package lib.app.library.service;

import lib.app.library.dto.PublisherDTO;
import lib.app.library.exception.ResourceNotFoundException;
import lib.app.library.model.Publisher;
import lib.app.library.repository.PublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService{


    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, ModelMapper modelMapper) {
        this.publisherRepository = publisherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PublisherDTO> findAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()){
            throw new ResourceNotFoundException("publishers");
        }
        return publishers.stream().
                map(publisher -> modelMapper.map(publisher, PublisherDTO.class)).toList();
    }

    @Override
    public PublisherDTO getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("publisher"));
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = modelMapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    @Override
    public String deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("publisher"));
        publisherRepository.delete(publisher);
        return "Publisher deleted: " + publisher.getName();

    }

    @Override
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO, Long id) {
        Publisher savedPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("publisher"));
        modelMapper.map(publisherDTO, savedPublisher);
        Publisher updatedPublisher = publisherRepository.save(savedPublisher);
        return modelMapper.map(updatedPublisher, PublisherDTO.class);
    }

}
