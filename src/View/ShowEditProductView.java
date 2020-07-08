package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;

public class ShowEditProductView extends JFrame {

	private JPanel contentPane;
	public JTextField proudctIdtextField_;
	
	public JButton saveButton_;
	public JButton searchButton_;
	public JButton backButton_;
	public JButton removeProductButton_;
	
	public JTextField proudctNametextField_;
	public JTextField categoryTextField_;
	public JTextField costPriceTextField_;
	public JTextField sellingPriceTextField_;
	public JTextField amountTextField_;
	private JButton btnNewButton;
	
	public ShowEditProductView() {
		
		this.setTitle("edit product screen");	

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(700, 400));
		setBounds(100, 100, 450, 346);
		this.pack();
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter product ID for editing");
		lblNewLabel.setBounds(38, 34, 178, 16);
		getContentPane().add(lblNewLabel);
		
		proudctIdtextField_ = new JTextField();
		proudctIdtextField_.setBounds(65, 67, 116, 22);
		getContentPane().add(proudctIdtextField_);
		proudctIdtextField_.setColumns(10);
		
		searchButton_ = new JButton("search");
		searchButton_.setBounds(75, 102, 97, 25);
		getContentPane().add(searchButton_);
		
		JLabel lblNewLabel_1 = new JLabel("Product name");
		lblNewLabel_1.setBounds(369, 34, 111, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("category");
		lblNewLabel_2.setBounds(369, 70, 111, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("cost price");
		lblNewLabel_3.setBounds(369, 106, 111, 16);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("selling price");
		lblNewLabel_4.setBounds(369, 146, 111, 16);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("amount");
		lblNewLabel_5.setBounds(369, 188, 111, 16);
		getContentPane().add(lblNewLabel_5);
		
		removeProductButton_ = new JButton("remove product");
		removeProductButton_.setBounds(487, 315, 135, 25);
		getContentPane().add(removeProductButton_);
		
		saveButton_ = new JButton("save");
		saveButton_.setBounds(506, 238, 97, 25);
		getContentPane().add(saveButton_);
		
		proudctNametextField_ = new JTextField();
		proudctNametextField_.setBounds(506, 31, 116, 22);
		getContentPane().add(proudctNametextField_);
		proudctNametextField_.setColumns(10);
		
		categoryTextField_ = new JTextField();
		categoryTextField_.setBounds(506, 67, 116, 22);
		getContentPane().add(categoryTextField_);
		categoryTextField_.setColumns(10);
		
		costPriceTextField_ = new JTextField();
		costPriceTextField_.setBounds(506, 103, 116, 22);
		getContentPane().add(costPriceTextField_);
		costPriceTextField_.setColumns(10);
		
		sellingPriceTextField_ = new JTextField();
		sellingPriceTextField_.setBounds(506, 143, 116, 22);
		getContentPane().add(sellingPriceTextField_);
		sellingPriceTextField_.setColumns(10);
		
		amountTextField_ = new JTextField();
		amountTextField_.setBounds(506, 188, 116, 22);
		getContentPane().add(amountTextField_);
		amountTextField_.setColumns(10);
		
		backButton_ = new JButton("back");
		backButton_.setBounds(49, 299, 97, 25);
		getContentPane().add(backButton_);
		
		
		
		setStateForAllTextFiles(false);
	}
	
	public void addSearchProductListener(ActionListener listenForButton) {
		
		searchButton_.addActionListener(listenForButton);
	}
	
	public void addSaveEditedProductListener(ActionListener listenForButton) {
		
		saveButton_.addActionListener(listenForButton);
	}
	
	public void addBackProductListener(ActionListener listenForButton) {
		
		backButton_.addActionListener(listenForButton);
	}
	
	
	public void addRemoveProductListener(ActionListener listenForButton) {
		
		removeProductButton_.addActionListener(listenForButton);
	
	}
	public void setStateForAllTextFiles(boolean state) {
		
		this.proudctNametextField_.setEnabled(state);
		this.categoryTextField_.setEnabled(state);
		this.costPriceTextField_.setEnabled(state);
		this.sellingPriceTextField_.setEnabled(state);
		this.amountTextField_.setEnabled(state);
	}
}
