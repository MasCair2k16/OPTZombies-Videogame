import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * 
 * @author Daniel Tucker
 *
 */

public class zombieSpawner extends JLabel implements Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
    *This class will actually be spawning the zombies into the world. Implements Runnable so it can be run as a thread
    *@author Daniel Tucker
    *@version 1.0
    */
    private int X, Y;
    private static Random rand = new Random();
    String dir = System.getProperty("user.dir");
   	ImageIcon zombieBoy1 = new ImageIcon(dir + "\\src\\zombie_pics\\zombie1.png");
   	ImageIcon zombieKing = new ImageIcon(dir + "\\src\\zombie_pics\\zombie2.png");
   	ImageIcon zombieBoy2 = new ImageIcon(dir + "\\src\\zombie_pics\\zombie3.png");
    ImageIcon zombieGirl = new ImageIcon(dir + "\\src\\zombie_pics\\zombie4.png");
    
    /**
    *Default constructor for the class, assigns it X/Y values of 250
    */
    public zombieSpawner() {
        X = 250;
        Y = 250;
    }
    /**
    *This constructor sets the X/Y values of the zombieSpawner, so you can choose where they are on the map
    *@param xIn desired X value
    *@param yIn desired y value
    */
    public zombieSpawner(int xIn, int yIn) {
        X = xIn;
        Y = yIn;
        this.setBounds(X, Y, 10, 10);
    }
    


    /**
    *This is the main method of the class, it will spawn zombies based on a percent chance and sleep for a length of time determined by how many zombies you have killed
    *@param bodyCount the number of vanquished zombies
    */
    public void spawn(int bodyCount) { //5 planned spawn points
    	zombie z;
    	if (bodyCount <= 15)
    		try{
                if (rand.nextInt(4) < 2) /* 50% chance of spawn */ { z = new zombie(getIcon(), X, Y);
                	//System.out.println(JLayeredPane.getLayer(this));
                	if (JLayeredPane.getLayer(this) == 0) {
                    	//System.out.println("First Floor");
                    	z.setVisible(true);
                    	OPTGUI.removeFloor();
                		OPTGUI.FirstFloor.add(z);
                		OPTGUI.addFloor();
                	}
                	else if (JLayeredPane.getLayer(this) == 4) {
                		OPTGUI.SecondFloorPanel.add(z);
                	}
                	else {;}
                }
                Thread.sleep(3500); //3.5 second sleep. average 1 zombie/1.4 seconds
            } catch(Exception e) {;}
        else if (bodyCount > 15 && bodyCount <= 25)
            try{
                if (rand.nextInt(10) < 6) /* 60% chance of spawn */ { z = new zombie(getIcon(), X, Y); 
                	if (JLayeredPane.getLayer(this) == 0) {
                		//System.out.println("First Floor");
                		z.setVisible(true);
                		OPTGUI.removeFloor();
                		OPTGUI.FirstFloor.add(z);
                		OPTGUI.addFloor();
            		}
                }
                Thread.sleep(3000); //3 second sleep. average 1 zombie/second
            } catch(Exception e) {;}
                
        else if (bodyCount > 25 && bodyCount <= 35)
            try{
                if (rand.nextInt(10) < 7) /* 70% chance of spawn */ { z = new zombie(getIcon(), X, Y); 
                	if (JLayeredPane.getLayer(this) == 0) {
                		//System.out.println("First Floor");
                		z.setVisible(true);
                		OPTGUI.removeFloor();
                		OPTGUI.FirstFloor.add(z);
                		OPTGUI.addFloor();
                	}
                }
                Thread.sleep(2500); //2.5 second sleep. average 1.4 zombies/second
           } catch(Exception e) {;}
        else if (bodyCount > 35 && bodyCount <= 50)
            try {
                if (rand.nextInt(10) < 7) /* 70% chance of spawn */ { z = new zombie(getIcon(), X, Y); 
                	if (JLayeredPane.getLayer(this) == 0) {
                		//System.out.println("First Floor");
                		z.setVisible(true);
                		OPTGUI.removeFloor();
                		OPTGUI.FirstFloor.add(z);
                		OPTGUI.addFloor();
            		}
                }
                Thread.sleep(2000); //2 second sleep. average 1.75 zombies/second
            } catch(Exception e) {;}
        else if (bodyCount > 50 && bodyCount < 80)
            try { //at this point the average spawn rate will overtake your ammo regen rate
                if (rand.nextInt(10) < 8) /* 80% chance of spawn */ { z = new zombie(getIcon(), X, Y); 
	                if (JLayeredPane.getLayer(this) == 0) {
	                	//System.out.println("First Floor");
	                	z.setVisible(true);
	                	OPTGUI.removeFloor();
	            		OPTGUI.FirstFloor.add(z);
	            		OPTGUI.addFloor();
                	}
                }
                Thread.sleep(1500); //1.5 second sleep. average 2.6 zombies/second
            } catch(Exception e) {;}
        else if (bodyCount >= 80)
            try { //This is endgame. This is supposed to be unfair. You will die.
                z = new zombie(getIcon(), X, Y); /* 100% chance of spawn */
                z.setVisible(true);
                OPTGUI.removeFloor();
                OPTGUI.FirstFloor.add(z);
                OPTGUI.addFloor();
                Thread.sleep(750); //.75 second sleep. average 6.66 zombies/second
            } catch(Exception e) {;}
        run();
       }

    public boolean isBlocked(int viewX, int viewY) {
        if (viewX >= X && viewX <= X && viewY >= Y && viewY <= Y)
            return true;
        else
            return false;
    }
    
    /**
     * randomly select the Icon for the zombie jlabel
     */
    public ImageIcon getIcon() {
    	int x = rand.nextInt(4);
    	if (x == 0) {
    		return zombieBoy2;
    	}
    	else if (x == 1) {
    		return zombieKing;
    	}
    	else if (x == 2) {
    		return zombieGirl;
    	}
    	else {
    		return zombieBoy1;
    	}
    }

    /**
    *calls the spawn(*) method. Implements Runnable so the class can be run as a thread
    */
    public void run() {
            spawn(OPTGUI.bc);
    }
}