package com.egenchallenge.emulator.domain;
 
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;

import com.fasterxml.jackson.annotation.JsonIgnore;


 
/**Base entity class having common information
 * 
 * @author saranjithkrishnan
 *
 */
public abstract class BaseEntity {
 
    @Id
    @Property("id")
    @JsonIgnore
    protected ObjectId id;
 
    
    /**
     *Optional requirement - Also can use @AutoTimestamp for auto generation of created and updated dates
     */
    @Property("createdDate")
    @JsonIgnore
    private Date createdDate;
    
    @PrePersist
    public void persistCreatedDate(){
    	this.createdDate =  new Date();
    }
    
    /**Optionally indexed - since no functionality for fetching one record based on an input
     * 
     */
    @Indexed(name = "idx_timestamp", unique = true)
	@Property("timeStamp")
	private long timeStamp;
	
 
	

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public BaseEntity() {
        super();
    }
 
    public ObjectId getId() {
        return id;
    }
 
    public void setId(ObjectId id) {
        this.id = id;
    }
 
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	


    
 
}