package com.team1.supermarket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Shopping {

	JFrame frame;
	private JTextField txtNo;
	private JTextField txtQr;
	private static Login window;
	private Connect c = new Connect();
	private ResultSet s;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Login();
					Shopping window = new Shopping();
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
	public Shopping() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("User: ");
		frame.getContentPane().setBackground(new Color(51, 102, 153));
		frame.getContentPane().setForeground(new Color(102, 102, 153));
		frame.setBounds(300, 100, 978, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNo = new JTextField();
		txtNo.setBounds(622, 126, 97, 36);
		txtNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(txtNo);
		txtNo.setColumns(10);
		
		
		txtQr = new JTextField();
		txtQr.setBounds(759, 126, 172, 36);
		txtQr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQr.setColumns(10);
		frame.getContentPane().add(txtQr);
		
		JLabel lblNo = new JLabel("No.");
		lblNo.setBounds(622, 88, 56, 25);
		lblNo.setForeground(SystemColor.text);
		lblNo.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		frame.getContentPane().add(lblNo);
		
		JLabel lblQr = new JLabel("QR");
		lblQr.setBounds(759, 88, 44, 25);
		lblQr.setForeground(SystemColor.text);
		lblQr.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		frame.getContentPane().add(lblQr);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(622, 253, 120, 48);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.counter = 0;
				try {
					int i = Integer.parseInt(txtNo.getText());
					int j = Integer.parseInt(txtQr.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Please Enter only number");
				}
					int z = Integer.parseInt(txtQr.getText());
					int y = Integer.parseInt(txtNo.getText());
					c.insertItemSales(Connect.buyerId,Connect.userId,z,y);
					Connect.counter++;
					s = c.getSales(Connect.userId);
					table_1.setModel(DbUtils.resultSetToTableModel(s));
			}
		});
		btnInsert.setHorizontalTextPosition(SwingConstants.LEFT);
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnInsert.setBackground(new Color(153, 102, 0));
		frame.getContentPane().add(btnInsert);
		
		JButton btnFinish = new JButton("Recipt");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, c.recipt(Connect.userId));
			}
		});
		btnFinish.setBounds(811, 253, 120, 48);
		btnFinish.setForeground(Color.WHITE);
		btnFinish.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnFinish.setBackground(new Color(0, 102, 51));
		frame.getContentPane().add(btnFinish);
		
		JLabel lblItGroupe = new JLabel("IT Group");
		lblItGroupe.setBounds(743, 462, 97, 25);
		lblItGroupe.setFont(new Font("Stylus BT", Font.BOLD, 20));
		lblItGroupe.setForeground(new Color(135, 206, 235));
		frame.getContentPane().add(lblItGroupe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 586, 474);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table_1.getSelectedColumn()==4) {
					System.out.println("Clicked");
					int row = table_1.getSelectedRow();
					System.out.println(row);
					String item = (String)table_1.getValueAt(row, 1);
					c.deleteSales(Connect.buyerId,Connect.userId,item);
					s = c.getSales(Connect.userId);
					table_1.setModel(DbUtils.resultSetToTableModel(s));
					
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		scrollPane.setViewportView(table_1);
		
		JButton btnNewBuyer = new JButton("Finish!");
		btnNewBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connect.buyerId++;
				s = c.getSalesEmpty();
				table_1.setModel(DbUtils.resultSetToTableModel(s));
				
			}
		});
		btnNewBuyer.setForeground(Color.WHITE);
		btnNewBuyer.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnNewBuyer.setBackground(new Color(0, 102, 51));
		btnNewBuyer.setBounds(720, 347, 120, 48);
		frame.getContentPane().add(btnNewBuyer);

	}
}
