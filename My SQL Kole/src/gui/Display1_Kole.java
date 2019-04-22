package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jacob Kole
 */
public class Display1_Kole extends JFrame implements ActionListener {
	// serial added to get rid of a warning
	private static final long serialVersionUID = 1L;
	
	JButton textButton, imageButton;
	JLabel textLabel, imageLabel;
	
	private BufferedImage image;
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Display1_Kole gui = new Display1_Kole();
	}
	
	/**
	 * 
	 */
	public Display1_Kole() {
		//setLayout(new BorderLayout());
		
		JFrame frame = new JFrame("display1.png");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image myDisplay = null;
		try {
			BufferedImage img = ImageIO.read(new File("display1.png"));
			myDisplay = img.getScaledInstance(1050, 750, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//let's put our icon directly within label declarations
		JLabel displayLabel = new JLabel();
		displayLabel.setIcon(new ImageIcon(myDisplay));
		frame.getContentPane().add(displayLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
//		JLabel displayLabel = new JLabel(new ImageIcon(myDisplay));
//		add(displayLabel);
		
		// the two commented out lines below were replaced by the
		// lines following them in the tutorial
//		JPanel centerPanel = new JPanel(new GridLayout(2,3));
//		add("Center", centerPanel);
		// Layout Managers and Components
//		textLabel = new JLabel("North");
//		add("North", textLabel);
//		
//		imageButton = new JButton(createImage());
//		add("West", imageButton);
//		
//		imageLabel = new JLabel(createImage());
//		add("South", imageLabel);
//		
//		textButton = new JButton("A Button");
//		textButton.addActionListener(this); // lets it know to send SampleGUI action event updates
//		add("East", textButton);
//		
//		JPanel centerPanel = new JPanel(new GridLayout(2,3));
//		JLabel[][] labelArray = new JLabel[2][3];
//		for (int r = 0; r < 2; r++)
//		{
//			for (int c = 0; c < 3; c++)
//			{
//				labelArray[r][c] = new JLabel(" (" + r + ":" + c + ") ");
//				centerPanel.add(labelArray[r][c]);
//			}
//		}
//		add("Center", centerPanel);
//		
//		pack();
//		setVisible(true);
	}
	
//	public void ImagePanel() {
//		try {
//			image = ImageIO.read(new File("display2.png"));
//		} catch (IOException ex) {
//			// handle exception...
//		}
//	}
//	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
//	}
	
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
