import java.util.*;
import java.sql.*;
/*in mySQL create a client' table and a warehouse, table
    and download a mysql connector
    In this specific case in your SQL 
    host name = localhost
    port = 3306
    schema = database
    user = root
    password = Database
    tables:
    clients = idClient(AI), clientName, clientSurname, phoneNumber)
    warehouse(idProduct(AI), category, productName, idClient)
*/ 
public class Database {
	//main
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		List<Client> clientList = new ArrayList<>();
		List<Warehouse> inventoryList = new ArrayList<>();
		int idClient = 0;
	 	String clientName = "";
	 	String clientSurname = "";
	 	String phoneNumber = "";
	 	
	 	int idProduct = 0;
	    String category = "";
	    String productName = "";
	    
	    String choice = "";
	    
	    
	    
	    while(!choice.equals("Q")) {

	 		System.out.println("\nDATABASE\n");
	 		System.out.print("(G)et clients' list, (F)ind client, (A)dd new client, (D)elete client, (U)pdate, (Q)uit  ");
	 		System.out.print(",Get (I)inventory, (AD)d new product, (FI)nd product, (DE)lete product  ");
	 		System.out.println(" ");
	 		choice = s.nextLine().toUpperCase().trim();

	 		switch(choice) {
	 			case "G":
	 				System.out.println("\nClients List : ");
	 				clientList = getAllClients();
	 				for (Client c : clientList) {
	 					System.out.println(c);
	 				}
	 				break;
	 			case "I":
	 				System.out.println("\nInventory : ");
	 				inventoryList = getInventory();
	 				for (Warehouse p : inventoryList) {
	 					System.out.println(p);
	 				}
	 				break;
	 			case "F":
	 				System.out.print("\nType the client ID : ");
	    			idClient = s.nextInt();
	    			String garbage = s.nextLine();
	    			getClientFromID(idClient);
		    	    break;
	 			case "FI":
	 				System.out.print("\nType the product ID : ");
	    			idProduct = s.nextInt();
	    			garbage = s.nextLine();
	    			getProductFromID(idProduct);
		    	    break;
	 			case "A":
	 				System.out.print("\nType the new client personal data : ");
	    		 	System.out.print("\nName : ");
	    		 	clientName = s.nextLine();
	    		 	System.out.print("Surname : ");
	    		 	clientSurname = s.nextLine();
	    		 	System.out.print("Phone number : ");
	    		 	phoneNumber = s.nextLine();	
	    			addClient(clientName, clientSurname, phoneNumber);
		    	    break;
	 			case "AD":
	 				System.out.print("\nType the new product data : ");
	    		 	System.out.print("\nCategory : ");
	    		 	category = s.nextLine();
	    		 	System.out.print("Name : ");
	    		 	productName = s.nextLine();
	    		 	System.out.print("Client ID : ");
	    		 	idClient = s.nextInt();
	    		 	garbage = s.nextLine();
	    			addProduct(category, productName, idClient);
		    	    break;
	 			case "D":
	    		    System.out.print("\nType the client ID : ");
	    			idClient = s.nextInt();
	    			garbage = s.nextLine();
	    			deleteClient( idClient);
		    	    break;
	 			case "DE":
	    		    System.out.print("\nType the product ID : ");
	    			idProduct = s.nextInt();
	    			garbage = s.nextLine();
	    			deleteProduct( idProduct);
		    	    break;
	 			case "U":
	 				String c = "";
	 				System.out.print("\nClient ID to update : ");
	 				idClient = s.nextInt();
	 				garbage = s.nextLine();
	 				System.out.print("\nUpdate (N)ame, (S)urname, (P)hone number  ");
	 				c = s.nextLine().toUpperCase().trim();
	 				switch(c) {
	 					case "N":
		    		    	System.out.print("\nNew name : ");
			    		 	clientName = s.nextLine();
			    		 	updateClientName(idClient,clientName);
		    		 		break;
		    		    case "S":
		    		    	System.out.print("\nNew surname : ");
			    		 	clientSurname = s.nextLine();
			    		 	updateClientSurname(idClient,clientSurname);
			    		 	break;
		    		    case "P":
		    		    	System.out.print("\nNew phone number : ");
			    		 	phoneNumber = s.nextLine();
			    		 	updatePhoneNumber(idClient,phoneNumber);
			    		 	break;
		    		    default:
		    		    	System.out.println ("Error");
	 				}//end switch
	 				break;
	 			case "Q":
	 				System.out.println ("\nGoodbye");
	 				s.close();
	 				break;
	 			default:
	 				System.out.println ("Error");
	 		}
	 	}
	}//end main
	
	//getAllClients method
	public static List<Client> getAllClients() {
		List<Client> clientList = new ArrayList<>();
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "SELECT * FROM database.clients"; 
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Client newClient = new Client(rs.getInt("idClient"), rs.getString("clientName"), rs.getString("clientSurname"), rs.getString("phoneNumber"));
				clientList.add(newClient);
			}
			return clientList;
		}
		catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}//end getAllClients()
	
	//getInventory method
		public static List<Warehouse> getInventory() {
			List<Warehouse> inventoryList = new ArrayList<>();
			try {
				Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
                String querySQL = "SELECT * FROM database.warehouse"; 
                PreparedStatement ps = DBCon.prepareStatement(querySQL);
                ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Warehouse newProduct = new Warehouse(rs.getInt("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("idClient"));
					inventoryList.add(newProduct);
				}
				return inventoryList;
			}
			catch(SQLException e) {
				System.out.println(e);
				return null;
			}
		}//end getInventory()

	//getClientFromID method
	public static void getClientFromID(int idClient) {
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "SELECT * FROM database.clients WHERE idClient = ?";
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			ps.setInt(1, idClient);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Client newClient = new Client(rs.getInt("idClient"), rs.getString("clientName"), rs.getString("clientSurname"), rs.getString("phoneNumber"));
				System.out.println(newClient);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}//end getClientFromID
	
	//getProductFromID method
		public static void getProductFromID(int idProduct) {
			try {
				Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
				String querySQL = "SELECT * FROM database.warehouse WHERE idProduct = ?";
				PreparedStatement ps = DBCon.prepareStatement(querySQL);
				ps.setInt(1, idProduct);
				ResultSet rs = ps.executeQuery();

				while(rs.next()){
					Warehouse newProduct = new Warehouse(rs.getInt("idProduct"), rs.getString("category"), rs.getString("productName"), rs.getInt("idClient"));
					System.out.println(newProduct);
				}
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}//end getProductFromID
		
	//addClient method
	public static void addClient(String clientName, String clientSurname, String phoneNumber) {
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "INSERT INTO clients(clientName, clientSurname, phoneNumber) VALUES (?,?,?)";
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			//ps.setInt(1, idClient);
			ps.setString(1, clientName);
			ps.setString(2, clientSurname);
			ps.setString(3, phoneNumber);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}// end AddClient()
	
	//addProduct method
		public static void addProduct(String category, String productName, int idClient) {
			try {
				Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
				String querySQL = "INSERT INTO warehouse(category, productName, idClient) VALUES (?,?,?)";
				PreparedStatement ps = DBCon.prepareStatement(querySQL);
				//ps.setInt(1, idClient);
				ps.setString(1, category);
				ps.setString(2, productName);
				ps.setInt(3, idClient);
				ps.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}// end AddProduct()

	//deteteClient method
	public static void deleteClient(int idClient) {
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "DELETE FROM database.clients WHERE idclient = ?";
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			ps.setInt(1, idClient);						
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}// end deleteClient()
	
	//deteteProduct method
		public static void deleteProduct(int idProduct) {
			try {
				Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
				String querySQL = "DELETE FROM database.warehouse WHERE idproduct = ?";
				PreparedStatement ps = DBCon.prepareStatement(querySQL);
				ps.setInt(1, idProduct);						
				ps.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}// end deleteProduct()

	//updateClientName method
	public static void updateClientName(int idClient, String clientName) {
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "UPDATE database.clients SET clientName = ?  WHERE idClient = ?";
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			ps.setString(1, clientName);
			
			ps.setInt(2, idClient);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}// end updateClientName()

	//updateClientSurname method
	public static void updateClientSurname(int idClient, String clientSurname) {
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "UPDATE database.clients SET clientSurname = ?  WHERE idClient = ?";
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			ps.setString(1, clientSurname);						
			ps.setInt(2, idClient);							
			ps.executeUpdate();
		}
		catch(SQLException e) {	
			System.out.println(e);
		}
	}// end updateClientSurname()

	//updatePhoneNumber method
	public static void updatePhoneNumber(int idClient, String phoneNumber) {
		try {
			Connection DBCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Database");
			String querySQL = "UPDATE database.clients SET phoneNumber = ?  WHERE idClient = ?";
			PreparedStatement ps = DBCon.prepareStatement(querySQL);
			ps.setString(1, phoneNumber);						
			ps.setInt(2, idClient);							
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}// end updatePhoneNumber()

	//idGenerator method
	public static int idGenerator(List<Client> clientList) {
		return clientList.size() + 1;
	}//end idGenerator()
	
	
}//end
