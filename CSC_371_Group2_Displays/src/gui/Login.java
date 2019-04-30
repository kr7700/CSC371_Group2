package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A login gui to pass in a username to be used. It checks a given username and password
 * against the DB to be able to switch frames.
 * @author Jacob Kole
 */
public class Login extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * My assigned DB.
	 */
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_30";
	public static final String LOGIN_NAME = "csc371_30";
	public static final String PASSWORD = "Password30";
	// Make sure and use the java.sql imports.
	protected Connection m_dbConn = null;
	
	// Variables declaration - do not modify                     
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_Username;
    // End of variables declaration     
    
    @SuppressWarnings("static-access")
	public static void main(String args[]) {
    	Login login = new Login();
    	login.runner();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void runner() {
    	
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }         
	
    /**
     * Creates new form LoginFrame
     */
    public Login() {
    	activateJDBC(); // activate the DB
		connectDB(); // connect to the DB
        initComponents();
    }

    /**
     * Initialize all the java swing components.
     */                        
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField_Username = new javax.swing.JTextField();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jButton_Login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 2, 40));

        jPanel1.setLayout(null);

        jTextField_Username.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jTextField_Username.setText("Username");
        jTextField_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_UsernameActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_Username);
        jTextField_Username.setBounds(300, 120, 140, 20);

        jPasswordField_Password.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jPasswordField_Password.setText("Password");
        jPasswordField_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_PasswordActionPerformed(evt);
            }
        });
        jPanel1.add(jPasswordField_Password);
        jPasswordField_Password.setBounds(300, 150, 140, 19);

        jButton_Login.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Login.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 11)); // NOI18N
        jButton_Login.setText("Login");
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Login);
        jButton_Login.setBounds(330, 180, 80, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon("login.png")); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 720, 320);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }                      

    /**
     * On a click to the login button, the username and password will be checked against
     * the database. If they're confirmed to exist, it switches frames and disposes of
     * this one.
     * @param evt on mouse click
     */
    @SuppressWarnings({ "deprecation", "static-access" })
	private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        System.out.println(jTextField_Username.getText());
        System.out.println(jPasswordField_Password.getText());
        boolean confirmedLogin = false;
        try {
			confirmedLogin = checkCred(jTextField_Username.getText(), jPasswordField_Password.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        if(confirmedLogin) {
//        	Display1_Kole frame = new Display1_Kole(jTextField_Username.getText());
//        	frame.runner();
        	Menu menu = new Menu(jTextField_Username.getText());
        	menu.runner();
        	this.dispose();
        }
    }
    
    /**
     * Lets the user hit enter on the username field and activate login
     * @param evt
     */
    private void jTextField_UsernameActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	jButton_LoginActionPerformed(evt);
    }                                                   

    /**
     * Lets the user hit enter on the password field and activate login
     * @param evt pressing enter
     */
    private void jPasswordField_PasswordActionPerformed(java.awt.event.ActionEvent evt) {                                                        
    	jButton_LoginActionPerformed(evt);
    }
    
	/**
	 * Checks to see if the user's login is in the database.
	 * @throws SQLException
	 */
	public boolean checkCred(String username, String password) throws SQLException {
		String selectData1 = new String("select Name from PLAYER where Name = ? and Password = ?");
		PreparedStatement stmt1 = m_dbConn.prepareStatement(selectData1);
		stmt1.setString(1, username);
		stmt1.setString(2, password);
		
		ResultSet rs1 = stmt1.executeQuery();
		
		// returns true if the username and password are confirmed to be in the db
		boolean confirmed = false;
		if(rs1.next()) {
			confirmed = true;
		}
		
		if(confirmed) {
			System.out.println("Credentials exist in DB.");
		} else {
			System.out.println("Failure to login. Incorrect Username or Password.");
		}
		return confirmed;
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
			sqle.printStackTrace();
		}
	}
}