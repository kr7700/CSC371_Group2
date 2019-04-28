package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is a sample gui to show how we can implement our own
 * displays for the database. Tutorial found on d2l.
 * @author Jacob Kole
 */
public class Messanger extends JFrame implements ActionListener {
	// serial added to get rid of a warning
	private static final long serialVersionUID = 1L;
	
	JButton textButton, imageButton;
	JLabel textLabel, imageLabel;
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		SampleGUI gui = new SampleGUI();
	}
	
	/**
	 * Illustrates how we can set up the classes used in
	 * JavaSwing.
	 */
	public Messanger() {
		setLayout(new BorderLayout());
		
		// the two commented out lines below were replaced by the
		// lines following them in the tutorial
//		JPanel centerPanel = new JPanel(new GridLayout(2,3));
//		add("Center", centerPanel);
		// Layout Managers and Components
		textLabel = new JLabel("North");
		add("North", textLabel);
		
		imageButton = new JButton(createImage());
		add("West", imageButton);
		
		imageLabel = new JLabel(createImage());
		add("South", imageLabel);
		
		textButton = new JButton("A Button");
		textButton.addActionListener(this); // lets it know to send SampleGUI action event updates
		add("East", textButton);
		
		JPanel centerPanel = new JPanel(new GridLayout(2,3));
		JLabel[][] labelArray = new JLabel[2][3];
		for (int r = 0; r < 2; r++)
		{
			for (int c = 0; c < 3; c++)
			{
				labelArray[r][c] = new JLabel(" (" + r + ":" + c + ") ");
				centerPanel.add(labelArray[r][c]);
			}
		}
		add("Center", centerPanel);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Provides an example of how we can construct an ImageIcon.
	 * The code first creates a BufferedImage, fills in the whole
	 * image with a very light gray, then draws a green circle roughly
	 * in the center of the image.
	 * @return
	 */
	public ImageIcon createImage() {
		BufferedImage exampleImage = new
				BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
		Graphics drawer = exampleImage.getGraphics();
		
		drawer.setColor(new Color(200,200,200));
		drawer.fillRect(0, 0, 50, 50);
		
		drawer.setColor(new Color(0,250,0));
		drawer.fillOval(20, 20, 10, 10);
		
		return new ImageIcon(exampleImage);
	}
	
	/**
	 * As textButton isn't the only thing that could generate an action event, 
	 * we check to see who generated the event before doing something with it.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == textButton)
		{
			textButton.setText("Pushed");
		}
	}
}

