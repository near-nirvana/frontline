package com.nn.frontline.resources;

import com.nn.frontline.model.Organisation;
import com.nn.frontline.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

        return organisationService.searchOrganisationByLatLng(lat, lng, radius);
    }
}
