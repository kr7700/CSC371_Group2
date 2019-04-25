package db;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Display1DBStatements {
	
	MyDB db;
	private String playerName;
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
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getMoney() {
		return money;
	}
	
	/**
	 * Selects from the table and prints out the result. To execute an SQL statement
	 * that is a SELECT statement.
	 * 
	 * @throws SQLException
	 */
	public void selectPlayerName() throws SQLException {
		String selectData1 = new String("SELECT Name FROM PLAYER");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
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
			String data = rs1.getString("Name");
			playerName = data;
			System.out.println(playerName);
		}
	}
	
	/**
	 * Selects from the table and prints out the result. To execute an SQL statement
	 * that is a SELECT statement.
	 * 
	 * @throws SQLException
	 */
	public void selectMoney() throws SQLException {
		String selectData1 = new String("SELECT Money FROM PLAYER");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
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
	 * Inserts to the table. To execute an SQL statement that is not a SELECT
	 * statement. WITH PRIMARY KEY.
	 */
	public void insertStatementLoop() throws Exception {
		// Using a PreparedStatement to insert a value (best option when providing
		// values
		// from variables).
		// Use place holders '?' to mark where I am going to provide the data.
		String insertData = new String("INSERT INTO TEST_KOLE VALUES (?,?,?,?,?)");
		PreparedStatement stmt = db.getConn().prepareStatement(insertData);
		
//		stmt.setInt(1, i);
//		stmt.setInt(2, i + 1);
//		stmt.setString(3, "a" + i);
//		stmt.setString(4, "a" + (i + 1));
//		stmt.setDouble(5, (double) i);
		// When I need to set a primitive type as null.
		// stmt2.setNull(2, java.sql.Types.INTEGER);
		
	}
	
}
