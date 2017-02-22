package com.nn.frontline.repo;

import com.nn.frontline.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by santiagoginzburg on 20/02/2017.
 */
@Component
@Transactional
public interface SearchDao extends JpaRepository<Organisation, Long> {

    @Query(value = "SELECT id, name, url, lat, lng, postcode, type, summary, branch, tags, " +
            "(6371 * acos(cos(radians(?1)) * cos(radians(lat)) * cos(radians(lng) - radians(?2)) " +
            " + sin(radians(?1)) * sin(radians(lat)))) AS distance FROM organisation " +
            "WHERE (6371 * acos(cos(radians(?1)) * cos(radians(lat)) * cos(radians(lng)" +
            " - radians(?2)) + sin(radians(?1)) * sin(radians(lat)))) < ?3 ORDER BY distance", nativeQuery = true)
    List<Organisation> findOrganisationByLatLng(final Double lat, final Double lng, final Double radius);
}
