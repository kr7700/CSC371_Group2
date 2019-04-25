package gui;

/**
 * Displays the amount of money the player has, costs for each type of building
 * (Factory, Mine, Research Center, and ShipYard), and allows the player to buy
 * additional buildings.
 * @author Jacob Kole
 */
public class Display1_Kole extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L; // default serial code
	
	// Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButton_Close;
    private javax.swing.JButton jButton_Purchase;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_BuildingDescrip;
    private javax.swing.JLabel jLabel_BuildingName;
    private javax.swing.JLabel jLabel_CurrencyAmt;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton_F;
    private javax.swing.JRadioButton jRadioButton_M;
    private javax.swing.JRadioButton jRadioButton_RC;
    private javax.swing.JRadioButton jRadioButton_SY;
    private javax.swing.JScrollPane jScrollPane_BuildingDescription;
    private javax.swing.JTextArea jTextArea_BuildingDescription;
    // End of variables declaration         
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
                new Display1_Kole().setVisible(true);
            }
        });
    }

    /**
     * Creates new form DisplayFrame
     */
    public Display1_Kole() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup(); // connects the selectable buttons for each building
        jPanel2 = new javax.swing.JPanel(); // panel to hold the jframe and all its components
        jLabel_BuildingName = new javax.swing.JLabel(); // label for the building name above the description
        jLabel_CurrencyAmt = new javax.swing.JLabel(); // label for the currency amount the player currently has
        jLabel_BuildingDescrip = new javax.swing.JLabel(); // label for the building description itself
        jRadioButton_F = new javax.swing.JRadioButton(); // select button for Factory
        jRadioButton_M = new javax.swing.JRadioButton(); // select button for Mine
        jRadioButton_RC = new javax.swing.JRadioButton(); // select button for Research Center
        jRadioButton_SY = new javax.swing.JRadioButton(); // select button for ShipYard
        jButton_Purchase = new javax.swing.JButton(); // button for purchasing a building
        jButton_Close = new javax.swing.JButton(); // button to close without purchasing
        jScrollPane_BuildingDescription = new javax.swing.JScrollPane(); // in case the building description needs to scroll
        jTextArea_BuildingDescription = new javax.swing.JTextArea(); // the text area for the building description
        jLabel2 = new javax.swing.JLabel(); // this holds the background image

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // jframe will exit on close
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
        jLabel_CurrencyAmt.setText("[Currency Amt]");
        jPanel2.add(jLabel_CurrencyAmt); // add the label to the panel
        jLabel_CurrencyAmt.setBounds(280, 550, 210, 30);

        // set the specifications for the Building Description jlabel
        jLabel_BuildingDescrip.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel_BuildingDescrip.setForeground(new java.awt.Color(255, 253, 208));
        jLabel_BuildingDescrip.setText("[Player Name]");
        jPanel2.add(jLabel_BuildingDescrip); // add the label to the panel
        jLabel_BuildingDescrip.setBounds(90, 550, 190, 30);

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
        jLabel_CurrencyAmt.setText("Wallet - 10000");
        // set variables for current building name and amount
    }                                              

    /**
     * When Mine radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_MActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        jLabel_BuildingName.setText("Mine");
        jTextArea_BuildingDescription.setText("Mines have the ability to gather raw resources the player"
        		+ " may use to build ships at a ShipYard, items to sell at a Factory, or research technology"
        		+ " at a Research Center.");
        jLabel_CurrencyAmt.setText("Wallet - 500");
        // set variables for current building name and amount
    }                                              

    /**
     * When Research Center radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_RCActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        jLabel_BuildingName.setText("Research Center");
        jTextArea_BuildingDescription.setText("Research Centers use mined resources to improve existing technologies.");
        jLabel_CurrencyAmt.setText("Wallet - 45000");
        // set variables for current building name and amount
    }                                               

    /**
     * When ShipYard radiobutton is pressed, it should edit the Building Name and Building Description,
     * also setting a variable for the building to be purchased if the Purchase button is pressed.
     * @param evt on button press
     */
    private void jRadioButton_SYActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        jLabel_BuildingName.setText("ShipYard");
        jTextArea_BuildingDescription.setText("ShipYards use mined resources to build additional ships.");
        jLabel_CurrencyAmt.setText("Wallet - 1500");
        // set variables for current building name and amount
    }                                               

    /**
     * When Purchase button is pressed, it should subtract the purchased building amount from
     * the player's wallet and add the building to their inventory. Disposes the frame when done.
     * @param evt on button press
     */
    private void jButton_PurchaseActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        // subtract purchased amount from wallet and add building
        // to player's inventory
        this.dispose();
    }                                                

    /**
     * Disposes the frame without purchasing anything.
     * @param evt on button press
     */
    private void jButton_CloseActionPerformed(java.awt.event.ActionEvent evt) {                                              
        this.dispose();
    }                                              
}