package com.joes.portfolio.book_info.publisher_rest_api.util;

import com.joes.portfolio.book_info.publisher_rest_api.model.Publisher;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class PublisherSearcher {

    public List<Publisher> publishersToSearch(String columnName, String searchArtifact, List<Publisher> publishers) {

        List<Publisher> publishersFound = new ArrayList<>();

        switch (columnName) {
            case "id" -> publishersFound = findMatchingPublishersById(publishers, searchArtifact);
            case "publisher_code" -> publishersFound = findMatchingRatingsByRatingCode(publishers, searchArtifact);
            case "publisher_name" -> publishersFound = findMatchingPublishersByPublisherName(publishers, searchArtifact);
        }
        return publishersFound;
    }

    private List<Publisher> findMatchingPublishersById(List<Publisher> publishers, String searchArtifact) {

        List<Publisher> found = new ArrayList<>();

        for (Publisher publisher : publishers ) {
            if ( publisher.getId().longValue() == parseLong(searchArtifact) ) {
                found.add(publisher);
                break;
            }
        }
        return found;
    }

    private List<Publisher> findMatchingRatingsByRatingCode(List<Publisher> publishers, String searchArtifact) {

        List<Publisher> found = new ArrayList<>();

        for (Publisher publisher : publishers ) {
            if ( publisher.getPublisher_code().contains(searchArtifact) ) {
                found.add(publisher);
                break;
            }
        }
        return found;
    }

    private List<Publisher> findMatchingPublishersByPublisherName(List<Publisher> publishers, String searchArtifact) {

        List<Publisher> found = new ArrayList<>();

        for (Publisher publisher : publishers ) {
            if ( publisher.getPublisher_name().contains(searchArtifact) ) {
                found.add(publisher);
            }
        }
        return found;
    }

    public Long getIdFromPublisherByPublisherCode(Publisher publisher, String publisherCode) {

        Long id = 0L;
        if ( publisher.getPublisher_code().contains(publisherCode) ) {
            id = publisher.getId();
        }
        return id;
    }
}
