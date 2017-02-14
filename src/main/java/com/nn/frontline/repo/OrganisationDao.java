package com.nn.frontline.repo;

import com.nn.frontline.model.Organisation;

import java.util.List;

/**
 * Created by santiagoginzburg on 14/02/2017.
 */
public interface OrganisationDao {

    Long save(Organisation organisation);

    int deleteOrganisations();

    List<Organisation> listOrganisation();

    void saveOrganisation(Organisation newOrganisation);

    Organisation findOrganisation(Long id);

    Organisation updateOrganisation(Organisation organisation);

    List<Organisation> findOrganisationByPostcode(final String postcode);

    List<Organisation> findOrganisationByLatLng(final Double lat, final Double lng);

    List<Organisation> findOrganisationByLatLng(final Double lat, final Double lng, final Double radius);

}
