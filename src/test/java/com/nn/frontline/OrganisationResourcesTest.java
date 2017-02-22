package com.nn.frontline;

import com.nn.frontline.model.Organisation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
public class OrganisationResourcesTest {

    public static final String HTTP_LOCALHOST = "http://localhost:";
    public static final String SEARCH = "/search";
    public static final String ORGANISTATION = "/organisation";
    public static final String ORGANISTATIONS = "/organisations";

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new TestRestTemplate();

    /**
     * Start checking health status of the service.
     * */
    @Test
    public void applicationHelthcheck() {
        final ResponseEntity<String> entity = this.restTemplate
                .getForEntity(HTTP_LOCALHOST + this.port + "/healthcheck", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    /**
     * List department after deleting content, assert that we start with an empty list.
     * */
    @Test
    public void searchOrganisation() {
        //given that a series of organisation are ready to be searched
        //given after making sure that department table is empty and there is only one ...
        this.restTemplate
                .delete(HTTP_LOCALHOST + this.port + ORGANISTATION);

        final Organisation newOrganisation = new Organisation.Builder()
                .withName("department new")
                .withBranch("test")
                .withLat(51.550531375273)
                .withLng(-0.0973081996294384)
                .withPostcode("TEST")
                .withSummary("test summary")
                .withTags("test tags")
                .withType("test")
                .withUrl("url")
                .build();

        this.restTemplate
                .postForObject(HTTP_LOCALHOST + this.port + ORGANISTATION, newOrganisation, Organisation.class);

        //and I hit the /organisations end point
        this.restTemplate
                .getForEntity(HTTP_LOCALHOST + this.port + ORGANISTATIONS, List.class);

        //and a search was made with given coordinates
        ///51.55/-0.09/100
        final Map<String, String> params = new HashMap<String, String>();
        params.put("lat", "51.55");
        params.put("lng", "-0.09");
        params.put("radius", "100");


        //when department endpoint service is hit
        final ResponseEntity<List> entity = this.restTemplate
                .getForEntity(HTTP_LOCALHOST + this.port + SEARCH + "/{lat}/{lng}/{radius}", List.class, params);

        //then I should get and ok status and a list object shouldn't be null
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
    }
}
