/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.sql.SQLException;

/**
 * A menu to select which display you want to view.
 * @author Jacob Kole
 */
public class Menu extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify                     
    private javax.swing.JButton jButton_DirectAccess;
    private javax.swing.JButton jButton_Display1;
    private javax.swing.JButton jButton_Display2;
    private javax.swing.JButton jButton_Display3;
    private javax.swing.JButton jButton_Logout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
    
    static String username;
    
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("static-access")
	public static void main(String args[]) {
    	Menu menu = new Menu("guy");
    	menu.runner();
    }
    
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(username).setVisible(true);
            }
        });
    }

    /** Creates new form MenuFrame */
    @SuppressWarnings("static-access")
	public Menu(String username) {
    	this.username = username;
        initComponents();
    }

    /** 
     * Initialize all java swing components.
     */                     
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_DirectAccess = new javax.swing.JButton();
        jButton_Display1 = new javax.swing.JButton();
        jButton_Display2 = new javax.swing.JButton();
        jButton_Display3 = new javax.swing.JButton();
        jButton_Logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setPreferredSize(new java.awt.Dimension(750, 500));
        setPreferredSize(new java.awt.Dimension(765, 538));

        jPanel1.setBackground(new java.awt.Color(0, 2, 40));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 500));

        jButton_DirectAccess.setBackground(new java.awt.Color(255, 255, 255));
        jButton_DirectAccess.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton_DirectAccess.setText("Direct Access");
        jButton_DirectAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DirectAccessActionPerformed(evt);
            }
        });

        jButton_Display1.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Display1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton_Display1.setText("Display One");
        jButton_Display1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Display1ActionPerformed(evt);
            }
        });

        jButton_Display2.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Display2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton_Display2.setText("Display Two");
        jButton_Display2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton_Display2ActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jButton_Display3.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Display3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton_Display3.setText("Display Three");
        jButton_Display3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Display3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 253, 208));
        jLabel1.setText("Welcome, " + username);
        
        jButton_Logout.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Logout.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 11)); // NOI18N
        jButton_Logout.setText("Logout");
        jButton_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogoutActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Logout);
//        jButton_Login.setBounds(330, 180, 80, 23);
        jButton_Logout.setBounds(650, 450, 80, 23);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_DirectAccess, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jButton_Display2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Display1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Display3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addGap(67, 67, 67))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_DirectAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Display1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Display2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Display3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * Disposes the current window after opening a new login screen.
     * @param evt
     */
    @SuppressWarnings("static-access")
	private void jButton_LogoutActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    	// TODO add your handling code here:
    	Login login = new Login();
    	login.runner();
    	this.dispose();
    }    
    
	private void jButton_DirectAccessActionPerformed(java.awt.event.ActionEvent evt) {                                                     
    	DirectAccessDisplay_Stake frame = new DirectAccessDisplay_Stake();
    	frame.setVisible(true);
    }                                                    

	/**
	 * Opens Display One
	 * @param evt
	 */
    @SuppressWarnings("static-access")
	private void jButton_Display1ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	Display1_Kole frame = new Display1_Kole(username);
    	frame.runner();
    }                                                

    private void jButton_Display2ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException { 
    	/**
    	 * Opens display 2
    	 */
    	Messanger m = new Messanger();
        Display2_Forrester frame = new Display2_Forrester();
        frame.runner(username);
        
    }                                                

    /**
     * Opens display 3
     * @param evt	on d3 button pressed
     */
    @SuppressWarnings("static-access")

    private void jButton_Display3ActionPerformed(java.awt.event.ActionEvent evt)
    {                                                 
        Display3_Rutter frame = null;
		try
		{
			frame = new Display3_Rutter(username);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
        frame.runner();      
    }
}