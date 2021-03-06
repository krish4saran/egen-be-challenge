package com.egenchallenge.emulator.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity("alerts")
@Component
@Scope("prototype")
public  class Alert extends BaseEntity{
	
	@Property("alertType")
	private String alertType;
	
	/**
	 *@todo - transient fields are getting generated in db - unknown issue
	 */
	@JsonIgnore
	@Transient
	private Metric metrics;

	/**
	 *@todo - transient fields are getting generated in db - unknown issue
	 */
	@JsonIgnore
	@Transient
	private float baseWeight;
	

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	
	public Metric getMetrics() {
		return metrics;
	}

	public void setMetrics(Metric metrics) {
		this.metrics = metrics;
	}

	public float getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(float baseWeight) {
		this.baseWeight = baseWeight;
	}

	
	

}
