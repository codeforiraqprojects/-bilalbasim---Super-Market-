package com.team1.supermarket;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface DataOperations {

	//select data from table by table name.
	ResultSet getData(String tableName);

	//select data from table by table name and filter by id.
	ResultSet getData(String tableName, String id);

	//insert data in ItemSales table.
	void insertItemSales(int id, int id2, int id3, int qty);
	
	//insert data in ItemSales table.
	void insertUsers(String id, String id2, int id3, int id4);
	
	
	//insert data in table for 5 parameters.
	void insertItems(int id, String item_name, int qty, Double price, Double selling_price);

	//delete data from table by table name and filter by id.
	void deleteData(String tableName, int id);
	
	//delete data from table by table name and filter by id.
	void UpdateData(String tableName,String colName, double data, int id);
	
	//delete data from table by table name and filter by id.
		void UpdateData(String tableName,String colName, int data, int id);
		
		//delete data from table by table name and filter by id.
		void UpdateData(String tableName,String colName, String data, int id);
}