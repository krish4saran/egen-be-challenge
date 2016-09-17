package com.egenchallenge.emulator.domain;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**Rule for setting the correct alert in the metric based on the @condition
 * @author saranjithkrishnan
 *
 */
@Rule(name = "overWeightRule", description = "Execute the rule when persons weight above 10%")
@Component("OverWeightAlert")
@Scope("prototype")
@Entity("alerts")
public class OverWeightAlert extends Alert {

	public OverWeightAlert() {
		setAlertType(AlertType.OVERWEIGHT.toString());
	}
	
	/**This method will check the condition to trigger the event
	 * If the current weight is greater than x% over the base weight
	 * 
	 * @return
	 */
	@Condition
    public boolean whenOverWeight() {
		boolean isOverWeight = false;
		if(getBaseWeight() == 0)
			return false;
        if(getMetrics() != null){
        	float percentOfbaseWeight = getBaseWeight() *  10/100;
        	if(getMetrics().getValue() > percentOfbaseWeight + getBaseWeight()){
        		isOverWeight =  true;
        	}
        	
        }
        	
        return isOverWeight;
    }

    @Action(order = 1)
    public void update() throws Exception {
    	getMetrics().setAlert(this);
    }
    

	
}
