package com.egenchallenge.emulator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egenchallenge.emulator.domain.Alert;
import com.egenchallenge.emulator.services.AlertService;

/**This {@link Controller} will handle all request for the {@link Alert} resource.
 * Currently the requirement is only for:
 * - finding all alerts
 * - finding alert between two timestamps
 * @author saranjithkrishnan
 *
 */
@RestController
@RequestMapping("api/v1/")
public class AlertRestController {

	@Autowired
	private AlertService alertService;
	

	/**Get All {@link Alert} information
	 * @return
	 */
	@RequestMapping(value="alert", method=RequestMethod.GET)
	public List<Alert> read(){
		return alertService.getAlerts();
	}
	
	
	/**Get all {@link Alert} information based on the from and to date
	 * @param fromDate
	 * @param toDate
	 * @return List of {@link Alert}
	 */
	@RequestMapping(value="alert" ,params={"from","to"}, method=RequestMethod.GET)
	public List<Alert> readByTimeRange(
			@RequestParam("from") String fromDate,
			@RequestParam("to") String toDate){
		return alertService.findAlertsBetweenDateRange(fromDate, toDate);
	}
}
