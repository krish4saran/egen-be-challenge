package com.egenchallenge.emulator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egenchallenge.emulator.domain.Alert;
import com.egenchallenge.emulator.repository.AlertDAORepository;

/**
 * @author saranjithkrishnan
 *
 */
@Service
public class AlertService {
	
	@Autowired
	private AlertDAORepository alertDAORepository;
	
	public List<Alert> getAlerts(){
		return alertDAORepository.findAll();
	}

	public List<Alert> findAlertsBetweenDateRange(String from, String to){
		return alertDAORepository.findAlertBetweenTimeRanges(from, to);
		
	}
}
