package com.nn.frontline.service;

import com.nn.frontline.model.Organisation;
import com.nn.frontline.repo.OrganisationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by santiagoginzburg on 14/02/2017.
 */
@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public List<Organisation> searchOrganisationByLatLng(final Double lat, final Double lng) {
        return organisationDao.findOrganisationByLatLng(lat, lng);
    }

    @Override
    public List<Organisation> searchOrganisationByLatLng(final Double lat, final Double lng, final Double radius) {
        return organisationDao.findOrganisationByLatLng(lat, lng, radius);
    }

    @Override
    public List<Organisation> searchOrganisationByPostcode(final String postcode) {
        return organisationDao.findOrganisationByPostcode(postcode);
    }
}
