package Model;

public class Product {
	
	private int productID_;
	private String name_;
	private String category_;
	private int costPrice_;
	private int sellingPrice_;
	private int amount_;
	
	public Product(int productID, String name,  
				String category, int costPrice, int sellingPrice, int amount) {
		
		 this.productID_ = productID;
		 this.name_ = name;
		 this.category_ = category;
		 this.costPrice_ = costPrice;
		 this.sellingPrice_ = sellingPrice;
		 this.amount_ = amount;
	}
	
	public int getProductID() {
        return this.productID_;
    }
 
    public void setProductID(int productID) {
        this.productID_ = productID;
    }
    
    public String getName() {
        return this.name_;
    }
 
    public void setProductName(String name) {
        this.name_ = name;
    }  
    
    
    public String getCategory() {
        return this.category_;
    }
 
    public void setCategory(String category) {
        this.category_ = category;
    }
    
    public int getCostPrice() {
        return this.costPrice_;
    }
 
    public void setCostPrice(int costPrice) {
        this.costPrice_ = costPrice;
    }
    
    public int getSellingPrice() {
        return this.sellingPrice_;
    }
 
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice_ = sellingPrice;
    }
    
    public int getAmount() {
        return this.amount_;
    }
 
    public void setAmount(int amount) {
        this.amount_ = amount;
    }

}
