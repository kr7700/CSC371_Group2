package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Displays a player's production: their resources, # factories and # shipyards.
 * Also allows a player to allocate their resources into factories and shipyards to build baubles or ships.
 * @author Kevin Rutter
 */
public class Display3_Rutter extends javax.swing.JFrame {

	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_30";
	public static final String LOGIN_NAME = "csc371_30";
	public static final String PASSWORD = "Password30";
	// Make sure and use the java.sql imports.
	protected Connection m_dbConn = null;
	
    /**
     * Creates new form DisplayThree 
     * @throws SQLException 
     */
    public Display3_Rutter() throws SQLException
    {
		m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);

        initComponents();
    }

    public void getDBinfo() throws SQLException
    {
    	player = "Diplosaur115";
    	numResources = 0;
    	numFactories = 0;
    	numShipyards = 0;
    	
		String selectData = new String("SELECT Resource_Amt FROM PLANET WHERE P_NAME=?");
		PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
		stmt.setString(1, player);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			numResources += rs.getInt("Resource_Amt");
		}
		
		selectData = new String("SELECT COUNT(ID) FROM FACTORY WHERE P_ID = (SELECT ID FROM PLANET WHERE P_NAME=?)");
		stmt = m_dbConn.prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		numFactories = rs.getRow();
    	
		selectData = new String("SELECT COUNT(ID) FROM SHIPYARD WHERE P_ID = (SELECT ID FROM PLANET WHERE P_NAME=?)");
		stmt = m_dbConn.prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		numShipyards = rs.getRow();
		
        jLabelNumFactories.setText("" + numFactories);
        jLabelNumResources.setText("" + numResources);
        jLabelNumShipyards.setText("" + numShipyards);
    }
    
    public void applyDBchanges() throws SQLException
    {
    	String[] factoryIDs = new String[100];
    	int[] alloc = new int[100];
		String selectData = new String("SELECT ID, Bauble_Alloc FROM FACTORY WHERE P_ID = (SELECT ID FROM PLANET WHERE P_NAME=?)");
		PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
		stmt.setString(1, player);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next())
		{
			factoryIDs[i] = rs.getString("ID");
			alloc[i] = rs.getInt("Bauble_Alloc");
			i++;
		}
		
		int factoriesOwned = i;
		int addTo = allocBaubles / factoriesOwned;
		
		for (i = 0; i < factoriesOwned; i++)
		{
			String updateData = new String("UPDATE FACTORY SET Bauble_Alloc = ? WHERE ID = ?");
			stmt = m_dbConn.prepareStatement(updateData);
			stmt.setString(2, factoryIDs[i]);
			stmt.setInt(1, alloc[i] + addTo);
			stmt.executeUpdate();
		}
		
    	String[] shipyardIDs = new String[100];
    	int[] currentCruiser = new int[100];
    	int[] currentCargo = new int[100];
		selectData = new String("call getShipyards(?)");
		stmt = m_dbConn.prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		i = 0;
		while (rs.next())
		{
			shipyardIDs[i] = rs.getString("ID");
			currentCruiser[i] = rs.getInt("Cruiser_Alloc");
			currentCargo[i] = rs.getInt("Cargo_Alloc");
			i++;
		}
		
		int shipyardsOwned = i;
		int addToCruiser = allocCruisers / shipyardsOwned;
		int addToCargo = allocCargo / shipyardsOwned;
		
		for (i = 0; i < shipyardsOwned; i++)
		{
			String updateData = new String("UPDATE SHIPYARD SET Cruiser_Alloc = ?, Cargo_Alloc = ? WHERE ID = ?");
			stmt = m_dbConn.prepareStatement(updateData);
			stmt.setString(3, shipyardIDs[i]);
			stmt.setInt(1, currentCruiser[i] + addToCruiser);
			stmt.setInt(2, currentCargo[i] + addToCargo);
			stmt.executeUpdate();
		}
		
    	String[] planetIDs = new String[100];
    	int[] planetResources = new int[100];
		selectData = new String("SELECT ID, Resource_Amt FROM PLANET WHERE P_NAME = ?");
		stmt = m_dbConn.prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		i = 0;
		while (rs.next())
		{
			planetIDs[i] = rs.getString("ID");
			planetResources[i] = rs.getInt("Resource_Amt");
			i++;
		}
		
		int planetsOwned = i;
		int removeFrom = (allocBaubles + allocCruisers + allocCargo) / planetsOwned;
		
		for (i = 0; i < planetsOwned; i++)
		{
			String updateData = new String("UPDATE PLANET SET Resource_Amt = ? WHERE ID = ?");
			stmt = m_dbConn.prepareStatement(updateData);
			stmt.setString(2, planetIDs[i]);
			stmt.setInt(1, planetResources[i] - removeFrom);
			stmt.executeUpdate();
		}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws SQLException 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() throws SQLException {
    	
        jLabelTitle = new javax.swing.JLabel();
        jLabelFactories = new javax.swing.JLabel();
        jSpinnerBaubles = new javax.swing.JSpinner();
        jLabelResources = new javax.swing.JLabel();
        jLabelShipyards = new javax.swing.JLabel();
        jLabelAllocateTitle = new javax.swing.JLabel();
        jLabelAllocateBaubles = new javax.swing.JLabel();
        jLabelAllocateCruisers = new javax.swing.JLabel();
        jLabelAllocateCargo = new javax.swing.JLabel();
        jSpinnerCruisers = new javax.swing.JSpinner();
        jSpinnerCargo = new javax.swing.JSpinner();
        jButtonConfirm = new javax.swing.JButton();
        jLabelNumFactories = new javax.swing.JLabel();
        jLabelNumResources = new javax.swing.JLabel();
        jLabelNumShipyards = new javax.swing.JLabel();
        
        jSpinnerBaubles.addChangeListener(listener);
        jSpinnerCruisers.addChangeListener(listener);
        jSpinnerCargo.addChangeListener(listener);
        
        jButtonConfirm.addActionListener(actionListener);

    	getDBinfo();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setText(player + "'s Production");

        jLabelFactories.setText("Factories:");

        jLabelResources.setText("Resources:");

        jLabelShipyards.setText("Shipyards:");

        jLabelAllocateTitle.setText("Allocate Resources");

        jLabelAllocateBaubles.setText("For Baubles:");

        jLabelAllocateCruisers.setText("For Cruiser Ships:");

        jLabelAllocateCargo.setText("For Cargo Ships:");

        jButtonConfirm.setText("Confirm Resource Allocation");

        jLabelNumFactories.setText("" + numFactories);

        jLabelNumResources.setText("" + numResources);

        jLabelNumShipyards.setText("" + numShipyards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle)
                    .addComponent(jLabelAllocateTitle)
                    .addComponent(jButtonConfirm)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelShipyards)
                                .addComponent(jLabelResources)
                                .addComponent(jLabelFactories))
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNumShipyards)
                                .addComponent(jLabelNumResources)
                                .addComponent(jLabelNumFactories)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabelAllocateCargo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSpinnerCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabelAllocateCruisers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSpinnerCruisers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabelAllocateBaubles)
                                .addGap(59, 59, 59)
                                .addComponent(jSpinnerBaubles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelResources)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFactories)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelShipyards)
                        .addGap(36, 36, 36)
                        .addComponent(jLabelAllocateTitle)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAllocateBaubles)
                            .addComponent(jSpinnerBaubles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAllocateCruisers)
                            .addComponent(jSpinnerCruisers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAllocateCargo)
                            .addComponent(jSpinnerCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConfirm))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNumResources)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNumFactories)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNumShipyards)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Display3_Rutter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display3_Rutter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display3_Rutter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display3_Rutter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new Display3_Rutter().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

    // Variables declaration - do not modify                     
	private String player = "Diplosaur115";
	private int numResources = 10;
	private int numFactories = 10;
	private int numShipyards = 10;
    
	private int allocBaubles = 0;
	private int allocCruisers = 0;
	private int allocCargo = 0;
	
	ChangeListener listener = new ChangeListener()
	{
		public void stateChanged(ChangeEvent e)
		{
			if (e.getSource().equals(jSpinnerBaubles))
			{
				allocBaubles = (int) jSpinnerBaubles.getValue();
			}
			else if (e.getSource().equals(jSpinnerCruisers))
			{
				allocCruisers = (int) jSpinnerCruisers.getValue();
			}
			else if (e.getSource().equals(jSpinnerCargo))
			{
				allocCargo = (int) jSpinnerCargo.getValue();
			}
		}
	};
	
	ActionListener actionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
				applyDBchanges();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				getDBinfo();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelNumResources;
    private javax.swing.JLabel jLabelNumShipyards;
    private javax.swing.JLabel jLabelFactories;
    private javax.swing.JLabel jLabelResources;
    private javax.swing.JLabel jLabelShipyards;
    private javax.swing.JLabel jLabelAllocateTitle;
    private javax.swing.JLabel jLabelAllocateBaubles;
    private javax.swing.JLabel jLabelAllocateCruisers;
    private javax.swing.JLabel jLabelAllocateCargo;
    private javax.swing.JLabel jLabelNumFactories;
    private javax.swing.JSpinner jSpinnerBaubles;
    private javax.swing.JSpinner jSpinnerCruisers;
    private javax.swing.JSpinner jSpinnerCargo;
    // End of variables declaration                   
}