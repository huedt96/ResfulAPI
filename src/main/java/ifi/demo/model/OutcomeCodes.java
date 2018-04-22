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
 * OutcomeCodes generated by hbm2java
 */
@Entity
@Table(name = "outcome_codes", catalog = "servicecalls")
public class OutcomeCodes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String outcomeCode;
	private String egProblemFixed;
	private String outcomeDescription;
	private Set<ServiceRequestActions> serviceRequestActionses = new HashSet<ServiceRequestActions>(0);

	public OutcomeCodes() {
	}

	public OutcomeCodes(String outcomeCode, String egProblemFixed, String outcomeDescription) {
		this.outcomeCode = outcomeCode;
		this.egProblemFixed = egProblemFixed;
		this.outcomeDescription = outcomeDescription;
	}

	public OutcomeCodes(String outcomeCode, String egProblemFixed, String outcomeDescription,
			Set<ServiceRequestActions> serviceRequestActionses) {
		this.outcomeCode = outcomeCode;
		this.egProblemFixed = egProblemFixed;
		this.outcomeDescription = outcomeDescription;
		this.serviceRequestActionses = serviceRequestActionses;
	}

	@Id

	@Column(name = "outcome_code", unique = true, nullable = false, length = 50)
	public String getOutcomeCode() {
		return this.outcomeCode;
	}

	public void setOutcomeCode(String outcomeCode) {
		this.outcomeCode = outcomeCode;
	}

	@Column(name = "eg_problem_fixed", nullable = false, length = 50)
	public String getEgProblemFixed() {
		return this.egProblemFixed;
	}

	public void setEgProblemFixed(String egProblemFixed) {
		this.egProblemFixed = egProblemFixed;
	}

	@Column(name = "outcome_description", nullable = false)
	public String getOutcomeDescription() {
		return this.outcomeDescription;
	}

	public void setOutcomeDescription(String outcomeDescription) {
		this.outcomeDescription = outcomeDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "outcomeCodes")
	@JsonIgnore
	public Set<ServiceRequestActions> getServiceRequestActionses() {
		return this.serviceRequestActionses;
	}

	public void setServiceRequestActionses(Set<ServiceRequestActions> serviceRequestActionses) {
		this.serviceRequestActionses = serviceRequestActionses;
	}

}
