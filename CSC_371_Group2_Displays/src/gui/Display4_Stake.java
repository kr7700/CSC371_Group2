package gui;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.MyDB;

/**
 * Generates the direct access and modification gui
 * Partially built by netbeans
 * @author andrew
 */
public class Display4_Stake extends javax.swing.JFrame 
{
	static MyDB db = null; //allows for easy access to database connection
	ArrayList<bundledTuple> tupleList; //holds the list of tuples from the currently selected table
	ArrayList<String> attributeList; //holds the list of attributes for the currently selected table
	
    /**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form DBDirectInterfaceGUI
     */
    public Display4_Stake() {
        try {
			initComponents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tupleScrollList = new javax.swing.JList<>();
        rowAdditionTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        templateLabel = new javax.swing.JLabel();
        attributeComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        updateTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tableComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        insertButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 2, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 450));
        
        tableComboBox.setBackground(new java.awt.Color(24, 24, 24));
        tableComboBox.setForeground(new java.awt.Color(255, 253, 208));
        ArrayList<String> tableList = new ArrayList<String>();
        DatabaseMetaData md = db.getConn().getMetaData(); 
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
          tableList.add(rs.getString(3));
        }
        tableComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(tableList.toArray(new String[1])));
        tableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					tableSelected(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        tableSelected(null); //initially preps the attribute list and other globals 
        
        attributeComboBox.setBackground(new java.awt.Color(24, 24, 24));
        attributeComboBox.setForeground(new java.awt.Color(255, 253, 208));
        ArrayList<String> attributeList = new ArrayList<String>();
        PreparedStatement stmt = db.getConn().prepareStatement("SELECT * FROM " + "ADMINISTRATOR");
		rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // The column count starts from 1
        for (int i = 1; i <= columnCount; i++ ) {
          String name = rsmd.getColumnName(i);
          attributeList.add(name);
        }
        attributeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(attributeList.toArray(new String[1])));
        attributeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributeSelected(evt);
            }
        });

        tupleScrollList.setBackground(new java.awt.Color(24, 24, 24));
        tupleScrollList.setForeground(new java.awt.Color(255, 253, 208));
        tupleScrollList.setModel(buildTupleListModel());
        jScrollPane1.setViewportView(tupleScrollList);

        rowAdditionTextField.setBackground(new java.awt.Color(24, 24, 24));
        rowAdditionTextField.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        rowAdditionTextField.setForeground(new java.awt.Color(255, 253, 208));
        rowAdditionTextField.setText("");

        jLabel1.setBackground(new java.awt.Color(24, 24, 24));
        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 253, 208));
        jLabel1.setText("Add to Selected Table, Follow Format:");

        templateLabel.setBackground(new java.awt.Color(24, 24, 24));
        templateLabel.setForeground(new java.awt.Color(255, 253, 208));
        templateLabel.setText(generateTemplate());

        jLabel3.setBackground(new java.awt.Color(24, 24, 24));
        jLabel3.setForeground(new java.awt.Color(255, 253, 208));
        jLabel3.setText("Selected Attribute:");

        updateTextField.setBackground(new java.awt.Color(24, 24, 24));
        updateTextField.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        updateTextField.setForeground(new java.awt.Color(255, 253, 208));
        updateTextField.setText("");

        jLabel4.setBackground(new java.awt.Color(24, 24, 24));
        jLabel4.setForeground(new java.awt.Color(255, 253, 208));
        jLabel4.setText("New Value:"); 

        jLabel5.setBackground(new java.awt.Color(24, 24, 24));
        jLabel5.setForeground(new java.awt.Color(255, 253, 208));
        jLabel5.setText("Select Table:");

        insertButton.setBackground(new java.awt.Color(24, 24, 24));
        insertButton.setForeground(new java.awt.Color(255, 253, 208));
        insertButton.setText("Insert Row");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonPressed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(24, 24, 24));
        updateButton.setForeground(new java.awt.Color(255, 253, 208));
        updateButton.setText("Apply Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonPressed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(24, 24, 24));
        deleteButton.setForeground(new java.awt.Color(255, 253, 208));
        deleteButton.setText("Delete Selected Row");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rowAdditionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(attributeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(templateLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel4)))
                                .addGap(0, 0, 0)))
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attributeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(templateLabel)
                        .addGap(6, 6, 6)
                        .addComponent(rowAdditionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insertButton)))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void insertButtonPressed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void updateButtonPressed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void deleteButtonPressed(java.awt.event.ActionEvent evt) 
    {       
        bundledTuple targetTuple = findTargetTuple(tupleScrollList.getSelectedValue());
        if(targetTuple == null)
        	return;
        
        String stmtString = "DELETE FROM " + tableComboBox.getSelectedItem() + " WHERE";
        int index = 0;
        for(String attribute : attributeList)
        {
        	if (index > 0)
        		stmtString += " AND";
        	
        	if(targetTuple.getDataValue(index) instanceof String)
            	stmtString += " " + attribute + " = '" + targetTuple.getDataValue(index) + "'";
        	else
        		stmtString += " " + attribute + " = " + targetTuple.getDataValue(index);

        	index++;
        }
        stmtString += ";";
        System.out.println(stmtString);
        
//        PreparedStatement stmt = db.getConn().prepareStatement()
    }                                    

    private void tableSelected(java.awt.event.ActionEvent evt) throws SQLException 
    {  
    	//Updates the attribute drop down list
    	attributeList = new ArrayList<String>();
        PreparedStatement stmt = db.getConn().prepareStatement("SELECT * FROM " + tableComboBox.getSelectedItem());
		ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // The column count starts from 1
        for (int i = 1; i <= columnCount; i++ ) {
          String name = rsmd.getColumnName(i);
          attributeList.add(name);
        }
        attributeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(attributeList.toArray(new String[1])));
        
        //Updates the tuple display list
        tupleScrollList.setModel(buildTupleListModel());
        
        //Updates the insertion template
        templateLabel.setText(generateTemplate());
    }                              

    private void attributeSelected(java.awt.event.ActionEvent evt) {                                   
        // TODO add your handling code here:
    }                                  

    /**
     * @param args the command line arguments
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException 
    {
		try {
			db = new MyDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
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
            java.util.logging.Logger.getLogger(Display4_Stake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display4_Stake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display4_Stake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display4_Stake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display4_Stake().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton insertButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> attributeComboBox;
    private javax.swing.JComboBox<String> tableComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel templateLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> tupleScrollList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField rowAdditionTextField;
    private javax.swing.JTextField updateTextField;
    // End of variables declaration    
    
    /**
     * Creates and returns the new tuple list when the active table is changed
     * @return
     * @throws SQLException
     */
    private javax.swing.AbstractListModel<String> buildTupleListModel() throws SQLException
    {
    	//creates statement to select all tuples from active table
    	PreparedStatement tupleRetrieval = db.getConn().prepareStatement("SELECT * FROM " + tableComboBox.getSelectedItem());
    	
    	//executes the statement and retrieves results
    	ResultSet rs = tupleRetrieval.executeQuery();
    	ResultSetMetaData rsmd = rs.getMetaData();
    	int numColumns = rsmd.getColumnCount();
    	
    	//Adds the tuples to a list
    	tupleList = new ArrayList<bundledTuple>();
    	int index = 1;
    	while(rs.next())
    	{
    		bundledTuple tempTuple = new bundledTuple(index);
    		for(int i = 1; i <= numColumns; i++)
    		{
    			tempTuple.addValue(rs.getObject(i), rsmd.getColumnName(i));
    		}
    		tupleList.add(tempTuple);
    		index++;
    	}    	
    	
    	//creates the model list for the tuple display list
    	return new javax.swing.AbstractListModel<String>() {
            bundledTuple[] tuples = tupleList.toArray(new bundledTuple[1]);
    		public int getSize() { return tuples.length; }
            public String getElementAt(int i) { return tuples[i].toString(); }
        };
    }
    
    private String generateTemplate() throws SQLException
    {
    	PreparedStatement tupleRetrieval = db.getConn().prepareStatement("SELECT * FROM " + tableComboBox.getSelectedItem());
    	ResultSet rs = tupleRetrieval.executeQuery();
    	ResultSetMetaData rsmd = rs.getMetaData();
    	int numColumns = rsmd.getColumnCount();
    	
    	String concatString = "";
    	
    	for(int i = 1; i <= numColumns; i++)
    	{
    		concatString += "[" + rsmd.getColumnName(i) + "]";
    		if(i != numColumns)
    			concatString += ", ";
//    		else
//    			concatString += "  -  Use comma as delimiter";
    	}
    	
    	return concatString;
    }
    
    private bundledTuple findTargetTuple(String tupleString)
    {
    	for(bundledTuple tuple : tupleList)
    	{
    		if (tuple.toString().equals(tupleString))
    			return tuple;
    	}
    	return null;
    }
}
