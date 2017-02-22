package com.nn.frontline.service;

import com.nn.frontline.model.Organisation;

import java.util.List;


/**
 * Created by santiagoginzburg on 14/02/2017.
 */
public interface OrganisationService {
    List<Organisation> searchOrganisationByLatLng(Double lat, Double lng);
    List<Organisation> searchOrganisationByLatLng(Double lat, Double lng, Double radius);
    List<Organisation> searchOrganisationByPostcode(String postcode);

    void deleteOrganisation();
    List<Organisation> listOrganisation();
    void createOrganisation(Organisation newOrganisation);
    Organisation findOrganisation(Long id);
    Organisation updateOrganisation(Organisation newOrganisation);
}
