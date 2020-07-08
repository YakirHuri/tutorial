package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowAddNewProductView extends JFrame {

	private JPanel contentPane;
	
	public JTextField productIdTextField_;
	public JTextField productNameTextField_;
	public JTextField productCategoryTextField_;
	public JTextField costPriceTextField_;
	public JTextField sellingPriceTextField_;
	public JTextField amountTextField_;

	public JButton btBackButton_;
	public JButton addProductButton_;
	
	public ShowAddNewProductView() {
		
		this.setTitle("add product screen");	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(417, 171, -391, -159);
		contentPane.add(scrollPane);
		
		productIdTextField_ = new JTextField();
		productIdTextField_.setBounds(292, 13, 116, 22);
		contentPane.add(productIdTextField_);
		productIdTextField_.setColumns(10);
		
		productNameTextField_ = new JTextField();
		productNameTextField_.setBounds(292, 48, 116, 22);
		contentPane.add(productNameTextField_);
		productNameTextField_.setColumns(10);
		
		productCategoryTextField_ = new JTextField();
		productCategoryTextField_.setBounds(292, 83, 116, 22);
		contentPane.add(productCategoryTextField_);
		productCategoryTextField_.setColumns(10);
		
		costPriceTextField_ = new JTextField();
		costPriceTextField_.setBounds(292, 118, 116, 22);
		contentPane.add(costPriceTextField_);
		costPriceTextField_.setColumns(10);
	
		
		sellingPriceTextField_ = new JTextField();
		sellingPriceTextField_.setBounds(292, 150, 116, 22);
		contentPane.add(sellingPriceTextField_);
		sellingPriceTextField_.setColumns(10);
		
		amountTextField_ = new JTextField();
		amountTextField_.setBounds(292, 190, 116, 22);
		contentPane.add(amountTextField_);
		amountTextField_.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Product Id");
		lblNewLabel.setBounds(69, 16, 122, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product name");
		lblNewLabel_1.setBounds(69, 51, 122, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("category");
		lblNewLabel_2.setBounds(69, 86, 122, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("cost price");
		lblNewLabel_5.setBounds(69, 121, 81, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3  = new JLabel("selling price");
		lblNewLabel_3.setBounds(69, 156, 81, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("amount");
		lblNewLabel_4.setBounds(69, 193, 81, 16);
		contentPane.add(lblNewLabel_4);			
		
		btBackButton_ = new JButton("back");		
		btBackButton_.setBounds(39, 261, 97, 25);
		contentPane.add(btBackButton_);		
		
		addProductButton_ = new JButton("add product");
		addProductButton_.setBounds(292, 261, 130, 25);
		contentPane.add(addProductButton_);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(700, 400));
		setBounds(100, 100, 450, 346);
		this.pack();		
		
	}
	
	public void addBackListener(ActionListener listenForButton) {
		
		btBackButton_.addActionListener(listenForButton);
	}
	
	public void addAddProductListener(ActionListener listenForButton) {
		
		addProductButton_.addActionListener(listenForButton);
	}
}
