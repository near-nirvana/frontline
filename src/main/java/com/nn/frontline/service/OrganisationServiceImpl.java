package com.nn.frontline.service;

import com.nn.frontline.model.Organisation;
import com.nn.frontline.repo.OrganisationDao;
import com.nn.frontline.repo.SearchDao;
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

    @Autowired
    private SearchDao searchDao;

    @Override
    public List<Organisation> searchOrganisationByLatLng(final Double lat, final Double lng) {
        return organisationDao.findOrganisationByLatLng(lat, lng);
    }

    @Override
    public List<Organisation> searchOrganisationByLatLng(final Double lat, final Double lng, final Double radius) {
        return searchDao.findOrganisationByLatLng(lat, lng, radius);
    }

    @Override
    public List<Organisation> searchOrganisationByPostcode(final String postcode) {
        return organisationDao.findOrganisationByPostcode(postcode);
    }

    @Override
    public void deleteOrganisation() {
        organisationDao.deleteOrganisations();
    }

    @Override
    public List<Organisation> listOrganisation() {
        return organisationDao.listOrganisation();
    }

    @Override
    public void createOrganisation(final Organisation newOrganisation) {
        organisationDao.save(newOrganisation);
    }

    @Override
    public Organisation findOrganisation(final Long id) {
        return organisationDao.findOrganisation(id);
    }

    @Override
    public Organisation updateOrganisation(final Organisation newOrganisation) {
        return organisationDao.updateOrganisation(newOrganisation);
    }
}
