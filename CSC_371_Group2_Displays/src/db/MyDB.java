package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * A program that creates, inserts to, and selects from a large table in a given
 * database and times each iteration of commands.
 * 
 * @author Jacob Kole
 */
public class MyDB {
	// For a more in-depth tutorial see:
	// https://www.tutorialspoint.com/jdbc/index.htm

	// IMPORTANT: Make sure all imports for the SQL stuff use java.sql !!!

	/**
	 * My assigned DB.
	 */
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_30";
	public static final String LOGIN_NAME = "csc371_30";
	public static final String PASSWORD = "Password30";
	// Make sure and use the java.sql imports.
	protected Connection m_dbConn = null;

	/**
	 * Main runner for the file.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		MyDB db = new MyDB();
		//db.setup();
	}
	
	/**
	 * Gets the connection to the db.
	 * @return connection to the db
	 */
	public Connection getConn() {
		return m_dbConn;
	}

	/**
	 * Constructor. This activated the DB, connects to it, then goes through and
	 * inserts all the rows and times them. To do the manual selects between the blocks
	 * of code, simply comment out the unrelated blocks following where you want to stop.
	 * 
	 * @throws Exception
	 */
	public MyDB() throws Exception {
		activateJDBC(); // activate the DB
		connectDB(); // connect to the DB
	}
	
	public void setup() throws Exception {
		File createFile = new File("createTables.txt"); // has all creation statements
		File insertFile = new File("insertTables.txt"); // has all insert statements
		File tableNamesReverseOrderFile = new File("tableNamesReverseOrder.txt"); // has names of all tables (in reverse order from creation)
		
		dropAllTables(tableNamesReverseOrderFile);
		// creating all tables
		createAllTables(createFile);
		// inserting into all tables
		insertToTables(insertFile);
		// dropping all tables starting from the most recent table made (reverse order from creation)
		//dropAllTables(tableNamesReverseOrderFile);
	}

	/**
	 * This is the recommended way to activate the JDBC drivers, but is only setup
	 * to work with one specific driver. Setup to work with a MySQL JDBC driver.
	 *
	 * If the JDBC Jar file is not in your build path this will not work. I have the
	 * Jar file posted in D2L.
	 * 
	 * @return Returns true if it successfully sets up the driver.
	 */
	public boolean activateJDBC() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return true;
	}

	/**
	 * Creates a connection to the database that you can then send commands to.
	 * 
	 * @throws SQLException
	 */
	public void connectDB() {
		try {
			m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
	}
	
	/**
	 * Create all the tables in the DB.
	 * @param file the file containing all the create statements
	 * @throws Exception
	 */
	public void createAllTables(File file) throws Exception {
		Scanner sc = new Scanner(file);
		String line;
		int tableCount = 1;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.println((tableCount++) + " : " + line);
			
			createStatement(line);
		}
		sc.close();
	}
	
	/**
	 * Insert into all tables in the DB.
	 * @param file the file containing all the insert statements
	 * @throws Exception
	 */
	public void insertToTables(File file) throws Exception {
		Scanner sc = new Scanner(file);
		String line;
		int insertCount = 1;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.println((insertCount++) + " : " + line);
			
			insertStatement(line);
		}
		sc.close();
	}
	
	/**
	 * Drops all tables in the DB in reverse order, starting from the last one made
	 * and ending with the first one created.
	 * @param file the file containing all the table names
	 * @throws Exception
	 */
	public void dropAllTables(File file) throws Exception {
		Scanner sc = new Scanner(file);
		String line;
		int dropCount = 1;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.println((dropCount++) + " : " + line);
			
			dropStatement(line);
		}
		sc.close();
	}
	
	/**
	 * Creates a table with a primary key. To execute an SQL statement that is not a
	 * SELECT statement. WITH PRIMARY KEY.
	 */
	public void createStatement(String line) throws Exception {
		String insertData = new String(line);
		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
		int rowsAdded = stmt.executeUpdate();
		if (rowsAdded == 0) {
			System.out.println("Created");
		}
	}
	
	/**
	 * Creates a table with a primary key. To execute an SQL statement that is not a
	 * SELECT statement. WITH PRIMARY KEY.
	 */
	public void insertStatement(String line) throws Exception {
		String insertData = new String(line);
		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
		int rowsAdded = stmt.executeUpdate();
		if (rowsAdded == 1) {
			System.out.println("Added");
		}
	}
	
	/**
	 * Drops the table. To execute an SQL statement that is not a SELECT statement.
	 * WITH PRIMARY KEY.
	 */
	public void dropStatement(String line) throws Exception {
		String insertData = new String("DROP TABLE IF EXISTS " + line);
		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
		int rowsAdded = stmt.executeUpdate();
		if (rowsAdded == 0) {
			System.out.println("Dropped");
		}
	}

//	/**
//	 * Creates a table with no primary key. To execute an SQL statement that is not
//	 * a SELECT statement. NO PRIMARY KEY.
//	 */
//	public void createStatementNoPK() throws Exception {
//		// Using a PreparedStatement to insert a value (best option when providing
//		// values
//		// from variables).
//		// Use place holders '?' to mark where I am going to provide the data.
//		String insertData = new String(
//				"CREATE TABLE TEST_KOLE (col1 INT,col2 INT,col3 CHAR(10),col4 VARCHAR(30),col5 DOUBLE)");
//		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
//		// When I need to set a primitive type as null.
//		// stmt2.setNull(2, java.sql.Types.INTEGER);
//		int rowsAdded = stmt.executeUpdate();
//		if (rowsAdded == 0) {
//			System.out.println("Created");
//		}
//	}
	
//	/**
//	 * Creates a table with a primary key. To execute an SQL statement that is not a
//	 * SELECT statement. WITH PRIMARY KEY.
//	 */
//	public void createStatementPK() throws Exception {
//		// Using a PreparedStatement to insert a value (best option when providing
//		// values
//		// from variables).
//		// Use place holders '?' to mark where I am going to provide the data.
//		String insertData = new String(
//				"CREATE TABLE TEST_KOLE (col1 INT,col2 INT,col3 CHAR(10),col4 VARCHAR(30),col5 DOUBLE,"
//						+ "PRIMARY KEY (col1))");
//		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
//		// When I need to set a primitive type as null.
//		// stmt2.setNull(2, java.sql.Types.INTEGER);
//		int rowsAdded = stmt.executeUpdate();
//		if (rowsAdded == 0) {
//			System.out.println("Created");
//		}
//	}

//	/**
//	 * Drops the table. To execute an SQL statement that is not a SELECT statement.
//	 * WITH PRIMARY KEY.
//	 */
//	public void dropStatement() throws Exception {
//		// Using a PreparedStatement to insert a value (best option when providing
//		// values
//		// from variables).
//		// Use place holders '?' to mark where I am going to provide the data.
//		String insertData = new String("DROP TABLE TEST_KOLE");
//		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
//		// When I need to set a primitive type as null.
//		// stmt2.setNull(2, java.sql.Types.INTEGER);
//		int rowsAdded = stmt.executeUpdate();
//		if (rowsAdded == 0) {
//			System.out.println("Dropped");
//		}
//	}
	
//	/**
//	 * Inserts to the table. To execute an SQL statement that is not a SELECT
//	 * statement. WITH PRIMARY KEY.
//	 */
//	public void insertStatementLoop() throws Exception {
//		// Using a PreparedStatement to insert a value (best option when providing
//		// values
//		// from variables).
//		// Use place holders '?' to mark where I am going to provide the data.
//		int percent = 0;
//		String insertData = new String("INSERT INTO TEST_KOLE VALUES (?,?,?,?,?)");
//		PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
//		for (int j = 1; j <= 5000; j++) {
//			for (int i = (j * 100 - 99); i <= j * 100; i++) {
//				stmt.setInt(1, i);
//				stmt.setInt(2, i + 1);
//				stmt.setString(3, "a" + i);
//				stmt.setString(4, "a" + (i + 1));
//				stmt.setDouble(5, (double) i);
//				// When I need to set a primitive type as null.
//				// stmt2.setNull(2, java.sql.Types.INTEGER);
//				stmt.addBatch();
//			}
//			stmt.executeBatch();
//			stmt.clearBatch();
//
//			if (j % 500 == 0) { // percentage checkpoints as the table is generated (by 10% chunks)
//				percent += 10;
//				System.out.print("\t" + percent + "%");
//			}
//		}
//		System.out.println("");
//	}

//	/**
//	 * Selects from the table and prints out the result. To execute an SQL statement
//	 * that is a SELECT statement.
//	 * 
//	 * @throws SQLException
//	 */
//	public void selectStatementLoopCol1(int j) throws SQLException {
//		String selectData1 = new String("SELECT * FROM TEST_KOLE WHERE col1=?");
//
//		PreparedStatement stmt1 = m_dbConn.prepareStatement(selectData1);
//
//		for (int i = j - 99; i <= j; i++) {
//			stmt1.setInt(1, i);
//
//			ResultSet rs1 = stmt1.executeQuery();
//
//			while (rs1.next()) {
//				// You can access values from a ResultSet either by column number - not advised:
////				String data = rs1.getString(1);
////				System.out.print(data + " : ");
//				// Or by column name - advised:
//				String data = rs1.getString("col1");
//				System.out.println(data);
//			}
//		}
//	}
	
//	/**
//	 * Selects from the table and prints out the result. To execute an SQL statement
//	 * that is a SELECT statement.
//	 * 
//	 * @throws SQLException
//	 */
//	public void selectStatementLoopCol2(int j) throws SQLException {
//		String selectData2 = new String("SELECT * FROM TEST_KOLE WHERE col2=?");
//
//		PreparedStatement stmt2 = m_dbConn.prepareStatement(selectData2);
//
//		for (int i = j - 99; i <= j; i++) {
//			stmt2.setInt(1, i + 1);
//
//			ResultSet rs2 = stmt2.executeQuery();
//
//			while (rs2.next()) {
//				// You can access values from a ResultSet either by column number - not advised:
////				String data = rs1.getString(1);
////				System.out.print(data + " : ");
//				// Or by column name - advised:
//				String data = rs2.getString("col2");
//				System.out.println(data);
//			}
//		}
//	}
}