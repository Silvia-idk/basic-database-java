public class Warehouse {

	 	private int idProduct;
	    private String category;
	    private String productName;
	    private int idClient;

	    public Warehouse(int idProduct, String category, String productName, int idClient) {
			this.idProduct = idProduct;
			this.productName = productName;
			this.category = category;
			this.idClient = idClient;
		}
		
		//toString
		public String toString() {
			return this.idProduct + " " + this.category + " " + this.productName + " " + this.idClient;
		}
		
		public int getIdProduct() {
			return idProduct;
		}
		public void setIdProduct(int idProduct) {
			this.idProduct = idProduct;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		
		public int getIdClient() {
			return idClient;
		}
		public void setIdClient(int idClient) {
			this.idClient = idClient;
		}
}
