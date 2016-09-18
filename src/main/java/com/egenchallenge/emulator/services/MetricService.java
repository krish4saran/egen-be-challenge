package com.egenchallenge.emulator.services;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.egenchallenge.emulator.domain.Metric;
import com.egenchallenge.emulator.domain.OverWeightAlert;
import com.egenchallenge.emulator.domain.UnderWeightAlert;
import com.egenchallenge.emulator.repository.AlertDAORepository;
import com.egenchallenge.emulator.repository.MetricDAORepository;

/**Service class for handling all metrics information
 * @author saranjithkrishnan
 *
 */
@Service
public class MetricService {

	@Autowired
	private MetricDAORepository metricDAORepository;
	
	@Autowired
	private AlertDAORepository alertDAORepository;

	/**
	 * There is an issue with getting prototype beans by autowiring(basically retrieving the same instance) - so getting context
	 * http://stackoverflow.com/questions/7621920/scopeprototype-bean-scope-not-creating-new-bean
	 */
	@Autowired
    private WebApplicationContext context;
	
	/**
	 * @return
	 */
	public List<Metric> findAllMetrics(){
		return metricDAORepository.findAll();
	}
	
	/**Save metrics information along with the alert if any rules are triggered
	 * @param metric
	 */
	public void createMetric(Metric metric){
		
		//Run through the rules
		OverWeightAlert overWeightAlert = getOverWeightAlert(); 
		UnderWeightAlert underWeightAlert = getUnderWeightAlert();
		float baseWeight = metricDAORepository.findBaseMetricValue() != null ? metricDAORepository.findBaseMetricValue().getValue() : 0f;
		overWeightAlert.setMetrics(metric);overWeightAlert.setBaseWeight(baseWeight);
		underWeightAlert.setMetrics(metric);underWeightAlert.setBaseWeight(baseWeight);
		
		RulesEngine rulesEngine = aNewRulesEngine().build();
        rulesEngine.registerRule(overWeightAlert);
        rulesEngine.registerRule(underWeightAlert);
        
        rulesEngine.fireRules();
        
        
        //save alert to the repository to generate the id
        if(metric.getAlert() != null){
        	metric.getAlert().setTimeStamp(metric.getTimeStamp());
        	alertDAORepository.save(metric.getAlert());
        }
        //Now save the metric with reference to alert
        metricDAORepository.save(metric);
	}
	
	/**find metrics between the date range
	 * @param from
	 * @param to
	 * @return
	 */
	public List<Metric> findMetricsBetweenDateRange(long from, long to){
		return metricDAORepository.findMetricBetweenTimeRanges(from, to);
		
	}
	
	public UnderWeightAlert getUnderWeightAlert(){
		return (UnderWeightAlert) context.getBean("UnderWeightAlert");
	}
	
	public OverWeightAlert getOverWeightAlert(){
		return (OverWeightAlert) context.getBean("OverWeightAlert");
	}

	public MetricDAORepository getMetricDAORepository() {
		return metricDAORepository;
	}

	public void setMetricDAORepository(MetricDAORepository metricDAORepository) {
		this.metricDAORepository = metricDAORepository;
	}

	
	
}
