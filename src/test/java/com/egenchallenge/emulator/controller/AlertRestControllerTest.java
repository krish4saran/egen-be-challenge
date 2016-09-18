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

/**This is an integration test for Alerts create and read operations
 * @author saranjithkrishnan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {App.class},initializers=ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class AlertRestControllerTest {

	@InjectMocks
    AlertRestController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;
    
    /**
     *Create test requests to generate alerts in the test system 
     */
    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        
        Metric baseMetric = mockMetric(100, timestamp);
    	Metric overWeighMetric = mockMetric(120, timestamp+1);
    	Metric underWeighMetric = mockMetric(80, timestamp+2);
    	try {
    		byte[] jsonMetric = toJson(baseMetric);
    		mvc.perform(post("/api/v1/metric")
					 .content(jsonMetric)
			         .contentType(MediaType.APPLICATION_JSON)
			         .accept(MediaType.APPLICATION_JSON))
			         .andExpect(status().isCreated());
    		
    		
    		jsonMetric = toJson(overWeighMetric);
    		mvc.perform(post("/api/v1/metric")
					 .content(jsonMetric)
			         .contentType(MediaType.APPLICATION_JSON)
			         .accept(MediaType.APPLICATION_JSON))
			         .andExpect(status().isCreated());
    		
    		jsonMetric = toJson(underWeighMetric);
    		mvc.perform(post("/api/v1/metric")
					 .content(jsonMetric)
			         .contentType(MediaType.APPLICATION_JSON)
			         .accept(MediaType.APPLICATION_JSON))
			         .andExpect(status().isCreated());
		} catch (Exception e) {
			System.out.println("Exception creating new metric due to:"+e.getMessage());
		}
         
        
    }

    
    private static final long timestamp = System.currentTimeMillis();
    
    /**Test for all alerts - since the above set up will force system
     * to create two alerts - this test will make sure the alerts are
     * getting generated.
     * @throws Exception
     */
    @Test
    public void testAlertRead() throws Exception{
    	
		 mvc.perform(get("/api/v1/alert"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$", notNullValue()));
         
    }
    
    /**Test for alerts between date range - since the above set up creates
     * over and under weight alert, with the timestamps avaialble can query
     * these - should always result 2 - under & over with this setup
     * @throws Exception
     */
    @Test
    public void testAlertReadBetweenDateRange() throws Exception{
    
    	long from = timestamp;
    	long to = timestamp + 3 ;
		 mvc.perform(get("/api/v1/alert?from="+from+"&to="+to))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$", hasSize(2)));
         
    }

    
    private Metric mockMetric(float value, long timestamp){
    	Metric metric = new Metric();
    	metric.setTimeStamp(timestamp);
    	metric.setValue(value);
    	return metric;
    }
    
    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
}

