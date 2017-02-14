package com.nn.frontline;

import com.nn.frontline.model.Department;
import com.nn.frontline.model.Employee;
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

import java.util.Arrays;
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

        //and a search was made with given coordinates
        final Map<String, String> params = new HashMap<String, String>();
        params.put("lat", "54.3");
        params.put("lng", "5.4");
        params.put("radius", "5.0");


        //when department endpoint service is hit
        final ResponseEntity<List> entity = this.restTemplate
                .getForEntity(HTTP_LOCALHOST + this.port + SEARCH + "/{lat}/{lng}/{radius}", List.class, params);

        //then I should get and ok status and a list object shouldn't be null
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
    }

}
