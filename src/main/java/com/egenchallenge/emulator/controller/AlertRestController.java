package com.egenchallenge.emulator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egenchallenge.emulator.domain.Alert;
import com.egenchallenge.emulator.services.AlertService;

/**
 * @author saranjithkrishnan
 *
 */
@RestController
@RequestMapping("api/v1/")
public class AlertRestController extends AbstractRestHandler{

	@Autowired
	private AlertService alertService;
	
	//Get All alert information
	@RequestMapping(value="alert", method=RequestMethod.GET)
	public List<Alert> read(){
		return alertService.getAlerts();
	}
	
	//Get all alert	 information based on the from and to date
	@RequestMapping(value="alert" ,params={"from","to"}, method=RequestMethod.GET)
	public List<Alert> readByTimeRange(
			@RequestParam("from") String fromDate,
			@RequestParam("to") String toDate){
		return alertService.findAlertsBetweenDateRange(fromDate, toDate);
	}
}
