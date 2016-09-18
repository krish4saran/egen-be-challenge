package com.egenchallenge.emulator.repository;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egenchallenge.emulator.domain.Alert;
import com.egenchallenge.emulator.domain.Metric;


/**This {@link Repository} is used for CRUD operations related to {@link Metric} document 
 * against metrics collection
 * @author saranjithkrishnan
 *
 */
@Repository
public class MetricDAORepository extends BasicDAO<Metric, String> {

	@Autowired
	private Datastore dataStore;
	
	protected MetricDAORepository(Datastore ds) {
		super(ds);
	}

	/**List all {@link Metric} records (documents) available in the metrics collections
	 * @return
	 */
	public List<Metric> findAll() {
	    return dataStore.find(Metric.class).asList();
	}

	
   /**List all {@link Metric} records (documents) avaialable between two timestamps
 * @param from
 * @param to
 * @return
 */
	public List<Metric> findMetricBetweenTimeRanges(long from, long to){
	   return dataStore.find(Metric.class).field("timeStamp").greaterThan(from).field("timeStamp").lessThan(to).asList();
   }
   
   /**Find base metric or the first metric document available
	 * @return
	 */
	public Metric findBaseMetricValue(){
	   return dataStore.find(Metric.class).get();
   }
}
