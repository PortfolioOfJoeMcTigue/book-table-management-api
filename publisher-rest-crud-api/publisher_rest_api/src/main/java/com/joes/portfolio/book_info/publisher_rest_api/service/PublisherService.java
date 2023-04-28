package com.joes.portfolio.book_info.publisher_rest_api.service;

import com.joes.portfolio.book_info.publisher_rest_api.exception.ResourceNotFoundException;
import com.joes.portfolio.book_info.publisher_rest_api.model.Publisher;
import com.joes.portfolio.book_info.publisher_rest_api.repository.PublisherRepository;
import com.joes.portfolio.book_info.publisher_rest_api.util.PublisherSearcher;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    private static final Logger logger = Logger.getLogger(PublisherService.class);

    public ResponseEntity<List<Publisher>> listAllPublishers() {
        logger.info("starting request for list of all Publisher records.");
        List<Publisher> publishers;
        String message;
        try {
            publishers = (List<Publisher>) publisherRepository.findAll();
        } catch (ResourceNotFoundException rnfe) {
            message = "resources not found in listAllPublishers.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publishers);
    }
    public ResponseEntity<String> createPublisher(Publisher publisher) {
        String message;
        try {
            publisherRepository.save(publisher);
            message = "Successfully saved new Publisher.";
        } catch(ResourceNotFoundException rnfe) {
            message = "Could not save Publisher.";
            logger.info(message);
        }
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<List<Publisher>> listAllMatchingPublishers(String columnName, String searchArtifact) {
        List<Publisher> allPublishers = (List<Publisher>) publisherRepository.findAll();
        String message;
        if ( allPublishers.isEmpty() ) {
            message = "No Publisher data was returned from searching by column: { "+columnName+" } and searchArtifact: { "+searchArtifact+" } request.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        PublisherSearcher publisherSearcher = new PublisherSearcher();
        return ResponseEntity.ok(publisherSearcher.publishersToSearch(columnName, searchArtifact, allPublishers));
    }
    public ResponseEntity<Publisher> updatePublisherByPublisherCode(Publisher publisher, String publisherCode) {
        PublisherSearcher publisherSearcher = new PublisherSearcher();
        String message;
        try {
            Long id = publisherSearcher.getIdFromPublisherByPublisherCode(publisher, publisherCode);
            Optional<Publisher> publisherOptional = publisherRepository.findById(id);
            if ( publisherOptional.isEmpty() ) {
                message = "No Publisher records could be found by publisher_code { "+ publisherCode +" } requested.";
                logger.info(message);
                return ResponseEntity.notFound().build();
            }
            publisher.setId(id);
            publisherRepository.save(publisher);
        } catch (ResourceNotFoundException rnfe) {
            message = "Suggested Publisher record could not be updated by publisher_code { "+ publisherCode +" } requested.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publisher);
    }
    public ResponseEntity<String> deletePublisherByPublisherCode(String publisherCode) {
        Long id = null;
        List<Publisher> allPublishers;
        String message;
        try {
            allPublishers = (List<Publisher>) publisherRepository.findAll();
            if ( allPublishers.isEmpty() ) {
                message = "No Publishers records were returned with matching publisher_code: { "+publisherCode+" } requested.";
                throw new ResourceNotFoundException(message);
            }
        } catch (ResourceNotFoundException rnfe) {
            message = "There are no entries yet in the Publishers table.";
            logger.info(message);
            return ResponseEntity.ok(message);
        }
        List<Publisher> publisherMatchingPublisherCode;
        try {
            PublisherSearcher publisherSearcher = new PublisherSearcher();
            publisherMatchingPublisherCode = publisherSearcher.publishersToSearch("publisher_code", publisherCode, allPublishers);
            if ( publisherMatchingPublisherCode.isEmpty() ) {
                message = "No Publishers match rating_code {" + publisherCode + "} requested.";
                logger.info(message);
                return ResponseEntity.ok(message);
            }
        } catch (ResourceNotFoundException rnfe) {
            message = "No Publishers match rating_code {" + publisherCode + "} requested.";
            logger.info(message);
            return ResponseEntity.ok(message);
        }
        for (Publisher publisher : publisherMatchingPublisherCode) {
            id = publisher.getId();
            break;
        }
        if (id == 0L) {
            message = "No id was found within the Publisher rows matching publisher_code { " +publisherCode+ " } requested.";
            return ResponseEntity.ok(message);
        }
        try {
            publisherRepository.deleteById(id);
            message = "Publisher record found by publisher_code {"+publisherCode+"} has been deleted successfully.";
        } catch (ResourceNotFoundException rnfe) {
            message = "No Publisher by publisher_code {"+publisherCode+"} exist.";
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(message);
    }
}
