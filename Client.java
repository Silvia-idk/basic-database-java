public class Client {

	 private int idClient;
	    private String clientName;
	    private String clientSurname;
	    private String phoneNumber;

	    public Client(int idClient, String clientName, String clientSurname, String phoneNumber) {
			this.idClient = idClient;
			this.clientName = clientName;
			this.clientSurname = clientSurname;
			this.phoneNumber = phoneNumber;
		}
		
		//toString
		public String toString() {
			return this.idClient + " " + this.clientName + " " + this.clientSurname + " " + this.phoneNumber;
		}
		
		public int getIdClient() {
			return idClient;
		}
		public void setIdClient(int idClient) {
			this.idClient = idClient;
		}
		
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		
		public String getClientSurname() {
			return clientSurname;
		}
		public void setClientSurname(String clientSurname) {
			this.clientSurname = clientSurname;
		}
		
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
}
