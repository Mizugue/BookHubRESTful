package lib.app.library.controller;


import lib.app.library.dto.PublisherDTO;
import lib.app.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("")
    public ResponseEntity<List<PublisherDTO>> findAllBooks() {
        return new ResponseEntity<>(publisherService.findAllPublishers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Long id){
        return new ResponseEntity<>(publisherService.getPublisherById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDTO publisherDTO){
        return new ResponseEntity<>(publisherService.createPublisher(publisherDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long id){
        return new ResponseEntity<>(publisherService.deletePublisher(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable long id, @RequestBody PublisherDTO publisherDTO){
        return new ResponseEntity<>(publisherService.updatePublisher(publisherDTO, id), HttpStatus.OK);
    }
}
