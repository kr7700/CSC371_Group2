package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db.MyDB;

/**
 * Displays a player's production: their resources, # factories and # shipyards.
 * Also allows a player to allocate their resources into factories and shipyards to build baubles or ships.
 * @author Kevin Rutter
 */
public class Display3_Rutter extends javax.swing.JFrame
{
	
	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	// variables for the database
	static MyDB db;
	private static String player;

    // variables for resources/buildings from db                   
	private int numResources = 0;
	private int numFactories = 0;
	private int numShipyards = 0;
    
	// variables for keeping track of how many resources are being allocated
	private int allocBaubles = 0;
	private int allocCruisers = 0;
	private int allocCargo = 0;
	
	// listeners for buttons/spinners
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
				e2.printStackTrace();
			}
			try {
				getDBinfo();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	};
	
	// ui variables
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
	
    /**
     * This can be used as the main if you don't want to launch from the menu.
     * @param args
     * @throws SQLException	shouldn't throw
     */
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws SQLException
    {
    	Display3_Rutter d3 = new Display3_Rutter("Diplosaur115");
    	d3.runner();
    }
    
    /**
     * Used to create this JFrame for the resource allocation UI
     * @param playerName	The player that is using this UI
     * @throws SQLException	shouldn't throw
     */
    public Display3_Rutter(String playerName) throws SQLException
    {
    	player = playerName;
    	
    	try {
			db = new MyDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
        initComponents();
    }

   /**
    * Gets the resources and buildings from the DB.
    * @throws SQLException	shouldn't throw
    */
    public void getDBinfo() throws SQLException
    {
    	numResources = 0;
    	numFactories = 0;
    	numShipyards = 0;
    	
    	// get the resources from all the planets this player owns
		String selectData = new String("SELECT Resource_Amt FROM PLANET WHERE P_NAME=?");
		PreparedStatement stmt = db.getConn().prepareStatement(selectData);
		stmt.setString(1, player);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			numResources += rs.getInt("Resource_Amt");
		}
		
		// get the number of factories this player owns
		selectData = new String("SELECT COUNT(*) FROM FACTORY WHERE P_ID = (SELECT ID FROM PLANET WHERE P_NAME=?)");
		stmt = db.getConn().prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		rs.next();
		numFactories = rs.getInt(1);
    	
		// get the number of shipyards this player owns
		selectData = new String("SELECT COUNT(*) FROM SHIPYARD WHERE P_ID = (SELECT ID FROM PLANET WHERE P_NAME=?)");
		stmt = db.getConn().prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		rs.next();
		numShipyards = rs.getInt(1);
		
		// update the jlabels for the info retrieved
        jLabelNumFactories.setText("" + numFactories);
        jLabelNumResources.setText("" + numResources);
        jLabelNumShipyards.setText("" + numShipyards);
    }
    
    /**
     * Update the DB with the resources allocated
     * @throws SQLException	shouldn't throw
     */
    public void applyDBchanges() throws SQLException
    {
    	// shouldn't be able to allocate more resources than you have
    	if ((allocCruisers + allocBaubles + allocCargo) > numResources)
    	{
    		System.out.println("ERROR: Not enough resources. Cannot allocate requested resources");
    		return;
    	}
    	
    	if (allocCruisers < 0 || allocBaubles < 0 || allocCargo < 0)
    	{
    		System.out.println("ERROR: Cannot allocate negative resources");
    		return;
    	}
    	
    	if ((allocBaubles > 0) && (numFactories == 0))
    	{
    		System.out.println("ERROR: Not enough factories. Cannot allocate requested resources");
    		return;
    	}
    	
    	if (((allocCruisers + allocCargo) > 0) && (numShipyards == 0))
    	{
    		System.out.println("ERROR: Not enough shipyards. Cannot allocate requested resources");
    		return;
    	}
    	
    	// get all the factories that this player owns
    	String[] factoryIDs = new String[100];
    	int[] factory_alloc = new int[100];
		String selectData = new String("SELECT ID, Bauble_Alloc FROM FACTORY WHERE P_ID = (SELECT ID FROM PLANET WHERE P_NAME=?)");
		PreparedStatement stmt = db.getConn().prepareStatement(selectData);
		stmt.setString(1, player);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next())
		{
			factoryIDs[i] = rs.getString("ID");
			factory_alloc[i] = rs.getInt("Bauble_Alloc");
			i++;
		}
		
		// distribute the allocated resources evenly across all factories
		int factoriesOwned = i;
		int addTo = allocBaubles / factoriesOwned;
		
		// update all the factories with the new amount of resources allocated for baubles
		for (i = 0; i < factoriesOwned; i++)
		{
			String updateData = new String("UPDATE FACTORY SET Bauble_Alloc = ? WHERE ID = ?");
			stmt = db.getConn().prepareStatement(updateData);
			stmt.setString(2, factoryIDs[i]);
			stmt.setInt(1, factory_alloc[i] + addTo);
			stmt.executeUpdate();
		}
		
		// get all the shipyards the player owns
    	String[] shipyardIDs = new String[100];
    	int[] currentCruiser = new int[100];
    	int[] currentCargo = new int[100];
		selectData = new String("call getShipyards(?)");
		stmt = db.getConn().prepareStatement(selectData);
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
		
		// evenly distribute resources between the shipyards
		int shipyardsOwned = i;
		int addToCruiser = allocCruisers / shipyardsOwned;
		int addToCargo = allocCargo / shipyardsOwned;
		
		// update the shipyards with their new allocated resource
		for (i = 0; i < shipyardsOwned; i++)
		{
			String updateData = new String("UPDATE SHIPYARD SET Cruiser_Alloc = ?, Cargo_Alloc = ? WHERE ID = ?");
			stmt = db.getConn().prepareStatement(updateData);
			stmt.setString(3, shipyardIDs[i]);
			stmt.setInt(1, currentCruiser[i] + addToCruiser);
			stmt.setInt(2, currentCargo[i] + addToCargo);
			stmt.executeUpdate();
		}
		
		// get all the planets the player owns
    	String[] planetIDs = new String[100];
    	int[] planetResources = new int[100];
		selectData = new String("SELECT ID, Resource_Amt FROM PLANET WHERE P_NAME = ?");
		stmt = db.getConn().prepareStatement(selectData);
		stmt.setString(1, player);
		rs = stmt.executeQuery();
		i = 0;
		while (rs.next())
		{
			planetIDs[i] = rs.getString("ID");
			planetResources[i] = rs.getInt("Resource_Amt");
			i++;
		}
		
		// evenly subtract resources from all planets
		int planetsOwned = i;
		int removeFrom = (allocBaubles + allocCruisers + allocCargo) / planetsOwned;
		
		// update the planets with their new resource counts
		for (i = 0; i < planetsOwned; i++)
		{
			String updateData = new String("UPDATE PLANET SET Resource_Amt = ? WHERE ID = ?");
			stmt = db.getConn().prepareStatement(updateData);
			stmt.setString(2, planetIDs[i]);
			stmt.setInt(1, planetResources[i] - removeFrom);
			stmt.executeUpdate();
		}
    }
    
    /**
     * Initialize all the UI components.
     * @throws SQLException shouldn't throw
     */
    private void initComponents() throws SQLException
    {
        //this.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18));
        //this.setBackground(new java.awt.Color(249, 242, 93));
    	
    	// create all components
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
        
        // assign listeners to the components
        jSpinnerBaubles.addChangeListener(listener);
        jSpinnerCruisers.addChangeListener(listener);
        jSpinnerCargo.addChangeListener(listener);
        
        jButtonConfirm.addActionListener(actionListener);

        // get the info from the DB
    	getDBinfo();
    	
    	// change spinner size slightly
    	Dimension spinnerSize = new Dimension(60, 28);
    	jSpinnerBaubles.setPreferredSize(spinnerSize);
    	jSpinnerCruisers.setPreferredSize(spinnerSize);
    	jSpinnerCargo.setPreferredSize(spinnerSize);
    	
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // set text for all components
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

        // all the formatting stuff generated using NetBeans
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
    }                     

    /**
     * Used by the main menu to run this UI.
     */
    public static void runner()
    {
        // Set the Nimbus look and feel
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

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new Display3_Rutter(player).setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
    }                
}