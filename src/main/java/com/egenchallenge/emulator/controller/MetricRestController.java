package com.egenchallenge.emulator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egenchallenge.emulator.domain.Metric;
import com.egenchallenge.emulator.services.MetricService;


/**
 * @author saranjithkrishnan
 *
 */
@RestController
@RequestMapping("api/v1/")
public class MetricRestController extends AbstractRestHandler {
	
	@Autowired
	private MetricService metricService;
	
	//Get All Metric information
	@RequestMapping(value="metric", method=RequestMethod.GET)
	public List<Metric> read(){
		return metricService.findAllMetrics();
	}
	
	//Save the metric information
	@RequestMapping(value="metric", method=RequestMethod.POST)
	public void create(@RequestBody Metric metric){
		metricService.createMetric(metric); 
	}
	
	//Get all metric information based on the from and to date
	@RequestMapping(value="metric", params={"from","to"}, method=RequestMethod.GET)
	public List<Metric> readByTimeRange (
			@RequestParam("from") String from,
			@RequestParam("to") String to){
		return metricService.findMetricsBetweenDateRange(from, to);
	}
}
