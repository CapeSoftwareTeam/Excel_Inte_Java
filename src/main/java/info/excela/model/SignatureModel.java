package info.excela.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Signature")
public class SignatureModel {

	@Id 
	@GeneratedValue(strategy= GenerationType.AUTO)
	
	@Column(name ="Sign_id")
	private Integer signId;
	
	@Column(name = "person_InCharge")
	private String personIncharge;
	
	@Column (name= "company_name")
	private String companyName;
	
	@Column(name = "role")
	private String role;
	
	@Column (name = "manager_name")
	private String managerName;

	public Integer getSignId() {
		return signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	public String getPersonIncharge() {
		return personIncharge;
	}

	public void setPersonIncharge(String personIncharge) {
		this.personIncharge = personIncharge;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public void setColumnIndex(int colIndex) {
		// TODO Auto-generated method stub
		
	}
	
	public String getColumnName() {
		return null;
	}
}

