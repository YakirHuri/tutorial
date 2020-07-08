package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LoginView extends JFrame {

	private JPanel contentPane;
	public JTextField userNametextField;
	public JTextField passwordtextField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	JButton connectButton;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userNametextField = new JTextField();
		userNametextField.setBounds(182, 95, 217, 22);
		contentPane.add(userNametextField);
		userNametextField.setColumns(10);

		passwordtextField = new JTextField();
		passwordtextField.setBounds(182, 177, 217, 22);
		contentPane.add(passwordtextField);
		passwordtextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("שם משתמש");
		lblNewLabel.setBounds(247, 66, 152, 16);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("ססמא");
		lblNewLabel_1.setBounds(263, 153, 140, 16);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("PHARM SHOP");
		lblNewLabel_2.setBounds(235, 28, 115, 16);
		lblNewLabel_2.setFont(lblNewLabel_2.getFont().deriveFont(40));
		contentPane.add(lblNewLabel_2);

		connectButton = new JButton("התחבר");
		connectButton.setBounds(247, 227, 97, 25);
		contentPane.add(connectButton);
	}

	public void addConnectListener(ActionListener listenForButton) {

		connectButton.addActionListener(listenForButton);
	}
}
