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
 * ChargeCodes generated by hbm2java
 */
@Entity
@Table(name = "charge_codes", catalog = "servicecalls")
public class ChargeCodes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chargeCode;
	private String chargeDescription;
	private Set<ServiceRequestCalls> serviceRequestCallses = new HashSet<ServiceRequestCalls>(0);

	public ChargeCodes() {
	}

	public ChargeCodes(String chargeCode, String chargeDescription) {
		this.chargeCode = chargeCode;
		this.chargeDescription = chargeDescription;
	}

	public ChargeCodes(String chargeCode, String chargeDescription, Set<ServiceRequestCalls> serviceRequestCallses) {
		this.chargeCode = chargeCode;
		this.chargeDescription = chargeDescription;
		this.serviceRequestCallses = serviceRequestCallses;
	}

	@Id

	@Column(name = "charge_code", unique = true, nullable = false, length = 50)
	public String getChargeCode() {
		return this.chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	@Column(name = "charge_description", nullable = false)
	public String getChargeDescription() {
		return this.chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chargeCodes")
	@JsonIgnore
	public Set<ServiceRequestCalls> getServiceRequestCallses() {
		return this.serviceRequestCallses;
	}

	public void setServiceRequestCallses(Set<ServiceRequestCalls> serviceRequestCallses) {
		this.serviceRequestCallses = serviceRequestCallses;
	}

}
