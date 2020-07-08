package net.codejava.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import Control.Controller;
import Model.Model;
import View.LoginView;
import View.ShowAddNewProductView;
import View.ShowEditProductView;
import View.ShowHomePageView;
import View.ShowProductsView;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;

import java.sql.*;

public class App  {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ShowProductsView showProductsView = new ShowProductsView();
				ShowAddNewProductView showAddNewProductView = new ShowAddNewProductView();
				ShowHomePageView showHomePageView = new ShowHomePageView();
				ShowEditProductView showEditProductView = new ShowEditProductView();				
				LoginView loginView = new LoginView();
				
				Model model = null;
				
				try {
					model = new Model("Manager");
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Controller controller = 
						new Controller(showHomePageView, showProductsView,
						showAddNewProductView, showEditProductView, loginView, model);
			}
			 
		});
	}
	
	
	
	
}

	