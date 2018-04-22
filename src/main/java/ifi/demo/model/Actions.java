package ifi.demo.model;
// Generated Oct 24, 2017 5:59:32 PM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Actions generated by hbm2java
 */
@Entity
@Table(name = "actions", catalog = "servicecalls")
public class Actions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionCode;
	private String actionDescription;
	private String egChecktheCircuits;
	private Set<ServiceRequestActions> serviceRequestActionses = new HashSet<ServiceRequestActions>(0);

	public Actions() {
	}

	public Actions(String actionCode, String actionDescription, String egChecktheCircuits) {
		this.actionCode = actionCode;
		this.actionDescription = actionDescription;
		this.egChecktheCircuits = egChecktheCircuits;
	}

	public Actions(String actionCode, String actionDescription, String egChecktheCircuits,
			Set<ServiceRequestActions> serviceRequestActionses) {
		this.actionCode = actionCode;
		this.actionDescription = actionDescription;
		this.egChecktheCircuits = egChecktheCircuits;
		this.serviceRequestActionses = serviceRequestActionses;
	}

	@Id

	@Column(name = "action_code", unique = true, nullable = false, length = 50)
	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Column(name = "action_description", nullable = false)
	public String getActionDescription() {
		return this.actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	@Column(name = "eg_checkthe_circuits", nullable = false, length = 50)
	public String getEgChecktheCircuits() {
		return this.egChecktheCircuits;
	}

	public void setEgChecktheCircuits(String egChecktheCircuits) {
		this.egChecktheCircuits = egChecktheCircuits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actions")
	@JsonIgnore
	public Set<ServiceRequestActions> getServiceRequestActionses() {
		return this.serviceRequestActionses;
	}

	public void setServiceRequestActionses(Set<ServiceRequestActions> serviceRequestActionses) {
		this.serviceRequestActionses = serviceRequestActionses;
	}

}