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
    
    /**
     * A basic constructor
     */
    Messanger(){
    	
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
				//This will get us the game's players
				result = getPlayers();
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
    * This will send the message into the CHAT_MESSAGE datatable
    */
    public void sendMessage(String input, String recipient, String user) throws Exception
    {

        //This string will allows us to insert into the database with any value
        String insertData = new String("INSERT INTO CHAT_MESSAGES(P_Name, S_Name ,Message) VALUES (?,?,?)"); 
        createConnection();
        PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
        stmt.setString(1, user);
        stmt.setString(2, recipient);
        stmt.setString(3, input);
        stmt.executeUpdate();
    }

   /**
    * This gets the list of players in the game
    */
    public ArrayList<String> getPlayers() throws SQLException
    {
         String selectData = new String("Call Select_Player()");
         PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
         //Used to record the time taken
         ResultSet rs = null;
         rs = stmt.executeQuery();
         while(rs.next())
         {
        	 String data = rs.getString("Name");
        	 result.add(data);
         }
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



