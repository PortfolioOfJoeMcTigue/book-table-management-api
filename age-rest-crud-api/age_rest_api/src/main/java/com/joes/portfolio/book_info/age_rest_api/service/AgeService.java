package com.joes.portfolio.book_info.age_rest_api.service;

import com.joes.portfolio.book_info.age_rest_api.exception.ResourceNotFoundException;
import com.joes.portfolio.book_info.age_rest_api.model.Age;
import com.joes.portfolio.book_info.age_rest_api.repository.AgeRepository;
import com.joes.portfolio.book_info.age_rest_api.util.AgeSearcher;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgeService {

    @Autowired
    private AgeRepository ageRepository;

    private static final Logger logger = Logger.getLogger(AgeService.class);

    public ResponseEntity<List<Age>> listAllSuggestedAges() {
        logger.info("starting request for list of all Suggested Age records.");
        List<Age> ages;
        String message;
        try {
            ages = (List<Age>) ageRepository.findAll();
        } catch (ResourceNotFoundException rnfe) {
            message = "resources not found in listAllSuggestedAges.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ages);
    }
    public ResponseEntity<String> createSuggestedAge(Age age) {
        String message;
        try {
            ageRepository.save(age);
            message = "Successfully saved new Suggested Age.";
        } catch(ResourceNotFoundException rnfe) {
            message = "Could not save Suggested Age.";
            logger.info(message);
        }
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<List<Age>> listAllMatchingSuggestedAges(String columnName, String searchArtifact) {
        List<Age> allAges = (List<Age>) ageRepository.findAll();
        String message;
        if ( allAges.isEmpty() ) {
            message = "No Suggested Age data was returned from searching by column: { "+columnName+" } and searchArtifact: { "+searchArtifact+" } request.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        AgeSearcher ageSearcher = new AgeSearcher();
        return ResponseEntity.ok(ageSearcher.agesToSearch(columnName, searchArtifact, allAges));
    }
    public ResponseEntity<Age> updateSuggestedAgeByAgeCode(Age age, String ageCode) {
        AgeSearcher ageSearcher = new AgeSearcher();
        String message;
        try {
            Long id = ageSearcher.getIdFromSuggestedAgeByAgeCode(age, ageCode);
            Optional<Age> ageOptional = ageRepository.findById(id);
            if ( ageOptional.isEmpty() ) {
                message = "No records could be found by age_code { "+ ageCode + "} requested.";
                logger.info(message);
                return ResponseEntity.notFound().build();
            }
            age.setId(id);
            ageRepository.save(age);
        } catch (ResourceNotFoundException rnfe) {
            message = "Suggested Age record could not be updated by ageCode { "+ ageCode +" } requested.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(age);
    }
    public ResponseEntity<String> deleteSuggestedAgeByAgeCode(String ageCode) {
        Long id = null;
        List<Age> allAges;
        String message;
        try {
            allAges = (List<Age>) ageRepository.findAll();
            if ( allAges.isEmpty() ) {
                message = "No Suggested Age records were returned with matching age_code: { "+ageCode+" } requested.";
                throw new ResourceNotFoundException(message);
            }
        } catch (ResourceNotFoundException rnfe) {
            message = "There are no entries yet in the Suggested Ages table.";
            logger.info(message);
            return ResponseEntity.ok(message);
        }
        List<Age> ageMatchingAgeCode;
        try {
            AgeSearcher ageSearcher = new AgeSearcher();
            ageMatchingAgeCode = ageSearcher.agesToSearch("age_code", ageCode, allAges);
            if ( ageMatchingAgeCode.isEmpty() ) {
                message = "No Suggested Ages match age_code {" + ageCode + "} requested.";
                logger.info(message);
                return ResponseEntity.ok(message);
            }
        } catch (ResourceNotFoundException rnfe) {
            message = "No Suggested Ages match age_code {" + ageCode + "} requested.";
            logger.info(message);
            return ResponseEntity.ok(message);
        }
        for (Age age : ageMatchingAgeCode) {
            id = age.getId();
            break;
        }
        if (id == 0L) {
            message = "No id was found within the Suggested Age rows matching ageCode { " +ageCode+ " } requested.";
            return ResponseEntity.ok(message);
        }
        try {
            ageRepository.deleteById(id);
            message = "Suggested Age record found by age_code {"+ageCode+"} has been deleted successfully.";
        } catch (ResourceNotFoundException rnfe) {
            message = "No Suggested Age by age_code {"+ageCode+"} exist.";
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(message);
    }
}
