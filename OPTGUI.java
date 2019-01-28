import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Casey Sweet (GUI)
 * @author Mason Caird (player)
 */

public class OPTGUI  implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	
	///UI components
	private JFrame frame;
	public static JPanel GameOverPanel;
	static JLayeredPane layeredPane = new JLayeredPane();
	JPanel introPanel = new JPanel();
	JButton Play = new JButton("PLAY");
	JLabel Title = new JLabel("OPT Zombies");
	JPanel InstructPanel = new JPanel();
	JLabel infoLabel = new JLabel("INFORMATION");
	JLabel ControlLabel = new JLabel("Controls:");
	JLabel pressW = new JLabel("- Press W to go up");
	JLabel pressA = new JLabel("- Press A to go left");
	JLabel pressS = new JLabel("- Press S to go down");
	JLabel pressD = new JLabel("- Press D to go left");
	JLabel Clickleft = new JLabel("- Left-click mouse to fire Nerf Dart");
	JLabel moveMouse = new JLabel("- Master Smiley will rotate when mouse is moved");
	static JPanel FirstFloor = new JPanel();
	static JLabel onePic = new JLabel("");
	JButton mmBttn1 = new JButton("Main Menu");
	JLabel MainStairDetect = new JLabel("");
	JLabel SecretStairDetect = new JLabel("");
	static JPanel SecondFloorPanel = new JPanel();
	JLabel part1 = new JLabel("");
	JLabel part3 = new JLabel("");
	JLabel part2 = new JLabel("");
	JButton mmBttn2 = new JButton("Main Menu");
	JLabel MainStairDetect2 = new JLabel("");
	JLabel SecretStairDetect2 = new JLabel("");
	JLabel GameOverLabel = new JLabel("GAME OVER");
	static JLabel KillScoreLabel = new JLabel("NUMBER OF KILLS: 0");
	JButton ReplayBttn = new JButton("Play Again");
	JButton LetsGoBttn = new JButton("Let's Go!!");
	static ArrayList<wall> Walls1 = new ArrayList<wall>();
	ArrayList<wall> Walls2 = new ArrayList<wall>();
	public static int bc;
	zombieSpawner GatewaySpwnr = new zombieSpawner(163, 241);
	zombieSpawner ShaftSpwnr = new zombieSpawner(988, 43);
	zombieSpawner RoomSpwnr = new zombieSpawner(1295, 241);
	KeyEvent ev;
	
	///game components
	private static final long serialVersionUID = 1L;
	ArrayList<Bullet> DartInMag = new ArrayList<Bullet>();
	static player labelGameObject;
	KeyEvent e;
	int mouseX = 0; // Position of 
	int mouseY = 0;
	static double angleDirection;
	static int darts = 24;
	boolean dartAlive = false;
	static int width = 0;
	static int height = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OPTGUI window = new OPTGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OPTGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1800, 923);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		width = frame.getWidth();
		height = frame.getHeight();
		
		layeredPane.setBounds(0, 0, 1784, 873);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		introPanel.setBackground(Color.BLACK);
		layeredPane.add(introPanel, "name_38527473191000");
		introPanel.setLayout(null);
		
		Title.setFont(new Font("Courier New", Font.BOLD, 18));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(Color.GREEN);
		Title.setBackground(Color.GREEN);
		Title.setBounds(578, 101, 628, 269);
		introPanel.add(Title);
		
		Play.setForeground(Color.GREEN);
		Play.setBackground(Color.BLACK);
		Play.setFont(new Font("Courier New", Font.BOLD, 18));
		Play.setBounds(844, 381, 95, 54);
		Play.setOpaque(false);
		Play.setBorderPainted(false);
		Play.addActionListener(this);
		introPanel.add(Play);
		
		InstructPanel.setBackground(Color.BLACK);
		layeredPane.add(InstructPanel, "name_188986388168800");
		InstructPanel.setLayout(null);
		
		infoLabel.setFont(new Font("Courier New", Font.BOLD, 24));
		infoLabel.setForeground(Color.GREEN);
		infoLabel.setBackground(Color.BLACK);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(653, 52, 478, 108);
		InstructPanel.add(infoLabel);
		
		ControlLabel.setFont(new Font("Courier New", Font.PLAIN, 21));
		ControlLabel.setForeground(Color.GREEN);
		ControlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ControlLabel.setBounds(59, 226, 163, 47);
		InstructPanel.add(ControlLabel);
		
		pressW.setFont(new Font("Courier New", Font.PLAIN, 18));
		pressW.setForeground(Color.GREEN);
		pressW.setBounds(125, 284, 244, 47);
		InstructPanel.add(pressW);
		
		pressA.setForeground(Color.GREEN);
		pressA.setFont(new Font("Courier New", Font.PLAIN, 18));
		pressA.setBounds(125, 342, 244, 47);
		InstructPanel.add(pressA);
		
		pressS.setForeground(Color.GREEN);
		pressS.setFont(new Font("Courier New", Font.PLAIN, 18));
		pressS.setBounds(125, 400, 244, 47);
		InstructPanel.add(pressS);
		
		pressD.setFont(new Font("Courier New", Font.PLAIN, 18));
		pressD.setForeground(Color.GREEN);
		pressD.setBounds(125, 458, 244, 47);
		InstructPanel.add(pressD);
		
		Clickleft.setForeground(Color.GREEN);
		Clickleft.setFont(new Font("Courier New", Font.PLAIN, 18));
		Clickleft.setBounds(125, 516, 435, 47);
		InstructPanel.add(Clickleft);
		
		moveMouse.setFont(new Font("Courier New", Font.PLAIN, 18));
		moveMouse.setForeground(Color.GREEN);
		moveMouse.setBounds(125, 574, 546, 47);
		InstructPanel.add(moveMouse);
		LetsGoBttn.setForeground(Color.GREEN);
		LetsGoBttn.setBackground(Color.BLACK);
		LetsGoBttn.setFont(new Font("Courier New", Font.BOLD, 18));
		LetsGoBttn.setBorderPainted(false);
		

		LetsGoBttn.setBounds(762, 684, 244, 47);
		LetsGoBttn.addActionListener(this);
		InstructPanel.add(LetsGoBttn);
		
		layeredPane.add(FirstFloor, "name_185921878038500");
		FirstFloor.setLayout(null);
		
		try {
			URL player = new URL("https://raw.githubusercontent.com/MasCair2k16/OPTZombies-Final/master/src/smiley.png");
			Image playImage = ImageIO.read(player);
			labelGameObject = new player(new ImageIcon(playImage));
		} catch (IOException e) {};
		
		labelGameObject.setBackground(Color.ORANGE);
		layeredPane.addKeyListener(this);
		layeredPane.setFocusable(true);
		layeredPane.requestFocus();
		FirstFloor.addMouseMotionListener(this);
		FirstFloor.addMouseListener(this);
		labelGameObject.setHorizontalAlignment(SwingConstants.CENTER);
		labelGameObject.setBounds(1007, 268, 32, 37);
		FirstFloor.add(labelGameObject);
		
		for (int i = 0; i < 24; i++) {
			try {
				URL bullet = new URL("https://raw.githubusercontent.com/MasCair2k16/OPTZombies-Final/master/src/dart.png");
				Image bulImage = ImageIO.read(bullet);
				Bullet l = new Bullet(new ImageIcon(bulImage));
				FirstFloor.add(l);
				l.setVisible(false);
				l.setBounds(labelGameObject.getX(), labelGameObject.getY(), 30, 30);
				DartInMag.add(l);
			} catch (IOException e) {};
		}
		
		onePic.setHorizontalAlignment(SwingConstants.CENTER);
		onePic.setBounds(-18, 0, 1774, 888);
		try {
			URL p11 = new URL("https://piskel-imgstore-b.appspot.com/img/2d17b680-1c48-11e9-b40c-11d4774c3d72.gif");
			Image p11Image = ImageIO.read(p11);
			onePic.setIcon(new ImageIcon(p11Image));
		} catch (IOException e) {
			System.out.printf("oops");
		}
		FirstFloor.add(GatewaySpwnr);
		FirstFloor.add(ShaftSpwnr);
		FirstFloor.add(RoomSpwnr);
		
		FirstFloor.add(onePic);
		
		mmBttn1.setBounds(1684, 0, 100, 23);
		FirstFloor.add(mmBttn1);
		
		MainStairDetect.setBounds(880, 509, 58, 37);
		FirstFloor.add(MainStairDetect);
		
		SecretStairDetect.setBounds(1228, 119, 45, 37);
		FirstFloor.add(SecretStairDetect);
		
		///arraylist of wall components for the first floor
		Walls1.add(new wall(148, 316, 806, 28));
		Walls1.add(new wall(148, 199, 806, 28));
		Walls1.add(new wall(129, 207, 32, 132));
		Walls1.add(new wall(938, 315, 12, 240));
		Walls1.add(new wall(1098, 315, 12, 251));
		Walls1.add(new wall(868, 329, 12, 307));
		Walls1.add(new wall(878, 634, 293, 23));
		Walls1.add(new wall(1101, 316, 646, 23));
		Walls1.add(new wall(957, 22, 789, 23));
		Walls1.add(new wall(937, 32, 32, 198));
		Walls1.add(new wall(937, 222, 46, 8));
		Walls1.add(new wall(1207, 210, 152, 23));
		Walls1.add(new wall(1219, 43, 11, 190));
		Walls1.add(new wall(1060, 43, 159, 37));
		Walls1.add(new wall(1273, 94, 86, 83));
		Walls1.add(new wall(944, 533, 45, 13));
		Walls1.add(new wall(1064, 535, 45, 13));
		Walls1.add(new wall(1102, 603, 9, 30));
		Walls1.add(new wall(1165, 509, 9, 125));
		Walls1.add(new wall(1407, 93, 9, 145));
		Walls1.add(new wall(1407, 222, 131, 11));
		Walls1.add(new wall(1494, 114, 26, 64));
		Walls1.add(new wall(1745, 43, 11, 272));
		Walls1.add(new wall(1105, 499, 64, 14));
		for (int i = 0; i < Walls1.size(); i++) {
			FirstFloor.add(Walls1.get(i));
		}
		
		layeredPane.add(SecondFloorPanel, "name_181875283793200");
		SecondFloorPanel.setLayout(null);
		
		part1.setHorizontalAlignment(SwingConstants.CENTER);
		part1.setBounds(10, 11, 1155, 477);	
		try {
			URL p1 = new URL("https://piskel-imgstore-b.appspot.com/img/9fdc63cf-1c34-11e9-b0b3-cb72b4b8f8a9.gif");
			Image p1Image = ImageIO.read(p1);
			part1.setIcon(new ImageIcon(p1Image));
		} catch (IOException e) {
			System.out.printf("oops");
		}
		SecondFloorPanel.add(part1);
				
		part3.setHorizontalAlignment(SwingConstants.CENTER);
		part3.setBounds(954, 89, 857, 686);
		try {
			URL p3 = new URL("https://piskel-imgstore-b.appspot.com/img/c5942ab5-1c4a-11e9-9d06-11d4774c3d72.gif");
			Image p3Image = ImageIO.read(p3);
			part3.setIcon(new ImageIcon(p3Image));
		} catch (IOException e) {
			System.out.printf("oops");
		}	
		SecondFloorPanel.add(part3);
				
		part2.setHorizontalAlignment(SwingConstants.CENTER);
		part2.setBounds(59, 400, 954, 383);

		try {
			URL p2 = new URL("https://piskel-imgstore-b.appspot.com/img/18b65651-1c4a-11e9-bee5-11d4774c3d72.gif");
			Image p2Image = ImageIO.read(p2);
			part2.setIcon(new ImageIcon(p2Image));
		} catch (IOException e) {
			System.out.printf("oops");
		}
		SecondFloorPanel.add(part2);
		
		for (int i = 0; i < Walls2.size(); i++) {
			SecondFloorPanel.add(Walls2.get(i));
		}
		
		mmBttn2.setBounds(1684, 0, 100, 23);
		SecondFloorPanel.add(mmBttn2);
		
		MainStairDetect2.setBounds(864, 400, 69, 43);
		SecondFloorPanel.add(MainStairDetect2);
		
		SecretStairDetect2.setBounds(1260, 146, 53, 35);
		SecondFloorPanel.add(SecretStairDetect2);
		
		JLabel lblFirespwnr = new JLabel("FireSpwnr");
		lblFirespwnr.setBounds(128, 306, 64, 64);
		SecondFloorPanel.add(lblFirespwnr);
		
		JLabel lblThirdspwnr = new JLabel("ThirdSpwnr");
		lblThirdspwnr.setBounds(874, 655, 64, 64);
		SecondFloorPanel.add(lblThirdspwnr);
		
		GameOverPanel = new JPanel();
		GameOverPanel.setBackground(Color.BLACK);
		layeredPane.add(GameOverPanel, "name_181556459877400");
		GameOverPanel.setLayout(null);
		
		GameOverLabel.setFont(new Font("Courier New", Font.BOLD, 32));
		GameOverLabel.setForeground(Color.GREEN);
		GameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GameOverLabel.setBounds(806, 100, 171, 37);
		GameOverPanel.add(GameOverLabel);
		
		KillScoreLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		KillScoreLabel.setForeground(Color.GREEN);
		KillScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		KillScoreLabel.setBounds(776, 293, 232, 19);
		GameOverPanel.add(KillScoreLabel);
		
		ReplayBttn.setForeground(Color.GREEN);
		ReplayBttn.setBackground(Color.BLACK);
		ReplayBttn.setFont(new Font("Courier New", Font.PLAIN, 16));
		ReplayBttn.setBounds(810, 515, 163, 27);
		ReplayBttn.setOpaque(false);
		ReplayBttn.setBorderPainted(false);
		ReplayBttn.addActionListener(this);
		GameOverPanel.add(ReplayBttn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Play) {
			//Play button pressed, move to InstructionPanel
			layeredPane.removeAll();
			layeredPane.add(InstructPanel);
			layeredPane.revalidate();
			layeredPane.repaint();
		}
		if (e.getSource() == LetsGoBttn) {
			//Lets Go Button pressed, start the first floor activity
			layeredPane.removeAll();
			layeredPane.add(FirstFloor);
			layeredPane.revalidate();
			layeredPane.repaint();
			
			Thread t1 = new Thread(GatewaySpwnr);
			Thread t2 = new Thread(RoomSpwnr);
			Thread t3 = new Thread(ShaftSpwnr);
			t1.start();
			t2.start();
			t3.start();
		}
		if (e.getSource() == ReplayBttn) {
			/////If the replay button is pressed, start all over again
			FirstFloor.removeAll();
			FirstFloor.add(GatewaySpwnr);
			FirstFloor.add(ShaftSpwnr);
			FirstFloor.add(RoomSpwnr);
			
			FirstFloor.add(onePic);
			
			mmBttn1.setBounds(1684, 0, 100, 23);
			FirstFloor.add(mmBttn1);
			
			MainStairDetect.setBounds(880, 509, 58, 37);
			FirstFloor.add(MainStairDetect);
			
			SecretStairDetect.setBounds(1228, 119, 45, 37);
			FirstFloor.add(SecretStairDetect);
			
			Walls1.add(new wall(148, 316, 806, 28));
			Walls1.add(new wall(148, 199, 806, 28));
			Walls1.add(new wall(129, 207, 32, 132));
			Walls1.add(new wall(938, 315, 12, 240));
			Walls1.add(new wall(1098, 315, 12, 251));
			Walls1.add(new wall(868, 329, 12, 307));
			Walls1.add(new wall(878, 634, 293, 23));
			Walls1.add(new wall(1101, 316, 646, 23));
			Walls1.add(new wall(957, 22, 789, 23));
			Walls1.add(new wall(937, 32, 32, 198));
			Walls1.add(new wall(937, 222, 46, 8));
			Walls1.add(new wall(1207, 210, 152, 23));
			Walls1.add(new wall(1219, 43, 11, 190));
			Walls1.add(new wall(1060, 43, 159, 37));
			Walls1.add(new wall(1273, 94, 86, 83));
			Walls1.add(new wall(944, 533, 45, 13));
			Walls1.add(new wall(1064, 535, 45, 13));
			Walls1.add(new wall(1102, 603, 9, 30));
			Walls1.add(new wall(1165, 509, 9, 125));
			Walls1.add(new wall(1407, 93, 9, 145));
			Walls1.add(new wall(1407, 222, 131, 11));
			Walls1.add(new wall(1494, 114, 26, 64));
			Walls1.add(new wall(1745, 43, 11, 272));
			Walls1.add(new wall(1105, 499, 64, 14));
			for (int i = 0; i < Walls1.size(); i++) {
				FirstFloor.add(Walls1.get(i));
			}
			FirstFloor.add(labelGameObject);
			layeredPane.removeAll();
			layeredPane.add(FirstFloor);
			layeredPane.revalidate();
			layeredPane.repaint();
		}
	}
	
	/**
	 * If a key a,s,d,w is pushed, the gameLabelObject is moved to that direction
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		boolean collide = false;
		// Captures the key pressed down
		int key = e.getKeyCode();
		//System.out.println("You pushed a thing");
		// If a known key is pressed down, then the location of the object increases or decreases depending on the
		// key option.
		if (key == KeyEvent.VK_D) {
			for (int i = 0; i < Walls1.size(); i++) {
				collide = Walls1.get(i).detectCollide(labelGameObject.getX()+4, labelGameObject.getY(), 25, 23);
				if (collide == true)
					break;
			}
			if (collide == false) {
				labelGameObject.setLocation(labelGameObject.getLocation().x+4, labelGameObject.getLocation().y);
			}
			else {;}
		}
		else if (key == KeyEvent.VK_A) {
			for (int i = 0; i < Walls1.size(); i++) {
				collide = Walls1.get(i).detectCollide(labelGameObject.getX()-4, labelGameObject.getY(), 25, 23);
				if (collide == true)
					break;
			}
			if (collide == false) {
				labelGameObject.setLocation(labelGameObject.getLocation().x-4, labelGameObject.getLocation().y);
			}
		}
		else if (key == KeyEvent.VK_W) {
			for (int i = 0; i < Walls1.size(); i++) {
				collide = Walls1.get(i).detectCollide(labelGameObject.getX(), labelGameObject.getY()-4, 25, 23);
				if (collide == true)
					break;
			}
			if (collide == false) {
				labelGameObject.setLocation(labelGameObject.getLocation().x,  labelGameObject.getLocation().y-4);
			}
		}
		else if (key == KeyEvent.VK_S) {
			for (int i = 0; i < Walls1.size(); i++) {
				collide = Walls1.get(i).detectCollide(labelGameObject.getX(), labelGameObject.getY()+4, 25, 23);
				if (collide == true)
					break;
			}
			if (collide == false) {
				labelGameObject.setLocation(labelGameObject.getLocation().x, labelGameObject.getLocation().y+4);
			}
		}
		
		// if user taps a key that wasn't programmed, nothing will happen
		else {System.out.println("That is key is not made");}
		
		for (int i = 0; i < Walls1.size(); i++) {
			Walls1.get(i).detectCollide(labelGameObject.getX(), labelGameObject.getY(), 25, 23);
		}
		
		// used vector and found the degree and converted it to angle and is now the resulting direction
		angleDirection =  (Math.toDegrees(Math.atan2(mouseX, mouseY))+120);
		labelGameObject.setRotation(angleDirection);
		FirstFloor.repaint();
	}
	
		/**
		 * Shooting the dart across the screen
		 * @param e the click event
		 */
	@Override
	public void mouseClicked(MouseEvent e) {
 
			mouseX = e.getX() - (labelGameObject.getX()+9);
			mouseY = e.getY() - (labelGameObject.getY()+9);
			if (!(DartInMag.isEmpty())) {
				DartInMag.get(darts-1).setBounds(labelGameObject.getX(), labelGameObject.getY(), 30, 30);
				DartInMag.get(darts-1).setVisible(true);
				DartInMag.get(darts-1).setRotation(angleDirection);
				DartInMag.get(darts-1).setX(e.getX());
				DartInMag.get(darts-1).setY(e.getY());
				DartInMag.get(darts-1).setPos(labelGameObject.getX(), labelGameObject.getY());
				Thread t = new Thread(DartInMag.get(darts -1));
				t.start();
				FirstFloor.repaint();
				DartInMag.remove(darts-1);
				darts--;
			}
			else if ((DartInMag.isEmpty())) {
				System.out.println("You have no bullets");
			}		
	}


	/**
	 * checks to see if the mouse is being moved and if it, rotate the gameLabelObject
	 * I will have a JLabel and a picture. The picture will be the one rotating. 
	 * I need to figure out how will keep the object moving too.
	 * @param e
	 */

	@Override
	public void mouseMoved(MouseEvent e) {
		// This is to make the origin relative to labelGameObject and find vector between two points
		mouseX = e.getX() - (labelGameObject.getX()+9);
		mouseY = e.getY() - (labelGameObject.getY()+9);

		// used vector and found the degree and converted it to angle and is now the resulting direction
		angleDirection =  (Math.toDegrees(Math.atan2(mouseX, mouseY))+120);
		labelGameObject.setRotation(angleDirection);
		
		// rotates the labelGameObject without having to push asdw.
		frame.repaint();
	}
	
	///static class for player so that player can rotate
	static class player extends JLabel {
		
		private static final long serialVersionUID = 1L;
		private double angleFaced = 0; // determines how far to rotate.
		
		public player(String text, int x, int y) {
			super(text);
			int width = getPreferredSize().width;
			int height = getPreferredSize().height;
			
			setBounds(x,y, width, height);
		}
		
		public player(ImageIcon i) {
			super(i);
			int width = getPreferredSize().width;
			int height = getPreferredSize().height;
		}
		
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;
			g2D.rotate(-1*Math.toRadians(angleFaced)+200, getWidth()/2, getHeight()/2);
			super.paintComponent(g);
		}
		public void setRotation(double angle) {this.angleFaced = angle;}
	}

	/**
	 * removes the Floor so that zombies can be added
	 */
	public static void removeFloor() {
		FirstFloor.remove(onePic);
	}
	
	/**
	 * replaces the floor after the zombies have been added to the screen
	 */
	public static void addFloor() {
		FirstFloor.add(onePic);
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
