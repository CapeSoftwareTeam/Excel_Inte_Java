package info.excela.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="register")
public class ExcelReadWriteModel {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	
	@Column (name="register_id")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer registerId;
//	
    @Column(name = "name")
    private String name;
   
//	@Column (name= "age")
//    private Integer age;
//    
//    @Column(name="mobile_number")
//    private Integer mobileNumber;
    
    @Column(name = "email")
    private String email;

    public Integer getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//
//	public Integer getMobileNumber() {
//		return mobileNumber;
//	}
//
//	public void setMobileNumber(Integer mobileNumber) {
//		this.mobileNumber = mobileNumber;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setColumnIndex(int colIndex) {
		// TODO Auto-generated method stub
		
	}
	
	public String getColumnName() {
		return null;
	}
	
}
