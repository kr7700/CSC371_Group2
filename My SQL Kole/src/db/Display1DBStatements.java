package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display1DBStatements {
	
	MyDB db;
	
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
	
	/**
	 * Selects from the table and prints out the result. To execute an SQL statement
	 * that is a SELECT statement.
	 * 
	 * @throws SQLException
	 */
	public void selectPlayerName() throws SQLException {
		String selectData1 = new String("SELECT * FROM PLAYER");
		PreparedStatement stmt1 = db.getConn().prepareStatement(selectData1);
		
		ResultSet rs1 = stmt1.executeQuery();

		while (rs1.next()) {
			// You can access values from a ResultSet either by column number - not advised:
//			String data = rs1.getString(1);
//			System.out.print(data + " : ");
			// Or by column name - advised:
			String data = rs1.getString("Name");
			System.out.println(data);
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
		int percent = 0;
		String insertData = new String("INSERT INTO TEST_KOLE VALUES (?,?,?,?,?)");
		PreparedStatement stmt = db.getConn().prepareStatement(insertData);
		for (int j = 1; j <= 5000; j++) {
			for (int i = (j * 100 - 99); i <= j * 100; i++) {
				stmt.setInt(1, i);
				stmt.setInt(2, i + 1);
				stmt.setString(3, "a" + i);
				stmt.setString(4, "a" + (i + 1));
				stmt.setDouble(5, (double) i);
				// When I need to set a primitive type as null.
				// stmt2.setNull(2, java.sql.Types.INTEGER);
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.clearBatch();

			if (j % 500 == 0) { // percentage checkpoints as the table is generated (by 10% chunks)
				percent += 10;
				System.out.print("\t" + percent + "%");
			}
		}
		System.out.println("");
	}
	
	
}
