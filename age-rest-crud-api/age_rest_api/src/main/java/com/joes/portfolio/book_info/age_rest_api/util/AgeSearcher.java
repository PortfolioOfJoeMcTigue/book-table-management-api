package com.joes.portfolio.book_info.age_rest_api.util;

import com.joes.portfolio.book_info.age_rest_api.model.Age;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class AgeSearcher {

    public List<Age> agesToSearch(String columnName, String searchArtifact, List<Age> ages) {

        List<Age> agesFound = new ArrayList<>();

        switch (columnName) {
                case "id" -> agesFound = findMatchingAgesById(ages, searchArtifact);
                case "age_code" -> agesFound = findMatchingAgesByAgeCode(ages, searchArtifact);
                case "age_range" -> agesFound = findMatchingAgesByAgeRange(ages, searchArtifact);
        }
        return agesFound;
    }

    private List<Age> findMatchingAgesById(List<Age> ages, String searchArtifact) {

        List<Age> found = new ArrayList<>();

        for (Age age : ages ) {
            if ( age.getId().longValue() == parseLong(searchArtifact) ) {
                found.add(age);
                break;
            }
        }
        return found;
    }

    private List<Age> findMatchingAgesByAgeCode(List<Age> ages, String searchArtifact) {

        List<Age> found = new ArrayList<>();

        for (Age age : ages ) {
            if ( age.getAge_code().contains(searchArtifact) ) {
                found.add(age);
                break;
            }
        }
        return found;
    }

    private List<Age> findMatchingAgesByAgeRange(List<Age> ages, String searchArtifact) {

        List<Age> found = new ArrayList<>();

        for (Age age : ages ) {
            if ( age.getAge_range().contains(searchArtifact) ) {
                found.add(age);
            }
        }
        return found;
    }

    public Long getIdFromSuggestedAgeByAgeCode(Age age, String ageCode) {

        Long id = 0L;
        if ( age.getAge_code().contains(ageCode) ) {
            id = age.getId();
        }
        return id;
    }
}
