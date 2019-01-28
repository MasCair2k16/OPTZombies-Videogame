import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 * 
 * @author Casey Sweet
 * 
 */
public class Bullet extends JLabel implements Runnable{

	private static final long serialVersionUID = 1L;
	private double angle;
	int dx, dy, smileyX, smileyY; ///dx, dy are the x,y of the mouse, SmileyX/Y is the x,y of the player
	int xx, yy;
	static int X;
	static int Y; 
	public static int whatX() { return X; }
	public static int whatY() { return Y; }
	
	public Bullet(String text, int x, int y) {
		super(text);
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
		
		setBounds(x,y, width, height);
	}
	
	public Bullet(ImageIcon i) {
		super(i);
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;
	}
	
	/**
	 * rotates the player image in the jlabel
	 * @param g is the Graphics image to be rotated
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.rotate(-1*Math.toRadians(angle)+200, getWidth()/2, getHeight()/2);
		double i = angle;
		super.paintComponent(g);
	}
	
	@Override
	/**
	 * this run is what move the bullet after it has been "fired"
	 */
	public void run() {
		//// these values are the unit vectors that figure the amount that the x and y must change per tick to be able to move correctly
		xx = (int)(((dx)/(Math.sqrt((dx*dx) + (dy*dy)))) * 20);
		yy = (int)((dy)/(Math.sqrt((dx*dx) + (dy*dy))) * 20);

	while (this.getX() >= -10 && this.getX() < OPTGUI.width && this.getY() >= -10 && this.getY() < OPTGUI.height) {

			int x = this.getX();
			int y = this.getY();
			X = x;
			Y = y;
			///these are if click is at an angle
			if((smileyX < dx) && (smileyY < dy))
				this.setLocation(x + (3*xx), y + (3*yy));
			else if((smileyX > dx) && (smileyY < dy))
				this.setLocation(x - (3*xx), y + (3*yy));
			else if((smileyX < dx) && (smileyY > dy))
				this.setLocation(x + (3*xx), y - (3*yy));
			else if((smileyX > dx) && (smileyY > dy))
				this.setLocation(x - (3*xx), y - (3*yy));
			
			///these are if the click is directly above, below, right, or left of the player
			if((Math.abs(smileyY - dy) < 15) && smileyX < dx)
				this.setLocation(x + (3*xx) , y);
			if((Math.abs(smileyX - dx) < 15) && smileyY > dy)
				this.setLocation(x, y - (3*yy));
			if((Math.abs(smileyY - dy) < 15) && smileyX > dx)
				this.setLocation(x - (3*xx) , y);
			if((Math.abs(smileyX - dx) < 15) && smileyY < dy)
				this.setLocation(x, y + (3*yy));

			this.repaint();
			try {
				Thread.sleep(50);
			}catch(InterruptedException ex) {
				System.out.print("oh no");
			}
		}
	}

	/**
	 * Allows player to rotate
	 * @param angle angle to rotate
	 */
	public void setRotation(double angle) {
		this.angle = angle;
	}
	/**
	 * setting a variable equal to the mouses x postion
	 * @param x the mouses x position
	 */
	public void setX(int x) {
		dx = x;
	}
	/**
	 * setting a variable equal to the mouses y position
	 * @param y the mouses y position
	 */
	public void setY(int y) {
		dy = y;
	}
	
	/**
	 * Setting the position of the player
	 * @param x the x value of the position of the player
	 * @param y the y value of the position of the player
	 */
	public void setPos(int x, int y) {
		smileyX = x;
		smileyY = y;
	}
}