package com.egenchallenge.emulator.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity("alerts")
@Component
@Scope("prototype")
public  class Alert extends BaseEntity{
	
	@Property("alertType")
	private String alertType;
	
	@Transient
	private Metric metrics;

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
