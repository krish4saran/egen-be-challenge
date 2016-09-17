package com.egenchallenge.emulator.repository;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egenchallenge.emulator.domain.Metric;


/**
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

	public List<Metric> findAll() {
	    return dataStore.find(Metric.class).asList();
	}

   public List<Metric> findMetricBetweenTimeRanges(String from, String to){
	   return dataStore.find(Metric.class).field("timeStamp").greaterThan(from).field("timeStamp").lessThan(to).asList();
   }
   
   public Metric findBaseMetricValue(){
	   return dataStore.find(Metric.class).get();
   }
}
