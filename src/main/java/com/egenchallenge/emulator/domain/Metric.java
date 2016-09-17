package com.egenchallenge.emulator.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity("metrics")
@Component
@Scope("prototype")
public class Metric extends BaseEntity {

	@Property("value")
	private float value;
	
	@Reference
	private Alert alert;

	
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	
	
}
