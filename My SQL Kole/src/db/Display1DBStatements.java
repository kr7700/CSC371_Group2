package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class to house all the statements utilized for display 1.
 * @author Jacob Kole
 */
public class Display1DBStatements {
	
	MyDB db;
	private int money;
	private String name;
	
	/**
	 * Constructor for the DB statements used for Display1
	 */
	public Display1DBStatements(String playerName) {
		name = playerName;
		db = null;
		try {
			db = new MyDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the money variable
	 * @return amount of money the player has
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Gets the player name
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Grabs how much money the player has using a procedure.
	 * @throws SQLException
	 */
	public void selectMoney(String playerName) throws SQLException {
		String selectData1 = new String("call selectMoney(?)");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		stmt1.setString(1, playerName);
		
		ResultSet rs1 = stmt1.executeQuery();
		
		// stores the amount of money into a variable
		if(rs1.next()) {
			int data = rs1.getInt("Money");
			money = data;
			System.out.println(money);
		}
	}
	
	/**
	 * Selects the cost of a given building.
	 * @throws SQLException
	 */
	public int getBuildingCost(String buildingName) throws SQLException {
		String selectData1 = new String("select Cost from " + buildingName);
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
		ResultSet rs1 = stmt1.executeQuery();
		
		// returns the cost of the building
		int cost = 0;
		if(rs1.next()) {
			cost = rs1.getInt("Cost");
			System.out.println(buildingName + " Cost: " + cost);
		}
		return cost;
	}
	
	/**
	 * Selects the planet ID of a planet owned by a certain player.
	 * @throws SQLException
	 */
	public String getPlanetID(String playerName) throws SQLException {
		String selectData1 = new String("select ID from PLANET where P_Name = ?");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		stmt1.setString(1, playerName);
		
		ResultSet rs1 = stmt1.executeQuery();
		
		// returns the planet id
		String pID = "00x00";
		if(rs1.next()) {
			pID = rs1.getString("ID");
//			System.out.println(buildingName + " P_ID: " + pID);
			System.out.println("Planet ID: " + pID);
		}
		return pID;
	}
	
	/**
	 * Grabs the highest ID in a given building's table and generates
	 * the next ID up from it.
	 * @throws SQLException
	 */
	public String generateID(String buildingName) throws SQLException {
		String selectData1 = new String("select MAX(ID) from " + buildingName);
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
		String newID = "000";
		ResultSet rs1 = stmt1.executeQuery();
		
		// returns the new generated id after grabbing
		// the last added id and incrementing it
		if(rs1.next()) {
			newID = rs1.getString("MAX(ID)");
			int lastAddedID = Integer.parseInt(newID);
			newID = "" + lastAddedID;
			newID = String.format("%03d", (lastAddedID + 1));
			
			System.out.println("Last Added ID: " + lastAddedID + " -> Generated ID: " + newID);
		}
		
		return newID;
	}
	
	/**
	 * Updates a given amount of money for a given player.
	 * @throws SQLException
	 */
	public void updateMoney(String playerName, int newAmount) throws SQLException {
		String selectData1 = new String("update PLAYER set Money = ? where Name = ?");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		stmt1.setInt(1, newAmount);
		stmt1.setString(2, playerName);
		
		// updates the money
		int added = stmt1.executeUpdate();
		if(added == 1) {
			System.out.println("Updated");
		}
	}
	
	/**
	 * Inserts into a table dependent on the modified statement passed in.
	 */
	public void insert(String statement, int money) throws Exception {
		this.money = money;
		db.insertStatement(statement);
	}
	
}
