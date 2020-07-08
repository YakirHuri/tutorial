package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import View.ShowProductsView;
import java.sql.*;


public class Model  {
	
	public User user_;
	
	public Model(String type) throws IOException {		
		
		 UserFactory userFactory = new UserFactory();
		 user_ = userFactory.getUser(type);
	}
	
	public ArrayList<Product> getAllProducts() throws IOException {

		return user_.getAllProducts();
	}
	
	public void updateProduct(Product product) throws IOException, SQLException  {
		
		user_.updateProduct(product);
	}
	
	public ArrayList<Product> readDataFromDataBase() throws IOException {		
		
		return user_.readDataFromDataBase();
	}


	public Product getProductById(int id) throws SQLException, NumberFormatException, IOException {
		
		return user_.getProductById(id);
	}
	
	public  HashMap<String, Integer> getAllCatagoriesNames(){
		
		return user_.getAllCatagoriesNames();
		
	}
	
	public ArrayList<Product> getAllOfCatagory(String catagory){
		
		return user_. getAllOfCatagory(catagory);
	}
	
	public void addProduct(Product p) throws IOException, SQLException {		
		
		if( user_.getType() == "Manager") {
			((Manager)user_).addProduct(p);
		}
	}
	
	public void removeProductById(int id) throws SQLException, IOException {
		
		if( user_.getType() == "Manager") {
			((Manager)user_).removeProductById(id);
		}
		
	}
	
		
}
