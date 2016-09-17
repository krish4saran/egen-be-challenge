package com.egenchallenge.emulator.repository;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egenchallenge.emulator.domain.Alert;

/**This {@link Repository} is used for CRUD operations related to {@link Alert} document 
 * against alerts collection
 * @author saranjithkrishnan
 *
 */
@Repository
public class AlertDAORepository extends BasicDAO<Alert, String>{

	@Autowired
	private Datastore dataStore;
	
	protected AlertDAORepository(Datastore ds) {
		super(ds);
	}

	/**List all {@link Alert} records (documents) available in the alerts collections
	 * @return
	 */
	public List<Alert> findAll() {
	    return dataStore.find(Alert.class).asList();
	}

	/**List all {@link Alert} records (documents) avaialable between two timestamps
	 * @param from
	 * @param to
	 * @return
	 */
	public List<Alert> findAlertBetweenTimeRanges(String from, String to){
	   return dataStore.find(Alert.class).field("timeStamp").greaterThan(from).field("timeStamp").lessThan(to).asList();
	}
}
