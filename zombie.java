import java.lang.Math;
import javax.swing.*;

/**
 * 
 * @author Daniel Tucker
 *
 */

public class zombie extends JLabel implements Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int zAlive = 1;
	static int pAlive = 1;
	static int i = 0;
	/**
    *This class is the zombie enemy.
    *@author Daniel Tucker
    *@version 1.4 Jan 23 2019
    */
    protected int zombieX, zombieY; // zombie's current position
    private int hitboxX = 32, hitboxY = 32; //height and width of zombie in the world, defaults to 32x32
    private int xDiff, yDiff; //difference in player and zombie coordinates
    private final int xMax = 5, yMax = 5; //the most a zombie can travel in one tick of the movement function
    private ImageIcon icon; //icon for the zombie
    protected static int bodyCount; //number of times the gotShot() function has been called. Passed into the zombieSpawner spawn(int) function. More kills = faster zombie spawns
    private String name; // name string, likely unused in real game, for testing purposes

    // get and set functions
    /** 
    *@return X coordinate of the zombie. This is the very top left pixel of the zombie object
    */
    public int getX() { return zombieX; } //zombie's current position on the X axis
    /** 
    *@return Y coordinate of the zombie. This is the very top left pixel of the zombie object
    */
    public int getY() { return zombieY; } //zombie's current position on the Y axis
    /**
    *@param newX sets the zombie's X coordinate. This will be the very top left pixel of the zombie object
    */
    public void setX(int newX) { zombieX = newX; } //forces the zombie to move to the selected coordinate on the X axis
    /**
    *@param newY sets the zombie's Y coordinate. This will be the very top left pixel of the zombie object
    */
    public void setY(int newY) { zombieY = newY; } //forces the zombie to move to the selected coordinate on the Y axis
    /**
    *@return the X measurement of the zombie's hitbox. This is the width of the zombie in the world
    */
    public int getHitboxX() { return hitboxX; } //zombie's x hitbox length
    /**
    *@return the Y measurement of the zombie's hitbox. This is the height of the zombie in the world
    */
    public int getHitboxY() { return hitboxY; } //zombie's y hitbox length
    /**
    *@param newHitX changes the X measurement of the zombie's hitbox, making it wider or narrower
    */
    public void setHitboxX(int newHitX) { hitboxX = newHitX; } //changes the zombie's hitbox to specified value along the x axis
    /**
    *@param newHitY changes the Y measurement of the zombie's hitbox, making it taller or shorter
    */
    public void setHitboxY(int newHitY) { hitboxY = newHitY; } //changes the zombie's hitbox to specified value along the y axis
    /**
    *@return the name of the zombie. This will not be used in the final version of the program, it was used in the testing of the zombie class, particulary the gotShot(*) method
    *@deprecated
    */
    public String getName() { return name; } //zombie's name
    /**
    *@param newName the new name for this zombie. Like the getName(), this will not be used in teh final version
    *@deprecated
    */
    public void setName(String newName) { name = newName; } //changes the zombie's name

    /** 
    *@param playerX the X value of the object you want the zombie to chase.
    *@param playerY the Y value of the object you want the zombie to chase.
    */
    public void basicAI(int playerX, int playerY) {// makes the zombie walk at you menacingly
        xDiff = Math.abs(playerX - zombieX); //determines how far the zombie has to travel on the X axis
        yDiff = Math.abs(playerY - zombieY); //determines how far the zombie has to travel on the Y axis
        if (playerX != zombieX || playerY != zombieY) { // if the zombie is not lined up with player on one or both axes, moves at a speed of up to 5 pixels/half second
            if (playerX > zombieX) { //zombie needs to go west
            			if (xDiff >= 5)
            				zombieX += 5;
            			else
            				zombieX += xDiff;
            }
            else if (playerX < zombieX) { //zombie needs to go east
            			if (xDiff >= 5)
            				zombieX -= 5;
            			else
            				zombieX -= xDiff;
            }
            if (playerY > zombieY) { //zombie needs to go south
            			if (yDiff >= 5)
            				zombieY += 5;
            			else
            				zombieY += yDiff;
            }
            else if (playerY < zombieY) { //zombie needs to go north
            			if (yDiff >= 5)
            				zombieY -= 5;
            			else
            				zombieY -= yDiff;
            }
        }
        else if (playerX == zombieX && playerY == zombieY) {
            gotPlayer();
        }
        OPTGUI.layeredPane.repaint();
    }

    /** 
    *@param z the zombie object that is checking if the projectile object collided with the zombie object
    *@param objectX the X value of the projectile object
    *@param objectY the Y value of the projectile object
    */
    public void gotShot(zombie z, int objectX, int objectY) { //kills a zombie if the coordinates of the object are within the zombie's hitbox
        if (objectX >= z.zombieX && objectX <= (z.zombieX + 60) && objectY >= z.zombieY && objectY <= (z.zombieY + 60)) {
            zAlive = 0;
            OPTGUI.FirstFloor.remove(this);
            this.zombieX = -1;
            this.zombieY = -1;
            OPTGUI.layeredPane.repaint();
            OPTGUI.bc++;
            OPTGUI.KillScoreLabel.setText("NUMBER OF KILLS: " + OPTGUI.bc);
        }
    }

    /** 
    *The function that checks if the zombie caught the object it was chasing, it will switch the layered pane that the game runs in to the game over screen
    */
    public void gotPlayer() { //kills the player if a zombie catches them
        //set layered pane to layer 5, which is the game over screen
        //lp = 5;
    	if (OPTGUI.labelGameObject.getX() - 20 == zombieX && OPTGUI.labelGameObject.getY() - 20 == zombieY) {
    		pAlive = 0;
    	}
    }

    /** 
    *Constructor. Defaults the X and Y values to (50,50) and the hitbox to 32x32
    */
    public zombie() {
        zombieX = 50;
        zombieY = 50;
        this.setText("");
    }

    /** 
    *Constructor.
    *@param X the desired X value of the zombie object that is being created
    *@param Y the desired Y value of the zombie object that is being created
    */
    public zombie(int X, int Y) { 
        zombieX = X;
        zombieY = Y;
    }

    /**
    *Constructor. Defaults the X and Y values to (50,50)
    *@param ICON the desired icon for the zombie object being created, never actually worked
    *@deprecated
    */
    public zombie(ImageIcon ICON) {
        zombieX = 50;
        zombieY = 50;
        icon = ICON;
    }

    /**
    *Constructor. Creates a zombie with a location and name with very small hitbox
    *@param newName the name of the zombie, used for testing purposes
    *@param X the desired X value of the zombie object being created
    *@param Y the desired Y value of the zombie object being created
    *@deprecated
    */
    public zombie(String newName, int X, int Y) { //constructor for testing zombies. Incredibly small hitbox and name for specification in output
        name = newName;
        zombieX = X;
        zombieY = Y;
        hitboxX = 2;
        hitboxY = 2;
    }

    /**
    *Constructor. Creates zombie with a location and icon
    *@param ICON the ImageIcon of this zombie
    *@param X the X location of the zombie, measured to the very left side of the hitbox
    *@param Y the Y location of the zombie, measured to the very top side of the hitbox
    */
    public zombie(ImageIcon ICON, int X, int Y) {
        icon = ICON;
        this.setIcon(icon);
        zombieX = X;
        zombieY = Y;
        this.setBounds(X, Y, 60, 60);
        Thread zthread = new Thread(this);
        zthread.start();    
    }
    
    public void run() {
    	while (pAlive == 1) {
    		gotPlayer();
    		gotShot(this, Bullet.whatX(), Bullet.whatY());
    		if (zAlive == 1) {
    			basicAI(OPTGUI.labelGameObject.getX() - 20, OPTGUI.labelGameObject.getY() - 20);
    			try {
    				Thread.sleep(200);
    			} catch (Exception ex) {;}
    		}
    		else if (zAlive == 0) {
    			OPTGUI.layeredPane.remove(this);
    			zAlive = 1;
    		}
    	}
    	if (pAlive == 0) {
			OPTGUI.layeredPane.removeAll();
			OPTGUI.layeredPane.add(OPTGUI.GameOverPanel);
			OPTGUI.layeredPane.repaint();
			OPTGUI.layeredPane.revalidate();
    	}
    }
}