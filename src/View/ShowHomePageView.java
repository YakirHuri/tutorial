package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class ShowHomePageView extends JFrame {

	private JPanel contentPane;

	public JButton showProductsButton_;
	public JButton addNewPrductButton_;
	public JButton editProductButton_;
	
	public ShowHomePageView() {
		
		this.setTitle("home page screen");			
				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		showProductsButton_ = new JButton("show products");
		showProductsButton_.setBounds(277, 41, 164, 25);
		contentPane.add(showProductsButton_);
		
		addNewPrductButton_ = new JButton("add new product");
		addNewPrductButton_.setBounds(277, 94, 164, 25);
		contentPane.add(addNewPrductButton_);
		
		editProductButton_ = new JButton("edit product");
		editProductButton_.setBounds(281, 147, 160, 25);
		contentPane.add(editProductButton_);
		
				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(700, 400));
		setBounds(100, 100, 450, 346);
		this.pack();
	}
	
	public void addShowProductsListener(ActionListener listenForButton) {
		
		showProductsButton_.addActionListener(listenForButton);
	}
	
	public void addAddNewProductsListener(ActionListener listenForButton) {
		
		addNewPrductButton_.addActionListener(listenForButton);
	}
	
	public void addEditProductsListener(ActionListener listenForButton) {
		
		editProductButton_.addActionListener(listenForButton);
	}

}
