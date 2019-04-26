package db;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Display1DBStatements {
	
	MyDB db;
	//private String playerName;
	private int money;
	
	public Display1DBStatements() {
		db = null;
		try {
			db = new MyDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(db == null) {
    		return;
    	}
	}
	
//	public String getPlayerName() {
//		return playerName;
//	}
	
	public int getMoney() {
		return money;
	}
	
//	/**
//	 * Selects from the table and prints out the result. To execute an SQL statement
//	 * that is a SELECT statement.
//	 * 
//	 * @throws SQLException
//	 */
//	public void selectPlayerName() throws SQLException {
//		String selectData1 = new String("SELECT Name FROM PLAYER");
//		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
//		
//		ResultSet rs1 = stmt1.executeQuery();
//
////		while (rs1.next()) {
////			// You can access values from a ResultSet either by column number - not advised:
//////			String data = rs1.getString(1);
//////			System.out.print(data + " : ");
////			// Or by column name - advised:
////			String data = rs1.getString("Name");
////			System.out.println(data);
////		}
//		
//		if(rs1.next()) {
//			String data = rs1.getString("Name");
//			playerName = data;
//			System.out.println(playerName);
//		}
//	}
	
	/**
	 * Selects from the table and prints out the result. To execute an SQL statement
	 * that is a SELECT statement.
	 * 
	 * @throws SQLException
	 */
	public void selectMoney(String playerName) throws SQLException {
		String selectData1 = new String("call selectMoney(?)");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		stmt1.setString(1, playerName);
		
		ResultSet rs1 = stmt1.executeQuery();

//		while (rs1.next()) {
//			// You can access values from a ResultSet either by column number - not advised:
////			String data = rs1.getString(1);
////			System.out.print(data + " : ");
//			// Or by column name - advised:
//			String data = rs1.getString("Name");
//			System.out.println(data);
//		}
		
		if(rs1.next()) {
			int data = rs1.getInt("Money");
			money = data;
			System.out.println(money);
		}
	}
	
	/**
	 * Selects the cost of a building.
	 * 
	 * @throws SQLException
	 */
	public int getBuildingCost(String buildingName) throws SQLException {
		String selectData1 = new String("select Cost from " + buildingName);
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
		ResultSet rs1 = stmt1.executeQuery();
		
		int cost = 0;
		if(rs1.next()) {
			cost = rs1.getInt("Cost");
			System.out.println(buildingName + " Cost: " + cost);
		}
		return cost;
	}
	
	/**
	 * Selects from the table and prints out the result. To execute an SQL statement
	 * that is a SELECT statement.
	 * 
	 * @throws SQLException
	 */
	public String generateID(String buildingName) throws SQLException {
		String selectData1 = new String("select MAX(ID) from " + buildingName);
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
		String data = "000";
		ResultSet rs1 = stmt1.executeQuery();

//		while (rs1.next()) {
//			// You can access values from a ResultSet either by column number - not advised:
////			String data = rs1.getString(1);
////			System.out.print(data + " : ");
//			// Or by column name - advised:
//			String data = rs1.getString("Name");
//			System.out.println(data);
//		}
		
		if(rs1.next()) {
			data = rs1.getString("MAX(ID)");
			System.out.println(data);
			
			int newestID = Integer.parseInt(data);
			data = "" + newestID;
			data = String.format("%03d", (newestID + 1));
			
			System.out.println("nID: " + newestID + " -> data: " + data);
		}
		
		return data;
	}
	
	/**
	 * Selects from the table and prints out the result. To execute an SQL statement
	 * that is NOT a SELECT statement.
	 * 
	 * @throws SQLException
	 */
	public void updateMoney(String playerName, int newAmount) throws SQLException {
		String selectData1 = new String("update PLAYER set Money = ? where Name = ?");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		stmt1.setInt(1, newAmount);
		stmt1.setString(2, playerName);
		
		int added = stmt1.executeUpdate();
		if(added == 1) {
			System.out.println("Updated");
		}

//		while (rs1.next()) {
//			// You can access values from a ResultSet either by column number - not advised:
////			String data = rs1.getString(1);
////			System.out.print(data + " : ");
//			// Or by column name - advised:
//			String data = rs1.getString("Name");
//			System.out.println(data);
//		}
		
//		if(rs1.next()) {
//			int data = rs1.getInt("Money");
//			money = data;
//			System.out.println(money);
//		}
	}
	
//	/**
//	 * Selects from the table and prints out the result. To execute an SQL statement
//	 * that is a SELECT statement.
//	 * 
//	 * @throws SQLException
//	 */
//	public void selectMoney() throws SQLException {
//		String selectData1 = new String("SELECT Money FROM PLAYER");
//		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
//		
//		ResultSet rs1 = stmt1.executeQuery();
//
////		while (rs1.next()) {
////			// You can access values from a ResultSet either by column number - not advised:
//////			String data = rs1.getString(1);
//////			System.out.print(data + " : ");
////			// Or by column name - advised:
////			String data = rs1.getString("Name");
////			System.out.println(data);
////		}
//		
//		if(rs1.next()) {
//			int data = rs1.getInt("Money");
//			money = data;
//			System.out.println(money);
//		}
//	}
	
	/**
	 * Inserts to the table. To execute an SQL statement that is not a SELECT
	 * statement. WITH PRIMARY KEY.
	 */
	public void insert(String statement) throws Exception {
		db.insertStatement(statement);
	}
	
}
