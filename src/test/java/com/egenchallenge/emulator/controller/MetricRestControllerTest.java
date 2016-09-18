package com.egenchallenge.emulator.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.egenchallenge.emulator.App;
import com.egenchallenge.emulator.domain.Metric;
import com.fasterxml.jackson.databind.ObjectMapper;

/**This is an integration test for Metric create and read operations
 * @author saranjithkrishnan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {App.class},initializers=ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class MetricRestControllerTest {

	@InjectMocks
    MetricRestController controller;

    @Autowired
    WebApplicationContext context;
    
    
    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private Metric metric = new Metric();
    private static final long timestamp = System.currentTimeMillis();
    
    @Test
    public void testMetricCreate() throws Exception{
    	
    	Metric metric = mockMetric();
    	byte[] jsonMetric = toJson(metric);
    	mvc.perform(post("/api/v1/metric")
    			 .content(jsonMetric)
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isCreated());
         
    }
    
    @Test
    public void testMetricRead() throws Exception{
    	
		 mvc.perform(get("/api/v1/metric"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$", notNullValue()));
         
    }
    
    @Test
    public void testMetricReadBetweenDateRange() throws Exception{
    	
    	long from = timestamp - 1;
    	long to = timestamp + 1;
		 mvc.perform(get("/api/v1/metric?from="+from+"&to="+to))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$", hasSize(1)));
         
    }

    
    private Metric mockMetric(){
    	metric.setTimeStamp(timestamp);
    	metric.setValue(100f);
    	return metric;
    }
    
    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
}

