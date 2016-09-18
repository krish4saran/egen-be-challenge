package com.egenchallenge.emulator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.egenchallenge.emulator.domain.Metric;
import com.egenchallenge.emulator.services.MetricService;


/**This {@link Controller} will handle all request for the {@link Metric} resource.
 * Currently the requirement is only for:
 * - finding all {@link Metric}
 * - finding {@link Metric} between two timestamps
 * - create {@link Metric}
 * @author saranjithkrishnan
 *
 */
@RestController
@RequestMapping("api/v1/")
public class MetricRestController {
	
	@Autowired
	private MetricService metricService;
	

	/**Get All {@link Metric} information
	 * @return
	 */
	@RequestMapping(value="metric", method=RequestMethod.GET)
	public List<Metric> read(){
		return metricService.findAllMetrics();
	}
	
	
	
	/**Save the {@link Metric} information
	 * @param metric
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="metric", method=RequestMethod.POST)
	public void create(@RequestBody Metric metric){
		metricService.createMetric(metric); 
	}
	
	
	
	/**Get all {@link Metric}c information based on the from and to date
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestMapping(value="metric", params={"from","to"}, method=RequestMethod.GET)
	public List<Metric> readByTimeRange (
			@RequestParam("from") long from,
			@RequestParam("to") long to){
		return metricService.findMetricsBetweenDateRange(from, to);
	}
}
