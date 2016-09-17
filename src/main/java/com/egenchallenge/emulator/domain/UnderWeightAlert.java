package com.egenchallenge.emulator.domain;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**Rule for setting the correct alert in the metric based on the @condition
 * @author saranjithkrishnan
 *
 */
@Rule(name = "underWeightRule", description = "Execute the rule when persons weight drops below 10% ")
@Component("UnderWeightAlert")
@Scope("prototype")
public class UnderWeightAlert extends Alert {

	public UnderWeightAlert() {
		setAlertType(AlertType.UNDERWEIGHT.toString());
	}
	
	/**This method will check the condition to trigger the event
	 * 
	 * If the current weight is less than x% below the base weight
	 * @return
	 */
	@Condition
    public boolean whenUnderWeight() {
		boolean isUnderWeight = false;
		if(getBaseWeight() == 0)
			return false;
		if(getMetrics() != null){
        	if(getMetrics() != null){
            	float percentOfbaseWeight = getBaseWeight() *  10/100;
            	if(getMetrics().getValue() < getBaseWeight() - percentOfbaseWeight){
            		isUnderWeight =  true;
            	}
            	
            }
            	
            return isUnderWeight;
        	
        }
        	
        return isUnderWeight;
    }

    /**Set this alert on the metrics object
     * @throws Exception
     */
    @Action(order = 1)
    public void update() throws Exception {
    	getMetrics().setAlert(this);
    }
    
}
