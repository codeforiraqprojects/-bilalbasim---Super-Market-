package com.team1.supermarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Connect implements DataOperations {
	private Connection conn = null;
	private String sql;
	static int userId;
	static int counter;
	static int buyerId;

	public Connect() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Supermarket.db");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public String recipt(int id) {
		Double total=0.0;
		String message= "Item    Quantity    Price  \n";
			   message += "-----------------------------\n";
		sql = "select i.item_name as 'Item',"
				+ "iis.qty as 'Quantity', i.selling_price as 'price'"
				+ " from ItemSales iis inner join Items i "
				+ "on iis.item_id = i.id where iis.buyer_id = "
				+ "(select MAX(buyer_id) from ItemSales) and "
				+ "iis.user_id ="+id;
		ResultSet s = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		try {
			while(s.next()) {
				message += s.getString(1)+"   "+s.getInt(2)
				+"    "+(s.getInt(2)*s.getDouble(3))+"\n";
				total += s.getInt(2)*s.getDouble(3);
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		message +="-----------------------------\n";
		message +="    total price:   "+ total;
		return message;
	}

	public ResultSet getSales(int id) {
		sql = "select "+counter+ " as 'No', i.item_name as 'Item',"
				+ "iis.qty as 'Quantity', i.selling_price as 'price'"
				+ ",'Delete' as 'Delete' from ItemSales iis inner join Items i "
				+ "on iis.item_id = i.id where iis.buyer_id = "
				+ "(select MAX(buyer_id) from ItemSales) "
				+ "and iis.user_id = "+id;
		ResultSet s = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return s;
	}
	
	public ResultSet getSalesEmpty() {
		sql = "select '' as 'No', '' as 'Item',"
				+ "'' as 'Quantity', '' as 'price'"
				+ ",'' as 'Delete'";
		ResultSet s = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return s;
	}
	
	public ResultSet getData(String tableName) {
		sql = "select * from " + tableName;
		ResultSet s = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.team1.supermarket.DataOperations#getData(java.lang.String, int)
	 */
	@Override
	public ResultSet getData(String tableName, String id) {	
		ResultSet s = null;
		try {
			switch(tableName) {
			case "Users":
				sql = "select * from " + tableName + " where user_name = '" + id.trim()+"'";
				break;
			default:
				sql = "select * from " + tableName + " where id = " + Integer.parseInt(id.trim());
			}
			
			PreparedStatement pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.team1.supermarket.DataOperations#insertItemSales(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	//insert data in table.
		public void insertItemSales(int id, int id2, int id3, int qty) {
		ResultSet s = null;
		PreparedStatement pst;
		int count=0;
		sql = "select qty from ItemSales where item_id ="+id3
				+" and user_id = "+userId+" and buyer_id = " + buyerId;
		try {
			pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
			count = (int) s.getInt(1);
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		if(count>0) {
			System.out.println("b = "+buyerId+", u = "+userId);
			sql = "update ItemSales set qty ="+(count+qty)+" where "
					+ "buyer_id = "+ buyerId+" and user_id = "+ userId
					+ " and item_id = "+id3;
			try {
				pst = conn.prepareStatement(sql);
				pst.execute();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		else {
			sql = "INSERT INTO ItemSales(buyer_id,user_id,item_id,qty) VALUES(?,?,?,?)";	
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, id2);
			pst.setInt(3, id3);
			pst.setInt(4, qty);
			pst.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
		}
	}
	
	//insert data in table.
	public void insertItems(int id, String id2, int id3, Double id4, Double id5) {
	sql = "INSERT INTO Items(id,item_name,qty,price,selling_price) VALUES(?,?,?,?,?)";
	PreparedStatement pst;
	try {
		pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		pst.setString(2, id2);
		pst.setInt(3, id3);
		pst.setDouble(4, id4);
		pst.setDouble(5, id5);
		pst.execute();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());	
	}
	
}
	
	//insert data in table.
	public void insertUsers(String id, String id2, int id3, int id4) {
	sql = "INSERT INTO Users(user_name,password,permission,status) VALUES(?,?,?,?)";
	PreparedStatement pst;
	try {
		pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		pst.setString(2, id2);
		pst.setInt(3, id3);
		pst.setInt(4, id4);
		pst.execute();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());	
	}
	
}
	
	
	@Override
	public void deleteData(String tableName, int id) {
		sql = "delete from " + tableName + " where id = " + id;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deleteSales(int id,int id2, String item) {
		sql = "delete from ItemSales where buyer_id = " + id 
				+ " and user_id = " +id2+" and item_id = (select id from"
						+ " Items where item_name ='"+ item+"')";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}


		public void UpdateData(String tableName,String colName, double data, int id) {
			sql = "update "+tableName+" set " + colName
					+ " = "+ data + " where id = "+ id;
			PreparedStatement pst;
			try {
				pst = conn.prepareStatement(sql);
				pst.execute();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		//delete data from table by table name and filter by id.
			public void UpdateData(String tableName,String colName, int data, int id) {
				sql = "update "+tableName+" set " + colName
						+ " = "+ data + " where id = "+ id;
				PreparedStatement pst;
				try {
					pst = conn.prepareStatement(sql);
					pst.execute();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			
			//delete data from table by table name and filter by id.
			public void UpdateData(String tableName,String colName, String data, int id) {
				sql = "update "+tableName+" set " + colName
						+ " = "+ data + " where id = "+ id;
				PreparedStatement pst;
				try {
					pst = conn.prepareStatement(sql);
					pst.execute();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public int getBuyerId(int id) {
		sql = "select * from ItemSales where buyer_id ="
				+ "(select MAX(buyer_id) from ItemSales where user_id = "+id+")";
		ResultSet s = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			s = pst.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No data in the table"
					+ "... will enter new record");
		}
		try {
			buyerId = s.next()==false?0:s.getInt("buyer_id");
			System.out.println(buyerId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return buyerId;
	}
	
}
