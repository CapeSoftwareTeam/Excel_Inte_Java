package info.excela.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Client")
public class ClientModel {

	 @Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
		
		@Column (name="Client_id")
		private Integer clientId;
	 
	    @Column (name = "Client_name")
	    private String clientName;
	 
	    @Column (name = "Site_Name")
	    private String siteName;
	    
	    @Column (name ="State")
	    private String state;
	    
	    public Integer getClientId() {
			return clientId;
		}

		public void setClientId(Integer clientId) {
			this.clientId = clientId;
		}

		public String getClientName() {
			return clientName;
		}

		public void setClientName(String clientName) {
			this.clientName = clientName;
		}

		public String getSiteName() {
			return siteName;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public void setColumnIndex(int colIndex) {
			// TODO Auto-generated method stub
			
		}
		
		public String getColumnName() {
			return null;
		}
}
