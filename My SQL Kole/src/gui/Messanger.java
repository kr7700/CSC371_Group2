package gui;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This will help with all connections to the TEST_Forrester datatable
 * @author Tyler Forrester
 *
 */
public class Messanger {
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_30";
	public static final String LOGIN_NAME = "csc371_30";
	public static final String PASSWORD = "Password30";
    protected Connection m_dbConn = null;
    public ArrayList <String> result = new ArrayList<String>();
    
    
    Messanger(){
    	
    }
    
    public ArrayList<String> getResults(){
    	System.out.println(result.get(1));
    	return result;
    }
    
    public int getResultsSize() {
    	return result.size();
    }
    
    public void addResult(String s) {
    	result.add(s);
    }
    
    
   /** 
    * Creates a connection to the database that you can then send commands to and gets meta data from the DB
    * @throws SQLException 
    */
    public void createConnection() throws SQLException
    {
      m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
      DatabaseMetaData meta = m_dbConn.getMetaData();
    }
    


    
    /**
 	 * This program is what allows communication by calling on other methods
     */
    public ArrayList<String> activateJDBC()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            try {
            	//This will call upon the method that runs the insert statements
				//testNonSelectStatements();
				//This will call upon the method that runs select statments
				result = testSelectStatements();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return result;
    }
    
    /**
     * This creates a table TEST_Forrester for the selection and insertion tests
     * @throws SQLException
     */
    public void createTable() throws SQLException
    {
    	String createData = new String("CREATE TABLE TEST_Forrester (CNum int, Age int, Name char(10), LName varchar(30), percent double, PRIMARY KEY (CNum));");
    	Statement stmt = m_dbConn.createStatement();
    	stmt.executeUpdate(createData);
    	System.out.println("Table Added");
    }

   /**
    * To execute an SQL statement that is not a SELECT statement.
    */
    public void testNonSelectStatements() throws Exception
    {

        //This string will allows us to insert into the database with any value
        String insertData = new String("INSERT INTO TEST_Forrester (CNum, Age, Name, LName, percent) VALUES (?,?,?,?,?)");
        PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
        final long startTime = System.currentTimeMillis();
        //This loop will run the loop inside 5000 times, while recording how close it is to being done.
        for(int j = 1; j <= 5000; j++)
        {
        	//This loop will insert 100 rows into the datatable
        	for(int i = 1; i <= 100; i++) {
        		stmt.setInt(1, ((j-1)*100)+(i-1));
           		stmt.setInt(2, ((j-1)*100)+(i-1));
           		int toString = 500000-(j*i);
           		String inset = Integer.toString(toString);
           		stmt.setString(3, inset);
           		stmt.setString(4, inset);
           		Random r = new Random();
           		double randomValue = 100 + (100 - 0) * r.nextDouble();
           		stmt.setDouble(5, randomValue);
           		stmt.addBatch();
        	}
        	stmt.executeBatch();
        	stmt.clearBatch(); 
        	double start = (double)(j);
        	double percentage = (start/5000) * 100;
        	System.out.println(percentage + "%");
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }

   /**
    * Executes a select command to the TEST_Forrester datatable
    */
    public ArrayList<String> testSelectStatements() throws SQLException
    {
         String selectData = new String("SELECT * FROM PLAYER");
         PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
         //Used to record the time taken
         final long startTime = System.currentTimeMillis();
         ResultSet rs = null;
         rs = stmt.executeQuery();
         //int i = 0;
         while(rs.next())
         {
        	 String data = rs.getString("Name");
        	 result.add(data);
        	 //System.out.println(data);
        	 //System.out.println(result.get(i));
        	 //i++;
         }
         final long endTime = System.currentTimeMillis();
         System.out.println("Total execution time: " + (endTime - startTime));
         return result;
     }
    
    
    /**
     * This runs the program
     */
   public ArrayList<String> runDatabase() throws SQLException
   {
	   Messanger db = new Messanger();
	   db.createConnection();
	   result = db.activateJDBC();  
	   return result;
   }
}



