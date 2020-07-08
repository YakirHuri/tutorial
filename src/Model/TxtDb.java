package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/// database cols:
//		productId
//		productName
//		category
//		costPrice
//		sellingPrice
//		amount
//	

public class TxtDb {

	String pathDatabase_ = "C:\\Users\\yakir\\eclipse-workspace\\ProjectHit\\database.csv";

	public TxtDb() throws IOException {
		
		File databaseFile = new File(pathDatabase_);
		if (!(databaseFile.exists())){
			BufferedWriter writer = new BufferedWriter(new FileWriter(databaseFile));
		}
	}

	ArrayList<Product> getAllProductsFromDb() throws IOException {

		ArrayList<Product> products = new ArrayList<Product>();
		
        BufferedReader br = new BufferedReader(new FileReader(pathDatabase_));
        String line = "";
        while ((line = br.readLine()) != null) {
			String[] productLine = line.split(",");
			int currentId = Integer.parseInt(productLine[0]); 
			String currentName = productLine[1];
			String currentCategory = productLine[2];;
			int costPrice = Integer.parseInt(productLine[3]);
			int sellingPrice = Integer.parseInt(productLine[4]);
			int amount = Integer.parseInt(productLine[5]);
			Product product = new Product(currentId, currentName, currentCategory, costPrice, sellingPrice, amount);
			
			products.add(product);
	
		}
        
        br.close();

		return products;
	}

	public void addProductToDb(Product p) throws IOException {

		FileWriter fileWriter = new FileWriter(pathDatabase_,true); 
		StringBuilder sb = new StringBuilder();
	    sb.append(String.valueOf(p.getProductID()));
	    sb.append(',');
	    sb.append(p.getName());
	    sb.append(',');	
	    sb.append(p.getCategory());
	    sb.append(',');
	    sb.append(String.valueOf(p.getCostPrice()));
	    sb.append(',');
	    sb.append(String.valueOf(p.getSellingPrice()));
	    sb.append(',');
	    sb.append(String.valueOf(p.getAmount()));
	    sb.append('\n');
	    
	    fileWriter.write(sb.toString());
	    fileWriter.close();
	}

	public Product getProductByProductId(int id) throws NumberFormatException, IOException   {

        BufferedReader br = new BufferedReader(new FileReader(pathDatabase_));

		Product product = null;

		String line = "";
        while ((line = br.readLine()) != null) {
			String[] productLine = line.split(",");
			int currentId = Integer.parseInt(productLine[0]); 
			String currentName = productLine[1];
			String currentCategory = productLine[2];;
			int costPrice = Integer.parseInt(productLine[3]);
			int sellingPrice = Integer.parseInt(productLine[4]);
			int amount = Integer.parseInt(productLine[5]);

			if (currentId == id ) {
				 product = new Product(currentId, currentName, currentCategory, costPrice, sellingPrice, amount);

				 br.close();
				 return product;
			}
	
		}
        
        br.close();

		return product;

	}

	public void updateProductInDb(Product product) throws IOException  {

		ArrayList<Product> products = getAllProductsFromDb();
		for(int i =0; i <products.size(); i++) {
			
			if (products.get(i).getProductID() == product.getProductID()) {
				
				products.get(i).setAmount(product.getAmount());
				products.get(i).setSellingPrice(product.getSellingPrice());
				products.get(i).setCategory(product.getCategory());
				products.get(i).setCostPrice(product.getCostPrice());
				products.get(i).setProductName(product.getName());
			}
		}
		
		FileWriter fileWriter = new FileWriter(pathDatabase_,false);
		for(int i =0; i < products.size(); i++ ) {
			Product p = products.get(i);
			
			StringBuilder sb = new StringBuilder();
		    sb.append(String.valueOf(p.getProductID()));
		    sb.append(',');
		    sb.append(p.getName());
		    sb.append(',');	
		    sb.append(p.getCategory());
		    sb.append(',');
		    sb.append(String.valueOf(p.getCostPrice()));
		    sb.append(',');
		    sb.append(String.valueOf(p.getSellingPrice()));
		    sb.append(',');
		    sb.append(String.valueOf(p.getAmount()));
		    sb.append('\n');
		    fileWriter.write(sb.toString());
		}
		
	    fileWriter.close();

		System.out.println("succ to update product !! ");
	}

	public void removeProductById(int id) throws IOException  {
		
		ArrayList<Product> products = getAllProductsFromDb();
		
		FileWriter fileWriter = new FileWriter(pathDatabase_,false);
		for(int i =0; i < products.size(); i++ ) {
			Product p = products.get(i);
			if ( ! (p.getProductID() == id) ) {
				StringBuilder sb = new StringBuilder();
			    sb.append(String.valueOf(p.getProductID()));
			    sb.append(',');
			    sb.append(p.getName());
			    sb.append(',');	
			    sb.append(p.getCategory());
			    sb.append(',');
			    sb.append(String.valueOf(p.getCostPrice()));
			    sb.append(',');
			    sb.append(String.valueOf(p.getSellingPrice()));
			    sb.append(',');
			    sb.append(String.valueOf(p.getAmount()));
			    sb.append('\n');
			    fileWriter.write(sb.toString());
			}
			
		}
		
	    fileWriter.close();

		System.out.println("succ to remove product !! ");

	}

}
