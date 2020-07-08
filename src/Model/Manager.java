package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Manager extends  User {

	public Manager( )  throws IOException {	
	
	}
	
	public String getType() {
		return "Manager";
	}
	
	public void removeProductById(int id) throws SQLException, IOException {
		
		txtDb_.removeProductById(id);
	}
	
	public void addProduct(Product p) throws IOException, SQLException {		
		
		txtDb_.addProductToDb(p);
		products_ = txtDb_.getAllProductsFromDb();	

	}

}
