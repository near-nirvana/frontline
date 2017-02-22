package com.nn.frontline.resources;

import com.nn.frontline.model.Organisation;
import com.nn.frontline.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by santiagoginzburg on 14/02/2017.
 */
@RestController
public class OrganisationResources {
    @Autowired
    private OrganisationService organisationService;

    @RequestMapping(value = "/search/{lat}/{lng}/{radius}", method = RequestMethod.GET)
    public List<Organisation> organisation(@PathVariable("lat") final Double lat,
                                           @PathVariable("lng") final Double lng,
                                           @PathVariable("radius") final Double radius) {

        return  organisationService.searchOrganisationByLatLng(lat, lng, radius);
    }

    @RequestMapping(value = "/organisation", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrganisations() {
        organisationService.deleteOrganisation();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/organisations", method = RequestMethod.GET)
    public List<Organisation> getOrganisations() {
        return organisationService.listOrganisation();
    }

    @RequestMapping(value = "/organisation", method = RequestMethod.POST)
    public Organisation postOrganisation(@RequestBody final Organisation organisation) {
        final Organisation newOrganisation = new Organisation.Builder()
                .withName(organisation.getName())
                .withUrl(organisation.getUrl())
                .withLat(organisation.getLat())
                .withLng(organisation.getLng())
                .build();
        organisationService.createOrganisation(newOrganisation);
        return newOrganisation;
    }

    @RequestMapping(value = "/organisation/{id}", method = RequestMethod.GET)
    public Organisation organisation(@PathVariable("id") final  Long id) {
        return organisationService.findOrganisation(id);
    }

    @RequestMapping(value = "/organisation", method = RequestMethod.PUT)
    public Organisation updateOrganisation(@RequestBody final Organisation organisation) {

        return organisationService.updateOrganisation(new Organisation.Builder()
                .withId(organisation.getId())
                .withName(organisation.getName())
                .build());
    }
}
