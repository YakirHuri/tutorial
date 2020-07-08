package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlDb {

	private String url = "jdbc:mysql://localhost:3306/productsdb?serverTimezone=UTC";
	private String user = "root";
	private String password = "YH31keva";

	private Connection myConn = null;

	public SqlDb() throws IOException {

		try {
			myConn = DriverManager.getConnection(url, user, password);

			System.out.println("connection to db is good");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ArrayList<Product> getAllProductsFromDb() {

		ArrayList<Product> products = new ArrayList<Product>();
		String query = "SELECT * FROM productsdb.products";
		try {
			Statement myStmt = myConn.createStatement();
			ResultSet rs = myStmt.executeQuery(query);

			while (rs.next()) {

				int currentId = rs.getInt("product_id");
				String currentName = rs.getString("name");
				String currentCategory = rs.getString("category");
				int costPrice = rs.getInt("cost_price");
				int sellingPrice = rs.getInt("selling_price");
				int amount = rs.getInt("amount");

				Product product = new Product(currentId, currentName, currentCategory, costPrice, sellingPrice, amount);

				products.add(product);

			}
			myStmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public void addProductToDb(Product p) throws IOException, SQLException {

		Statement myStmt = myConn.createStatement();

		String productId = String.valueOf(p.getProductID());
		String category = p.getCategory();
		String name = p.getName();
		String cost_price = String.valueOf(p.getCostPrice());
		String selling_price = String.valueOf(p.getSellingPrice());
		String amount = String.valueOf(p.getAmount());

		String query = "INSERT INTO `productsdb`.`products` (`product_id`, `category`, `name`, `cost_price`, `selling_price`, `amount`) VALUES "
				+ "('" + productId + "', '" + category + "', '" + name + "', '" + cost_price + "', '" + selling_price
				+ "', '" + amount + "')";

		System.out.println("succ to add product !!");

		myStmt.executeUpdate(query);

	}

	public Product getProductByProductId(int id) throws SQLException {

		Statement myStmt = myConn.createStatement();
		String query = "SELECT name, category, cost_price, selling_price, amount FROM productsdb.products WHERE product_id = "
				+ String.valueOf(id);
		Product product = null;
		try {

			ResultSet rs = myStmt.executeQuery(query);

			while (rs.next()) {

				int currentId = id;
				String currentName = rs.getString("name");
				String currentCategory = rs.getString("category");
				int costPrice = rs.getInt("cost_price");
				int sellingPrice = rs.getInt("selling_price");
				int amount = rs.getInt("amount");

				product = new Product(currentId, currentName, currentCategory, costPrice, sellingPrice, amount);
				System.out.println("product found in db");
				return product;

			}
			myStmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;

	}

	public void updateProductInDb(Product product) throws SQLException {

		String productId = "'" + String.valueOf(product.getProductID()) + "'";
		String category = "'" + product.getCategory() + "'";
		String name = "'" + product.getName() + "'";
		String cost_price = "'" + String.valueOf(product.getCostPrice()) + "'";
		String selling_price = "'" + String.valueOf(product.getSellingPrice()) + "'";
		String amount = "'" + String.valueOf(product.getAmount()) + "'";

		Statement myStmt = myConn.createStatement();

		String queryName = "UPDATE `productsdb`.`products` SET `name` = " + name + " WHERE (`product_id` = " + productId
				+ ")";
		String queryCategory = "UPDATE `productsdb`.`products` SET `category` = " + category + " WHERE (`product_id` = "
				+ productId + ")";
		String queryCostPrice = "UPDATE `productsdb`.`products` SET `cost_price` = " + cost_price
				+ " WHERE (`product_id` = " + productId + ")";
		String querySellingPrice = "UPDATE `productsdb`.`products` SET `selling_price` = " + selling_price
				+ " WHERE (`product_id` = " + productId + ")";
		String queryAmount = "UPDATE `productsdb`.`products` SET `amount` = " + amount + " WHERE (`product_id` = "
				+ productId + ")";

		myStmt.executeUpdate(queryName);
		myStmt.executeUpdate(queryCategory);
		myStmt.executeUpdate(queryCostPrice);
		myStmt.executeUpdate(querySellingPrice);
		myStmt.executeUpdate(queryAmount);

		System.out.println("succ to update product !! ");
	}

	public void removeProductById(int id) throws SQLException {

		Statement myStmt = myConn.createStatement();

		String productId = "'" + String.valueOf(id) + "'";
		String query = "DELETE FROM `productsdb`.`products` WHERE (`product_id` = " + productId + ")";

		myStmt.executeUpdate(query);
		System.out.println("succ to remove product !! ");

	}

}
