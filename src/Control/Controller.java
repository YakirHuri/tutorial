package Control;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Product;
import Model.Model;
import View.LoginView;
import View.ShowAddNewProductView;
import View.ShowEditProductView;
import View.ShowHomePageView;
import View.ShowProductsView;

public class Controller {

	private ShowProductsView showProductsView_;
	private ShowAddNewProductView showAddNewProductView_;
	private ShowHomePageView showHomePageView_;
	private ShowEditProductView showEditProductView_;
	private LoginView loginView_;

	private Model model_;

	public Controller(ShowHomePageView showHomePageView, ShowProductsView showProductsView,
			ShowAddNewProductView showAddNewProductView, 
			ShowEditProductView showEditProductView, LoginView loginView,
			Model model) {
		
		//screens
		this.loginView_ = loginView;
		this.showHomePageView_ = showHomePageView;
		this.showProductsView_ = showProductsView;
		this.showAddNewProductView_ = showAddNewProductView;
		this.showEditProductView_ = showEditProductView;
		
		///model
		this.model_ = model;
		
		
		this.showHomePageView_.addShowProductsListener(new GoToShowProductsScreenListener());
		this.showHomePageView_.addAddNewProductsListener(new GoToAddProductScreenListener());
		this.showHomePageView_.addEditProductsListener(new GoToEditProductScreenListener());

		this.showProductsView_.addSearchListener(new SearchingListener());
		this.showProductsView_.addShowAllProductsListener(new ShowAllProductsListener());
		this.showProductsView_.addComboBoxCatagoryListener(new ShowProductsBySingleCatagory());
		this.showProductsView_.addBackListener(new BackListenerFromShowProductsView());

		this.showAddNewProductView_.addBackListener(new BackListenerFromAddProductView());
		this.showAddNewProductView_.addAddProductListener(new AddProductListener());

		this.showEditProductView_.addSearchProductListener(new SearchProductForEditing());
		this.showEditProductView_.addSaveEditedProductListener(new SaveEditedProduct());
		this.showEditProductView_.addBackProductListener(new BackListenerFromEditProductView());
		this.showEditProductView_.addRemoveProductListener(new removeProduct());
		this.loginView_.addConnectListener(new makeConnection());
		
		this.loginView_.setVisible(true);
		this.showHomePageView_.setVisible(false);
		this.showProductsView_.setVisible(false);
		this.showAddNewProductView_.setVisible(false);
		this.showEditProductView_.setVisible(false);
	}
	
	////// login page view ///////////////////////////////////////

	class makeConnection implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String userName = loginView_.userNametextField.getText();
			String password = loginView_.passwordtextField.getText();
			
			if(userName.equals("MANAGER") == true && password.equals("MANAGER") ) {
				
				try {
					model_ = new Model("Manager");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				final Rectangle bounds = loginView_.getBounds();
				showHomePageView_.setLocation(bounds.x, bounds.y);
				loginView_.setVisible(false);
				showHomePageView_.setVisible(true);
			} else if(userName.equals("SELLER") == true && password.equals("SELLER") ) {
				try {
					model_ = new Model("Seller");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				final Rectangle bounds = loginView_.getBounds();
				showHomePageView_.setLocation(bounds.x, bounds.y);
				loginView_.setVisible(false);
				showHomePageView_.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "שם משתמש או ססמא לא תקינים",
						password, JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	////// Home page view ///////////////////////////////////////
	class GoToShowProductsScreenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			ArrayList<Product> products = null;
			try {
				products = model_.getAllProducts();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			showProductsView_.setAllProductsOnTable(products);

			HashMap<String, Integer> catagoriesMap = model_.getAllCatagoriesNames();
			showProductsView_.setAllCatagoriesInComboBox(catagoriesMap);

			final Rectangle bounds = showHomePageView_.getBounds();
			showProductsView_.setLocation(bounds.x, bounds.y);

			showProductsView_.setVisible(true);
			showHomePageView_.setVisible(false);
		}

	}

	class GoToAddProductScreenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (model_.user_.getType().equals("Manager")) {

				final Rectangle bounds = showHomePageView_.getBounds();
				showAddNewProductView_.setLocation(bounds.x, bounds.y);
	
				showAddNewProductView_.setVisible(true);
				showHomePageView_.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "אין לך הרשאת מנהל",
						null, JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	class GoToEditProductScreenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			final Rectangle bounds = showHomePageView_.getBounds();
			showEditProductView_.setLocation(bounds.x, bounds.y);

			showEditProductView_.setVisible(true);
			showHomePageView_.setVisible(false);

			showEditProductView_.proudctNametextField_.setText("");
			;
			showEditProductView_.categoryTextField_.setText("");
			showEditProductView_.costPriceTextField_.setText("");
			showEditProductView_.sellingPriceTextField_.setText("");
			showEditProductView_.amountTextField_.setText("");
			showEditProductView_.proudctIdtextField_.setText("");

			showEditProductView_.setStateForAllTextFiles(false);
		}

	}

	////// show products view ///////////////////////////////////////

	class BackListenerFromShowProductsView implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			final Rectangle bounds = showProductsView_.getBounds();
			showHomePageView_.setLocation(bounds.x, bounds.y);

			showHomePageView_.setVisible(true);
			showProductsView_.setVisible(false);
		}
	}

	class SearchingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (showProductsView_.filter_.getText().matches("\\d+")) {
				String idS = showProductsView_.filter_.getText();
				int id = Integer.parseInt(idS);
				showProductsView_.filter_.setText("");
				Product product = null;
				try {
					product = model_.getProductById(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				showProductsView_.showPorductById(product);
			}

		}

	}

	class ShowAllProductsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			ArrayList<Product> products = null;
			try {
				products = model_.getAllProducts();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			showProductsView_.setAllProductsOnTable(products);
		}
	}

	class ShowProductsBySingleCatagory implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {

			// The item affected by the event.
			Object item = event.getItem();
			ArrayList<Product> productsCategory = model_.getAllOfCatagory(item.toString());
			showProductsView_.setProductsCatagoryOnTable(productsCategory);
		}
	}

	class AddProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (showAddNewProductView_.productIdTextField_.getText().matches("\\d+") == true
					&& showAddNewProductView_.costPriceTextField_.getText().matches("\\d+") == true
					&& showAddNewProductView_.sellingPriceTextField_.getText().matches("\\d+") == true
					&& showAddNewProductView_.amountTextField_.getText().matches("\\d+") == true
					&& showAddNewProductView_.productNameTextField_.getText() != ""
					&& showAddNewProductView_.productCategoryTextField_.getText() != "") {

				int id = Integer.parseInt(showAddNewProductView_.productIdTextField_.getText());
				String name = showAddNewProductView_.productNameTextField_.getText();
				String category = showAddNewProductView_.productCategoryTextField_.getText();
				int costPrice = Integer.parseInt(showAddNewProductView_.costPriceTextField_.getText());
				int sellingPrice = Integer.parseInt(showAddNewProductView_.sellingPriceTextField_.getText());
				int amount = Integer.parseInt(showAddNewProductView_.amountTextField_.getText());

				Product product = new Product(id, name, category, costPrice, sellingPrice, amount);

				try {
					model_.addProduct(product);
					showAddNewProductView_.productIdTextField_.setText("");				
					showAddNewProductView_.productNameTextField_.setText("");
					showAddNewProductView_.productCategoryTextField_.setText("");
					showAddNewProductView_.costPriceTextField_.setText("");
					showAddNewProductView_.sellingPriceTextField_.setText("");
					showAddNewProductView_.amountTextField_.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Product> products = null;
				try {
					products = model_.getAllProducts();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				showProductsView_.setAllProductsOnTable(products);

				HashMap<String, Integer> catagoriesMap = model_.getAllCatagoriesNames();
				showProductsView_.setAllCatagoriesInComboBox(catagoriesMap);

				JOptionPane.showMessageDialog(null, "New product successfully added", "InfoBox: " + "Msg",
						JOptionPane.INFORMATION_MESSAGE);
			} else {

				JOptionPane.showMessageDialog(null, "One of the data entered is incorrect", "InfoBox: " + "Msg",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	////////// edit product view //////////////

	class SearchProductForEditing implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (showEditProductView_.proudctIdtextField_.getText().matches("\\d+") == true) {

				int productId = Integer.parseInt(showEditProductView_.proudctIdtextField_.getText());
				Product wantedProduct = null;
				try {
					wantedProduct = model_.getProductById(productId);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (wantedProduct != null) {

					showEditProductView_.setStateForAllTextFiles(true);

					showEditProductView_.proudctNametextField_.setText(wantedProduct.getName());
					;
					showEditProductView_.categoryTextField_.setText(wantedProduct.getCategory());
					;
					showEditProductView_.costPriceTextField_.setText(String.valueOf(wantedProduct.getCostPrice()));
					showEditProductView_.sellingPriceTextField_
							.setText(String.valueOf(wantedProduct.getSellingPrice()));
					showEditProductView_.amountTextField_.setText(String.valueOf(wantedProduct.getAmount()));

				} else {

					showEditProductView_.proudctNametextField_.setText("");					;
					showEditProductView_.categoryTextField_.setText("");
					showEditProductView_.costPriceTextField_.setText("");
					showEditProductView_.sellingPriceTextField_.setText("");
					showEditProductView_.amountTextField_.setText("");

					showEditProductView_.setStateForAllTextFiles(false);

					JOptionPane.showMessageDialog(null, "proudct id not found ", "InfoBox: " + "Msg",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {

				showEditProductView_.proudctNametextField_.setText("");
				;
				showEditProductView_.categoryTextField_.setText("");
				showEditProductView_.costPriceTextField_.setText("");
				showEditProductView_.sellingPriceTextField_.setText("");
				showEditProductView_.amountTextField_.setText("");

				showEditProductView_.setStateForAllTextFiles(false);

				JOptionPane.showMessageDialog(null, "Invalid number", "InfoBox: " + "Msg",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	class BackListenerFromEditProductView implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			final Rectangle bounds = showEditProductView_.getBounds();
			showHomePageView_.setLocation(bounds.x, bounds.y);

			showHomePageView_.setVisible(true);
			showEditProductView_.setVisible(false);
		}
	}

	class SaveEditedProduct implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/// if the id is correct
			if (showEditProductView_.proudctIdtextField_.getText().matches("\\d+") == true) {

				int productId = Integer.parseInt(showEditProductView_.proudctIdtextField_.getText());
				Product wantedProduct = null;
				try {
					wantedProduct = model_.getProductById(productId);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (wantedProduct != null) {

					/// check all the text fields and create a new product
					if (showEditProductView_.costPriceTextField_.getText() != ""
							&& showEditProductView_.costPriceTextField_.getText().matches("\\d+") == true
							&& showEditProductView_.sellingPriceTextField_.getText().matches("\\d+") == true
							&& showEditProductView_.amountTextField_.getText().matches("\\d+") == true
							&& showEditProductView_.proudctNametextField_.getText() != "") {

						int id = Integer.parseInt(showEditProductView_.proudctIdtextField_.getText());
						String name = showEditProductView_.proudctNametextField_.getText();
						String category = showEditProductView_.categoryTextField_.getText();
						int costPrice = Integer.parseInt(showEditProductView_.costPriceTextField_.getText());
						int sellingPrice = Integer.parseInt(showEditProductView_.sellingPriceTextField_.getText());
						int amount = Integer.parseInt(showEditProductView_.amountTextField_.getText());

						Product product = new Product(id, name, category, costPrice, sellingPrice, amount);

						try {
							model_.updateProduct(product);
							showEditProductView_.categoryTextField_.setText("");
							showEditProductView_.costPriceTextField_.setText("");
							showEditProductView_.costPriceTextField_.setText("");
							showEditProductView_.sellingPriceTextField_.setText("");
							showEditProductView_.amountTextField_.setText("");
							showEditProductView_.proudctNametextField_.setText("");
						} catch (IOException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					
					JOptionPane.showMessageDialog(null, "Product not exsist ", "InfoBox: " + "Msg",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}

		}
	}

	class removeProduct implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (model_.user_.getType().equals("Manager")) {


				showEditProductView_.setStateForAllTextFiles(false);
	
				/// if the id is correct
				if (showEditProductView_.proudctIdtextField_.getText().matches("\\d+") == true) {
	
					int productId = Integer.parseInt(showEditProductView_.proudctIdtextField_.getText());
					Product wantedProduct = null;
					try {
						wantedProduct = model_.getProductById(productId);
	
						if (wantedProduct != null) {
	
							model_.removeProductById(wantedProduct.getProductID());
	
							JOptionPane.showMessageDialog(null, "Product permanently removed", "InfoBox: " + "Msg",
									JOptionPane.INFORMATION_MESSAGE);
	
							showEditProductView_.setStateForAllTextFiles(false);
	
						}
	
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
				}
			} else {
				
				JOptionPane.showMessageDialog(null, "אין לך הרשאת מנהל",
						null, JOptionPane.INFORMATION_MESSAGE);
				
			}
		}

	}
	
	class BackListenerFromAddProductView implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {			
		
			final Rectangle bounds = showAddNewProductView_.getBounds();
			showHomePageView_.setLocation(bounds.x, bounds.y);

			showHomePageView_.setVisible(true);
			showAddNewProductView_.setVisible(false);
			
			
		}
	}
}
