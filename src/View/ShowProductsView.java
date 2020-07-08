package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Model.Product;
import Model.Model;
import View.ProductTableModel.ProductAmountCellRenderer;


public class ShowProductsView extends JFrame {
	
	JButton SearchByIdButton_ ;
	
	JButton showAllProductsButton_ ;
	
	JButton backButton_ ;
	
	JComboBox<String> catagoriesListComboBox_;
	
	JPanel filterPanel_ ;
	
	public JTextField filter_;
	
	JPanel ctrlPane_;
	
	JTable table_;
	
	JScrollPane tableScrollPane_;
	
	JSplitPane splitPane_;
	
	public ShowProductsView() {	
		
		this.setTitle("filter screen");	
		
		
		SearchByIdButton_ = new JButton("Search by ID");
		showAllProductsButton_ = new JButton("Show all Products");
		backButton_ =  new JButton("back");
		
		catagoriesListComboBox_= new JComboBox<>();
		
		filterPanel_ = new JPanel();
		
		filter_ = new JTextField(10);
		filterPanel_.add(filter_);
		filterPanel_.add(SearchByIdButton_);
		filterPanel_.add(showAllProductsButton_);		
		filterPanel_.add(catagoriesListComboBox_);
		filterPanel_.add(backButton_);

		
        ctrlPane_ = new JPanel();
        ctrlPane_.add(filterPanel_);
		
		 // create our own custom TableModel
	     ProductTableModel productModel = new ProductTableModel();
	     table_ = new JTable(productModel);
	     
	     for (int i =0; i< productModel.getColumnCount();i++) {
	    	 ProductAmountCellRenderer pRender =   new ProductAmountCellRenderer();
	    	 table_.setDefaultRenderer(productModel.getColumnClass(i), pRender);
	      }

	  
	      // create the scroll pane and add the table to it.
	     tableScrollPane_ = new JScrollPane(table_);  
	      
	      
        tableScrollPane_.setPreferredSize(new Dimension(700, 400));
        tableScrollPane_.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Products table",
				TitledBorder.CENTER, TitledBorder.TOP));

		splitPane_ = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane_, tableScrollPane_);
		splitPane_.setDividerLocation(35);
		splitPane_.setEnabled(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(splitPane_);
		this.pack();
		this.setLocationRelativeTo(null);
	  
	      addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent e) {
	            System.exit(0);
	         }
	      });
		
	      
	     this.pack();

    }
	
	public void addSearchListener(ActionListener listenForButton) {
		
		SearchByIdButton_.addActionListener(listenForButton);
	}
	
	public void addBackListener(ActionListener listenForButton) {
		
		backButton_.addActionListener(listenForButton);
	}
	
	public void addShowAllProductsListener(ActionListener listenForButton) {
		
		showAllProductsButton_.addActionListener(listenForButton);
	}
	
	public void addComboBoxCatagoryListener(ItemListener listenForComboBox) {
		
		catagoriesListComboBox_.addItemListener(listenForComboBox);
	}
	
	public void setAllProductsOnTable(ArrayList<Product> products) {
		
		 AbstractTableModel model = (AbstractTableModel) table_.getModel();
		
		 ProductTableModel productModel = new ProductTableModel();	     
	     
		 for(int i =0; i < products.size(); i++) {
			 Product p = products.get(i);
			 productModel.addProduct(p);
		 }
	     
	      model = productModel;
	      table_.setModel(model);			
		
	}
	
	public void setAllCatagoriesInComboBox(HashMap<String, Integer> catagoriesMap) {
		
		ArrayList<String> catagoriesNamesTitles = new ArrayList<String>();
		int count =0;
		for (String i : catagoriesMap.keySet()) {
			catagoriesNamesTitles.add(i);
			count++;
		}
		
		catagoriesListComboBox_.setModel(new DefaultComboBoxModel(catagoriesNamesTitles.toArray()));
	}
	
	public void showPorductById(Product product) {
		
		 AbstractTableModel model = (AbstractTableModel) table_.getModel();
		 
		 if (product != null) {
			 
			 ProductTableModel productModel = new ProductTableModel();	     
		     
			 productModel.addProduct(product);
		     
		      model = productModel;
		      table_.setModel(model);
			 
		 } else {
			 
			 JOptionPane.showMessageDialog(null, "product not exsist",
					 "InfoBox: " + "Msg", JOptionPane.INFORMATION_MESSAGE);
		 }
		 
	}
	
	public void setProductsCatagoryOnTable(ArrayList<Product> productsCategory) {
		
		 AbstractTableModel model = (AbstractTableModel) table_.getModel();
			
		 ProductTableModel productModel = new ProductTableModel();	     
	     
		 for(int i =0; i < productsCategory.size(); i++) {
			 Product p = productsCategory.get(i);
			 productModel.addProduct(p);
		 }
	     
	     model = productModel;
	     table_.setModel(model);
		
	}
	

	
}

class ProductTableModel extends AbstractTableModel {
  // holds the strings to be displayed in the column headers of our table
  final String[] columnNames = {"Product Id", "name", "category", "cost price", "selling price", "amount"};
 
  // holds the data types for all our columns
  final Class[] columnClasses = {Integer.class, String.class, String.class, 
		  						Integer.class, Integer.class, Integer.class};
 
  // holds our data
  final Vector data = new Vector();
  
  // adds a row
  public void addProduct(Product w) {
     data.addElement(w);
     fireTableRowsInserted(data.size()-1, data.size()-1);
  }
 
  public int getColumnCount() {
     return columnNames.length;
  }
         
  public int getRowCount() {
     return data.size();
  }
 
  public String getColumnName(int col) {
     return columnNames[col];
  }
  
  public Class getColumnClass(int c) {
      return columnClasses[c];
   }


  public Object getValueAt(int row, int col) {
	 Product product = (Product) data.elementAt(row);
     if (col == 0)      return product.getProductID();
     else if (col == 1) return product.getName();
     else if (col == 2) return product.getCategory();
     else if (col == 3) return product.getCostPrice();
     else if (col == 4) return product.getSellingPrice();
     else if (col == 5) return product.getAmount();
     else return null;
  }
 
  public boolean isCellEditable(int row, int col) {
     return false;
  }
  
  public static class ProductAmountCellRenderer extends DefaultTableCellRenderer {
	   public Component getTableCellRendererComponent(
	            JTable table, Object value, boolean isSelected,
	            boolean hasFocus, int row, int column)
	   {
		   final int Minimum_Amount_Requirement = 30;

		   ProductTableModel wtm = (ProductTableModel) table.getModel();
	       int amount = (int) wtm.getValueAt(row, 5);
	  
	      if (amount < Minimum_Amount_Requirement) {
	         setBackground(Color.red);
	      }
	      else {
	         setBackground(Color.white);
	      }
	  
	      return super.getTableCellRendererComponent(table, value, isSelected,
	                                                 hasFocus, row, column);
	   }
	}  
  
  
}