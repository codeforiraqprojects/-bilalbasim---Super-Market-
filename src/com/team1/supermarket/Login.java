package com.team1.supermarket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Login {

	JFrame frame;
	private JTextField textField;
	JPanel panelLogin;
	JPanel panelAdmin;
	JPanel panelManag;
	JPanel panelDelete;
	JPanel panelUserVeiw;
	private static Shopping window;
	private JTextField txtId;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txtDeleteId;
	private JLabel lblNewLabel_1;
	Connect c = new Connect();
	private JTextField textField_1;
	private ResultSet s;
	private String tableName;
	private JTable table;
	private JLabel lblManagment;
	private JLabel lblItemName;
	private JLabel lblQuantity;
	private JLabel lblPrice;
	private JLabel lblSellingPrice;
	private JLabel lblId;
	private JButton btnBack_2;
	private JLabel lblDelete;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Shopping();
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Super Market");
		frame.getContentPane().setBackground(new Color(51, 102, 153));
		frame.setBounds(500, 200, 611, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		 panelLogin = new JPanel();
		panelLogin.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panelLogin, "name_681495070997998");
		panelLogin.setLayout(null);
		
		JLabel label = new JLabel("Username:");
		label.setBounds(66, 125, 105, 27);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		panelLogin.add(label);
		
		textField = new JTextField();
		textField.setBounds(194, 124, 221, 31);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		panelLogin.add(textField);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setBounds(80, 179, 91, 27);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		panelLogin.add(label_1);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet s = c.getData("Users",textField.getText());
				try {
					if(textField.getText().isEmpty()||textField_1.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Don't leave fields empty!!");
					}
				else if(s.next()==false) {
					JOptionPane.showMessageDialog(null, "User not found!!");
					}
				else if(!s.getString("password").equals(textField_1.getText())){
					JOptionPane.showMessageDialog(null, "Password is incorrect!!");
					}
				else if(s.getInt("permission")==1){
					panelAdmin.setVisible(true);
					panelLogin.setVisible(false);
					}
				else {
					Connect.userId = s.getInt("id");
					Connect.buyerId = c.getBuyerId(Connect.userId)==0?1:c.getBuyerId(Connect.userId)+1;					
					window.frame.setTitle("User: "+s.getString("user_name"));
					window.frame.setVisible(true);
					frame.setVisible(false);
					c.closeConnection();
					}
				} catch (HeadlessException | SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
			
		});
		button.setBounds(243, 295, 113, 48);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		button.setBackground(new Color(51, 102, 153));
		panelLogin.add(button);
		
		JLabel label_2 = new JLabel("Login");
		label_2.setBounds(243, 31, 113, 39);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Lucida Handwriting", Font.PLAIN, 28));
		panelLogin.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(194, 178, 221, 31);
		panelLogin.add(textField_1);
		
		 panelAdmin = new JPanel();
		panelAdmin.setBackground(new Color(0, 51, 51));
		frame.getContentPane().add(panelAdmin, "name_681860872089096");
		panelAdmin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Dashboard");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblNewLabel.setBounds(205, 28, 199, 50);
		panelAdmin.add(lblNewLabel);
		
		JButton btnReport = new JButton("Report");
		btnReport.setForeground(Color.WHITE);
		btnReport.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnReport.setBackground(new Color(0, 0, 102));
		btnReport.setBounds(244, 199, 113, 48);
		panelAdmin.add(btnReport);
		
		JButton btnUserManagment = new JButton("User Managment");
		btnUserManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmin.setVisible(false);
				panelUserVeiw.setVisible(true);
				tableName = "Users";
				s = c.getData(tableName);
				table.setModel(DbUtils.resultSetToTableModel(s));
				lblNewLabel_1.setText("Users Managment");
			}
		});
		btnUserManagment.setForeground(Color.WHITE);
		btnUserManagment.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnUserManagment.setBackground(new Color(0, 102, 0));
		btnUserManagment.setBounds(333, 114, 219, 48);
		panelAdmin.add(btnUserManagment);
		
		JButton btnItemManagment = new JButton("Item Managment");
		btnItemManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmin.setVisible(false);
				panelUserVeiw.setVisible(true);
				tableName = "Items";
				s = c.getData(tableName);
				table.setModel(DbUtils.resultSetToTableModel(s));
				lblNewLabel_1.setText("Items Managment");
			}
		});
		btnItemManagment.setForeground(Color.WHITE);
		btnItemManagment.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnItemManagment.setBackground(new Color(51, 0, 0));
		btnItemManagment.setBounds(42, 114, 219, 48);
		panelAdmin.add(btnItemManagment);
		
		JLabel label_3 = new JLabel("IT Group");
		label_3.setForeground(new Color(135, 206, 235));
		label_3.setFont(new Font("Stylus BT", Font.BOLD, 20));
		label_3.setBounds(500, 371, 97, 25);
		panelAdmin.add(label_3);
		
		btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAdmin.setVisible(false);
				panelLogin.setVisible(true);
			}
		});
		btnBack_2.setForeground(Color.WHITE);
		btnBack_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnBack_2.setBackground(new Color(0, 0, 102));
		btnBack_2.setBounds(244, 298, 113, 48);
		panelAdmin.add(btnBack_2);
		
		panelUserVeiw = new JPanel();
		panelUserVeiw.setBackground(new Color(51, 0, 0));
		frame.getContentPane().add(panelUserVeiw, "name_682811712192444");
		panelUserVeiw.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(tableName) {
				case "Users":
					lblManagment.setText("Insert User");
					lblId.setVisible(false);
					txtId.setVisible(false);
					lblItemName.setText("Username:");
					lblQuantity.setText("Password:");
					lblPrice.setText("Permission:");
					lblSellingPrice.setText("Status:");
					break;
				case "Items":
					lblManagment.setText("Insert Item");
					lblId.setVisible(true);
					txtId.setVisible(true);
					lblItemName.setText("Item Name:");
					lblQuantity.setText("Quantity:");
					lblPrice.setText("Price:");
					lblSellingPrice.setText("Selling Price:");
					break;
				}
				panelUserVeiw.setVisible(false);
				panelManag.setVisible(true);
				
			}
		});
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnInsert.setBackground(new Color(0, 0, 102));
		btnInsert.setBounds(440, 84, 113, 48);
		panelUserVeiw.add(btnInsert);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnEdit.setBackground(new Color(0, 0, 102));
		btnEdit.setBounds(440, 145, 113, 48);
		panelUserVeiw.add(btnEdit);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(UIManager.getColor("Label.background"));
		lblNewLabel_1.setBounds(413, 32, 168, 25);
		panelUserVeiw.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 389, 360);
		panelUserVeiw.add(scrollPane);
		
		table = new JTable();
		table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				TableModel t = (TableModel)e.getSource();
				int id = (int)t.getValueAt(row, 0);
				String cell;
				Double doub;
				int i;
				String colName = t.getColumnName(col);
				switch (tableName) {
				case "Users":
					switch (col) {
					case 0:
						cell = (String)t.getValueAt(row, col);
						System.out.println(colName+" "+cell);
						c.UpdateData(tableName, colName, cell, id);
						break;
					case 1:
						cell = (String)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, cell, id);
						break;
					case 2:
						i = (int)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, i, id);
						break;
					case 3:
						i = (int)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, i, id);
						break;	
					}
					break;
				case "Items":
					switch (col) {
					case 1:
						cell = (String)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, cell, id);
						break;
					case 2:
						i = (int)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, i, id);
						break;
					case 3:
						doub = (double)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, doub, id);
						break;	
					case 4:
						doub = (double)t.getValueAt(row, col);
						c.UpdateData(tableName, colName, doub, id);
						break;	
					}
				}
				s = c.getData(tableName);
				table.setModel(DbUtils.resultSetToTableModel(s));
				}
		});
		scrollPane.setViewportView(table);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUserVeiw.setVisible(false);
				panelAdmin.setVisible(true);
			}
		});
		btnBack_1.setForeground(Color.WHITE);
		btnBack_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnBack_1.setBackground(new Color(0, 0, 102));
		btnBack_1.setBounds(440, 267, 113, 48);
		panelUserVeiw.add(btnBack_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				panelUserVeiw.setVisible(false);
				panelDelete.setVisible(true);
				switch(tableName) {
				case "Users":
					lblDelete.setText("Delete User");
					break;
				case "Items":
					lblDelete.setText("Delete Item");
					break;
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnDelete.setBackground(new Color(0, 0, 102));
		btnDelete.setBounds(440, 206, 113, 48);
		panelUserVeiw.add(btnDelete);
		
		panelManag = new JPanel();
		panelManag.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panelManag, "name_683202353817204");
		panelManag.setLayout(null);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtId.setBounds(242, 72, 211, 32);
		panelManag.add(txtId);
		txtId.setColumns(10);
		
		txt1 = new JTextField();
		txt1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txt1.setColumns(10);
		txt1.setBounds(242, 114, 211, 32);
		panelManag.add(txt1);
		
		txt2 = new JTextField();
		txt2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txt2.setColumns(10);
		txt2.setBounds(242, 156, 211, 32);
		panelManag.add(txt2);
		
		txt3 = new JTextField();
		txt3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txt3.setColumns(10);
		txt3.setBounds(242, 198, 211, 32);
		panelManag.add(txt3);
		
		txt4 = new JTextField();
		txt4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txt4.setColumns(10);
		txt4.setBounds(242, 240, 211, 32);
		panelManag.add(txt4);
		
		lblId = new JLabel("ID");
		lblId.setForeground(SystemColor.text);
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblId.setBounds(87, 72, 127, 23);
		panelManag.add(lblId);
		
		lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(Color.WHITE);
		lblItemName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblItemName.setBounds(87, 119, 127, 23);
		panelManag.add(lblItemName);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantity.setBounds(87, 161, 127, 23);
		panelManag.add(lblQuantity);
		
		lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPrice.setBounds(87, 203, 127, 23);
		panelManag.add(lblPrice);
		
		lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setForeground(Color.WHITE);
		lblSellingPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSellingPrice.setBounds(87, 242, 127, 23);
		panelManag.add(lblSellingPrice);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id,qty,permission,status;
				String name,user_name,password;
				Double price,sprice;
				try {
					id = Integer.parseInt(txtId.getText());
					qty = Integer.parseInt(txt2.getText());
					price = Double.parseDouble(txt3.getText());
					sprice = Double.parseDouble(txt4.getText());
					name = txt1.getText();
					user_name = txt1.getText();
					password = txt2.getText();
					permission = Integer.parseInt(txt3.getText());
					status = Integer.parseInt(txt4.getText());
					switch (tableName) {
					case "Items":
						c.insertItems(id, name, qty, price, sprice);
						s = c.getData(tableName);
						table.setModel(DbUtils.resultSetToTableModel(s));	
					case "Users":
						c.insertUsers(user_name, password, permission, status);
						s = c.getData(tableName);
						table.setModel(DbUtils.resultSetToTableModel(s));	
					}
					panelManag.setVisible(false);
					panelUserVeiw.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Please Enter a valid data");
				}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnSave.setBackground(new Color(0, 102, 153));
		btnSave.setBounds(115, 327, 113, 48);
		panelManag.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManag.setVisible(false);
				panelUserVeiw.setVisible(true);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnBack.setBackground(new Color(102, 0, 0));
		btnBack.setBounds(373, 327, 113, 48);
		panelManag.add(btnBack);
		
		lblManagment = new JLabel("Insert Item");
		lblManagment.setForeground(Color.WHITE);
		lblManagment.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblManagment.setBounds(232, 13, 154, 37);
		panelManag.add(lblManagment);
		
		 panelDelete = new JPanel();
		panelDelete.setBackground(new Color(0, 51, 0));
		frame.getContentPane().add(panelDelete, "name_684294072832663");
		panelDelete.setLayout(null);
		
		JLabel lblDeleteId = new JLabel("ID");
		lblDeleteId.setForeground(Color.WHITE);
		lblDeleteId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDeleteId.setBounds(128, 165, 84, 23);
		panelDelete.add(lblDeleteId);
		
		txtDeleteId = new JTextField();
		txtDeleteId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDeleteId.setColumns(10);
		txtDeleteId.setBounds(222, 160, 211, 32);
		panelDelete.add(txtDeleteId);
		
		JButton btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=0;
				try {
					id = Integer.parseInt(txtDeleteId.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Please Enter a valid data");
				}
				c.deleteData(tableName, id);
				panelDelete.setVisible(false);
				panelUserVeiw.setVisible(true);
				s = c.getData(tableName);
				table.setModel(DbUtils.resultSetToTableModel(s));
			}
		});
		btnDel.setForeground(Color.WHITE);
		btnDel.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnDel.setBackground(new Color(0, 102, 153));
		btnDel.setBounds(109, 312, 113, 48);
		panelDelete.add(btnDel);
		
		JButton btnUsrBack = new JButton("Back");
		btnUsrBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDelete.setVisible(false);
				panelUserVeiw.setVisible(true);
			}
		});
		btnUsrBack.setForeground(Color.WHITE);
		btnUsrBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnUsrBack.setBackground(new Color(102, 0, 0));
		btnUsrBack.setBounds(367, 312, 113, 48);
		panelDelete.add(btnUsrBack);
		
		lblDelete = new JLabel("Insert User");
		lblDelete.setForeground(SystemColor.text);
		lblDelete.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDelete.setBounds(218, 13, 158, 37);
		panelDelete.add(lblDelete);
		
		JPanel panelUpdate = new JPanel();
		panelUpdate.setBackground(Color.ORANGE);
		frame.getContentPane().add(panelUpdate, "name_263675005999582");
		panelUpdate.setLayout(null);
		
		JLabel label_4 = new JLabel("Insert Item");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Stencil", Font.PLAIN, 20));
		label_4.setBounds(232, 13, 154, 37);
		panelUpdate.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(242, 72, 211, 32);
		panelUpdate.add(textField_2);
		
		JLabel label_5 = new JLabel("ID");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_5.setBounds(87, 72, 127, 23);
		panelUpdate.add(label_5);
		
		JLabel label_6 = new JLabel("Item Name");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_6.setBounds(87, 119, 127, 23);
		panelUpdate.add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(242, 114, 211, 32);
		panelUpdate.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(242, 156, 211, 32);
		panelUpdate.add(textField_4);
		
		JLabel label_7 = new JLabel("Quantity");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_7.setBounds(87, 161, 127, 23);
		panelUpdate.add(label_7);
		
		JLabel label_8 = new JLabel("Price");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_8.setBounds(87, 203, 127, 23);
		panelUpdate.add(label_8);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(242, 198, 211, 32);
		panelUpdate.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(242, 240, 211, 32);
		panelUpdate.add(textField_6);
		
		JLabel label_9 = new JLabel("Selling Price");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_9.setBounds(87, 242, 127, 23);
		panelUpdate.add(label_9);
		
		JButton button_1 = new JButton("Save");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		button_1.setBackground(new Color(0, 102, 153));
		button_1.setBounds(115, 327, 113, 48);
		panelUpdate.add(button_1);
		
		JButton button_2 = new JButton("Back");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		button_2.setBackground(new Color(102, 0, 0));
		button_2.setBounds(373, 327, 113, 48);
		panelUpdate.add(button_2);
	}
}
