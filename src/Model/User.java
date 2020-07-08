package Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class User implements Person {

	public static final String userName_ = "";
	public static final String password_ = "";

	protected ArrayList<Product> products_;
	//protected SqlDb sqlDb_;
	protected TxtDb txtDb_;


	public User() throws IOException {

		//sqlDb_ =  new SqlDb();
		txtDb_ =  new TxtDb();
		
		products_ = readDataFromDataBase();
	}

	public String getType() {
		return "User";
	}

	public ArrayList<Product> getAllProducts() throws IOException {
		products_ = readDataFromDataBase();
		return products_;
	}

	public void updateProduct(Product product) throws IOException, SQLException {

		txtDb_.updateProductInDb(product);

		products_ = readDataFromDataBase();
	}

	public ArrayList<Product> readDataFromDataBase() throws IOException {

		return txtDb_.getAllProductsFromDb();
	}

	public Product getProductById(int id) throws NumberFormatException , IOException ,SQLException {
		return txtDb_.getProductByProductId(id);

	}

	public HashMap<String, Integer> getAllCatagoriesNames() {

		HashMap<String, Integer> catagoriesMap = new HashMap<String, Integer>();

		for (int i = 0; i < products_.size(); i++) {
			Product p = products_.get(i);
			catagoriesMap.put(p.getCategory(), 1);
		}

		return catagoriesMap;

	}

	public ArrayList<Product> getAllOfCatagory(String catagory) {

		ArrayList<Product> productsOfCatagory = new ArrayList<Product>();

		for (int i = 0; i < products_.size(); i++) {

			if (catagory.equals(products_.get(i).getCategory())) {

				productsOfCatagory.add(products_.get(i));
			}
		}

		return productsOfCatagory;
	}

}