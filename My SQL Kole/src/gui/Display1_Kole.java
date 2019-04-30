package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyDB;

/**
 * Displays the amount of money the player has, costs for each type of building
 * (Factory, Mine, Research Center, and ShipYard), and allows the player to buy
 * additional buildings.
 * @author Jacob Kole
 */
public class Display1_Kole extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L; // default serial code
	
	// Variables declaration for java swing                     
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButton_Close;
    private javax.swing.JButton jButton_Purchase;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_BuildingDescrip;
    private javax.swing.JLabel jLabel_BuildingName;
    private javax.swing.JLabel jLabel_CurrencyAmt;
    private javax.swing.JLabel jLabel_FCost;
    private javax.swing.JLabel jLabel_MCost;
    private javax.swing.JLabel jLabel_RCCost;
    private javax.swing.JLabel jLabel_SYCost;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton_F;
    private javax.swing.JRadioButton jRadioButton_M;
    private javax.swing.JRadioButton jRadioButton_RC;
    private javax.swing.JRadioButton jRadioButton_SY;
    private javax.swing.JScrollPane jScrollPane_BuildingDescription;
    private javax.swing.JTextArea jTextArea_BuildingDescription;
    // End of variables declaration         
    
    // keeping track of variables as they change
    // used for action listeners
    private int money = 0;
    private int updatedMoney = 0;
    private int deduction = 0;
    private String statement;
    private String buildingID = "000";
    private String planetID = "00x00";
    
    // variables to display the cost of each building type
    static int factoryCost;
    static int mineCost;
    static int researchCenterCost;
    static int shipYardCost;
    
    // variables for the database
    static MyDB db;
	private static String name;
    
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
    	Display1_Kole d1 = new Display1_Kole("guy"); // don't have a login to grab a name, so we're just gonna pick on "guy"
    	d1.runner();
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
            java.util.logging.Logger.getLogger(Display1_Kole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display1_Kole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display1_Kole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display1_Kole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display1_Kole(name).setVisible(true);
            }
        });
    }

    /**
     * Creates new form DisplayFrame. The parameter is
     * for a possible login name that gets passed in from
     * what creates the display.
     */
    public Display1_Kole(String playerName) {
    	name = playerName;
    	//d1db = new Display1DBStatements(playerName);
    	
    	try {
			db = new MyDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	try {
			//db.selectPlayerName();
			selectMoney(name);
			System.out.println("Name: " + name + " Money :" + money);
			factoryCost = getBuildingCost("FACTORY");
			mineCost = getBuildingCost("MINE");
			researchCenterCost = getBuildingCost("RESEARCH_CENTER");
			shipYardCost = getBuildingCost("SHIPYARD");
			//d1db.updateMoney(playerName, 900000); // sets our player's money very high for testing
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */          
    private void initComponents() {
    	// initialization of components
        buttonGroup = new javax.swing.ButtonGroup(); // connects the selectable buttons for each building
        jPanel2 = new javax.swing.JPanel(); // panel to hold the jframe and all its components
        jLabel_BuildingName = new javax.swing.JLabel(); // label for the building name above the description
        jLabel_CurrencyAmt = new javax.swing.JLabel(); // label for the currency amount the player currently has
        jLabel_BuildingDescrip = new javax.swing.JLabel(); // label for the building description itself
        jLabel_FCost = new javax.swing.JLabel();
        jLabel_MCost = new javax.swing.JLabel();
        jLabel_RCCost = new javax.swing.JLabel();
        jLabel_SYCost = new javax.swing.JLabel();
        jRadioButton_F = new javax.swing.JRadioButton(); // select button for Factory
        jRadioButton_M = new javax.swing.JRadioButton(); // select button for Mine
        jRadioButton_RC = new javax.swing.JRadioButton(); // select button for Research Center
        jRadioButton_SY = new javax.swing.JRadioButton(); // select button for ShipYard
        jButton_Purchase = new javax.swing.JButton(); // button for purchasing a building
        jButton_Close = new javax.swing.JButton(); // button to close without purchasing
        jScrollPane_BuildingDescription = new javax.swing.JScrollPane(); // in case the building description needs to scroll
        jTextArea_BuildingDescription = new javax.swing.JTextArea(); // the text area for the building description
        jLabel2 = new javax.swing.JLabel(); // this holds the background image

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // jframe will exit on close
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // jframe will dispose on close
        setPreferredSize(new java.awt.Dimension(1065, 785)); // sets the prefered size for the jframe window

        jPanel2.setLayout(null); // lets me layer buttons, labels, etc on top of each other without intrusion

        // set the specifications for the Building Name jlabel
        jLabel_BuildingName.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel_BuildingName.setForeground(new java.awt.Color(255, 253, 208));
        jLabel_BuildingName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel_BuildingName); // add the label to the panel
        jLabel_BuildingName.setBounds(550, 90, 450, 50);

        // set the specifications for the Currency Amount jlabel
        jLabel_CurrencyAmt.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_CurrencyAmt.setForeground(new java.awt.Color(249, 242, 93));
        jLabel_CurrencyAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_CurrencyAmt.setText("" + money);
        jPanel2.add(jLabel_CurrencyAmt); // add the label to the panel
        jLabel_CurrencyAmt.setBounds(280, 550, 210, 30);

        // set the specifications for the Building Description jlabel
        jLabel_BuildingDescrip.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_BuildingDescrip.setForeground(new java.awt.Color(255, 253, 208));
        jLabel_BuildingDescrip.setText(name);
        jPanel2.add(jLabel_BuildingDescrip); // add the label to the panel
        jLabel_BuildingDescrip.setBounds(90, 550, 190, 30);

        // set the specifications for the Factory Cost jlabel
        jLabel_FCost.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_FCost.setForeground(new java.awt.Color(249, 242, 93));
        jLabel_FCost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_FCost.setText("" + factoryCost);
        jPanel2.add(jLabel_FCost); // add the label to the panel
        jLabel_FCost.setBounds(230, 120, 210, 20);

        // set the specifications for the Mine Cost jlabel
        jLabel_MCost.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_MCost.setForeground(new java.awt.Color(249, 242, 93));
        jLabel_MCost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_MCost.setText("" + mineCost);
        jPanel2.add(jLabel_MCost); // add the label to the panel
        jLabel_MCost.setBounds(230, 240, 210, 20);

        // set the specifications for the Research Center Cost jlabel
        jLabel_RCCost.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_RCCost.setForeground(new java.awt.Color(249, 242, 93));
        jLabel_RCCost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_RCCost.setText("" + researchCenterCost);
        jPanel2.add(jLabel_RCCost); // add the label to the panel
        jLabel_RCCost.setBounds(230, 360, 210, 20);

        // set the specifications for the ShipYard Cost jlabel
        jLabel_SYCost.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_SYCost.setForeground(new java.awt.Color(249, 242, 93));
        jLabel_SYCost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_SYCost.setText("" + shipYardCost);
        jPanel2.add(jLabel_SYCost); // add the label to the panel
        jLabel_SYCost.setBounds(230, 480, 210, 20);
        
        // set the specifications for the Factory jradiobutton
        jRadioButton_F.setBackground(new java.awt.Color(0, 2, 40));
        buttonGroup.add(jRadioButton_F); // add the button to a button group to link with the other buttons
        jRadioButton_F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_FActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton_F); // add the button to the panel
        jRadioButton_F.setBounds(450, 100, 40, 40);

        // set the specifications for the Mine jradiobutton
        jRadioButton_M.setBackground(new java.awt.Color(0, 2, 40));
        buttonGroup.add(jRadioButton_M); // add the button to a button group to link with the other buttons
        jRadioButton_M.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_MActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton_M); // add the button to the panel
        jRadioButton_M.setBounds(450, 220, 40, 40);

        // set the specifications for the Research Center jradiobutton
        jRadioButton_RC.setBackground(new java.awt.Color(0, 2, 40));
        buttonGroup.add(jRadioButton_RC); // add the button to a button group to link with the other buttons
        jRadioButton_RC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton_RC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_RCActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton_RC); // add the button to the panel
        jRadioButton_RC.setBounds(450, 340, 40, 40);

        // set the specifications for the ShipYard jradiobutton
        jRadioButton_SY.setBackground(new java.awt.Color(0, 2, 40));
        buttonGroup.add(jRadioButton_SY); // add the button to a button group to link with the other buttons
        jRadioButton_SY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton_SY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_SYActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton_SY); // add the button to the panel
        jRadioButton_SY.setBounds(450, 460, 40, 40);

        // set the specifications for the Purchase jbutton
        jButton_Purchase.setBackground(new java.awt.Color(0, 204, 102));
        jButton_Purchase.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton_Purchase.setText("Purchase");
        jButton_Purchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PurchaseActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_Purchase); // add the button to the panel
        jButton_Purchase.setBounds(800, 650, 180, 40);

        // set the specifications for the Close jbutton
        jButton_Close.setBackground(new java.awt.Color(102, 102, 102));
        jButton_Close.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton_Close.setText("Close");
        jButton_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CloseActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_Close); // add the button to the panel
        jButton_Close.setBounds(560, 650, 180, 40);

        // set the specifications for the Building Description jscrollpane
        jScrollPane_BuildingDescription.setBorder(null);
        jScrollPane_BuildingDescription.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 11)); // NOI18N

        // set the specifications for the Building Description jtextarea
        jTextArea_BuildingDescription.setEditable(false);
        jTextArea_BuildingDescription.setBackground(new java.awt.Color(0, 2, 40));
        jTextArea_BuildingDescription.setColumns(20);
        jTextArea_BuildingDescription.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jTextArea_BuildingDescription.setForeground(new java.awt.Color(255, 253, 208));
        jTextArea_BuildingDescription.setLineWrap(true);
        jTextArea_BuildingDescription.setRows(5);
        jTextArea_BuildingDescription.setWrapStyleWord(true);
        jScrollPane_BuildingDescription.setViewportView(jTextArea_BuildingDescription);

        jPanel2.add(jScrollPane_BuildingDescription); // add the pane to the panel
        jScrollPane_BuildingDescription.setBounds(550, 160, 440, 360);

//      jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/display1/resources/Display One BG smaller.png"))); // NOI18N
        // fills a label with the png image for the display background
        jLabel2.setIcon(new javax.swing.ImageIcon("display1.png")); // NOI18N
        jPanel2.add(jLabel2); // adds the label to the panel
        jLabel2.setBounds(0, 0, 1050, 750);

        // layout specifications for the overall jpanel holding everything
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // pack all components together
        pack();
    }                 

    /**
     * When Factory radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_FActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        jLabel_BuildingName.setText("Factory");
        jTextArea_BuildingDescription.setText("Factories can be used to turn mined resources from Mines"
        		+ " into items called baubles to sell for money.");
        jLabel_CurrencyAmt.setText(money + " > " + (money - factoryCost)); // display money after deduction
        
        // set variables for current building name and amount
        deduction = factoryCost;
        updatedMoney = money - deduction; // calculate the new balance after deduction
        try {
			buildingID = generateID("FACTORY"); // generate a new ID for the factory
			planetID = getPlanetID(name); // get the planet ID owned by the player to apply to the building
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // the custom statement for a new factory
        statement = "INSERT INTO FACTORY(ID, Cost, Maintenance_Cost, Bauble_Alloc, P_ID) "
        		+ "VALUES ('" + buildingID + "', " + factoryCost + ", 500, 0, '" + planetID + "')";
    }                                              

    /**
     * When Mine radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_MActionPerformed(java.awt.event.ActionEvent evt) {                                               
        jLabel_BuildingName.setText("Mine");
        jTextArea_BuildingDescription.setText("Mines have the ability to gather raw resources the player"
        		+ " may use to build ships at a ShipYard, items to sell at a Factory, or research technology"
        		+ " at a Research Center.");
        jLabel_CurrencyAmt.setText(money + " > " + (money - mineCost)); // display money after deduction
        
        // set variables for current building name and amount
        deduction = mineCost;
        updatedMoney = money - deduction; // calculate the new balance after deduction
        try {
			buildingID = generateID("MINE"); // generate a new ID for the mine
			planetID = getPlanetID(name); // get the planet ID owned by the player to apply to the building
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // the custom statement for a new mine
        statement = "INSERT INTO MINE(ID, Cost, Maintenance_Cost, P_ID) "
        		+ "VALUES ('" + buildingID + "', " + mineCost + ", 10, '" + planetID + "')";
    }                                              

    /**
     * When Research Center radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_RCActionPerformed(java.awt.event.ActionEvent evt) {                                                
        jLabel_BuildingName.setText("Research Center");
        jTextArea_BuildingDescription.setText("Research Centers use mined resources to improve existing technologies.");
        jLabel_CurrencyAmt.setText(money + " > " + (money - researchCenterCost)); // display money after deduction
        
        // set variables for current building name and amount
        deduction = researchCenterCost;
        updatedMoney = money - deduction; // calculate the new balance after deduction
        try {
			buildingID = generateID("RESEARCH_CENTER"); // generate a new ID for the research center
			planetID = getPlanetID(name); // get the planet ID owned by the player to apply to the building
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // the custom statement for a new research center
        statement = "INSERT INTO RESEARCH_CENTER(ID, Cost, Maintenance_Cost, P_ID) "
        		+ "VALUES ('" + buildingID + "', " + researchCenterCost + ", 2500, '" + planetID + "')";
    }                                               

    /**
     * When ShipYard radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_SYActionPerformed(java.awt.event.ActionEvent evt) {                                                
        jLabel_BuildingName.setText("ShipYard");
        jTextArea_BuildingDescription.setText("ShipYards use mined resources to build additional ships.");
        jLabel_CurrencyAmt.setText(money + " > " + (money - shipYardCost)); // display money after deduction
        
        // set variables for current building name and amount
        deduction = shipYardCost;
        updatedMoney = money - deduction; // calculate the new balance after deduction
        try {
			buildingID = generateID("SHIPYARD"); // generate a new ID for the shipyard
			planetID = getPlanetID(name); // get the planet ID owned by the player to apply to the building
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // the custom statement for a new shipyard
        statement = "INSERT INTO SHIPYARD(ID, Cost, Maintenance_Cost, Cargo_Alloc, Cruiser_Alloc, P_ID) "
        		+ "VALUES ('" + buildingID + "', " + shipYardCost + ", 50, 0, 0, '" + planetID + "')";
    }                                               

    /**
     * When Purchase button is pressed, it should subtract the purchased building amount from
     * the player's wallet and add the building to their inventory. Disposes the frame when done.
     * @param evt on button press
     */
    private void jButton_PurchaseActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        
    	// make sure the player can actually afford the building being purchased
    	if(money >= 0) {
			// subtract purchased amount from wallet and adds new player-assigned building
			// to be placed
			if (statement != null) {
				try {
					updateMoney(name, updatedMoney);
					money = updatedMoney;
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// update a table dependant on which building was selected
				try {
					insert(statement, money);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// allows for repeated clicking on the purchase button
			if (deduction == factoryCost) {
				jRadioButton_FActionPerformed(evt);
			} else if (deduction == mineCost) {
				jRadioButton_MActionPerformed(evt);
			} else if (deduction == researchCenterCost) {
				jRadioButton_RCActionPerformed(evt);
			} else if (deduction == shipYardCost) {
				jRadioButton_SYActionPerformed(evt);
			}
    	} else {
    		System.out.println("Not enough currency.");
    	}
    }                                                

    /**
     * Disposes the frame without purchasing anything.
     * @param evt on button press
     */
    private void jButton_CloseActionPerformed(java.awt.event.ActionEvent evt) {                                              
        this.dispose();
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