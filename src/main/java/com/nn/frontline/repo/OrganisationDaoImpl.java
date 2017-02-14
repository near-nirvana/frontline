package com.nn.frontline.repo;

import com.nn.frontline.model.Organisation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by santiagoginzburg on 14/02/2017.
 */

@Component
@Transactional
public class OrganisationDaoImpl implements OrganisationDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Long save(final Organisation organisation) {
        em.persist(organisation);
        return organisation.getId();
    }

    @Override
    public int deleteOrganisations() {
        return em.createQuery("DELETE FROM Organisation").getFirstResult();
    }

    @Override
    public List<Organisation> listOrganisation() {
        return em.createQuery("FROM Organisation", Organisation.class).getResultList();
    }

    @Override
    public void saveOrganisation(final Organisation newOrganisation) {
        this.save(newOrganisation);

    }

    @Override
    public Organisation findOrganisation(final Long id) {
        final List<Organisation> organisations = em.createQuery("SELECT d FROM Organisation d WHERE d.id = :id")
                .setParameter("id", id)
                .getResultList();

        if (organisations.isEmpty()) {
            return  null;
        } else {
            return organisations.get(0);
        }
    }

    @Override
    public List<Organisation> findOrganisationByPostcode(final String postcode) {
        final List<Organisation> organisations = em.
                createQuery("SELECT d FROM Organisation d WHERE d.postcode = :postcode")
                .setParameter("postcode", postcode)
                .getResultList();

        return organisations;
    }

    @Override
    public List<Organisation> findOrganisationByLatLng(final Double lat, final Double lng) {
        final List<Organisation> organisations = em
                .createQuery("SELECT d FROM Organisation d WHERE d.lat = :lat and d.lng = :lng")
                .setParameter("lat", lat)
                .setParameter("lng", lng)
                .getResultList();

        return organisations;
    }

    @Override
    public List<Organisation> findOrganisationByLatLng(final Double lat, final Double lng, final Double radius) {
        final List<Organisation> organisations = (List<Organisation>) em

                .createQuery("SELECT id, name, url, lat, lng, postcode, type, summary, branch, tags, " +
                        "(6371 * acos(cos(radians(:lat)) * cos(radians(lat)) * cos(radians(lng) - radians(:lng))" +
                        " + sin(radians(:lat)) * sin(radians(lat)))) AS distance FROM Organisation " +
                        "WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(lat)) * cos(radians(lng)" +
                        " - radians(:lng)) + sin(radians(:lat)) * sin(radians(lat)))) < :radius ORDER BY distance")
                
                
                .setParameter("lat", lat)
                .setParameter("lng", lng)
                .setParameter("radius", radius)
                .getResultList();

        return organisations;
    }

    @Override
    public Organisation updateOrganisation(final Organisation organisation) {
        return  em.merge(organisation);
    }
}
