import javax.swing.JLabel;

/**
 * 
 * @author Daniel Tucker
 *
 */

public class wall extends JLabel {
    //rectangular hitbox that no object should not be able to move over/through
    /**
    *This class is a rectangular hitbox that will push objects out of it should they somehow get inside the wall
    *@author Daniel Tucker
    *@version 1.2 Jan 21 2019
    */
    private int x, y; // top left corner coordinates
    private int height, width; // dimensions of wall
    private int objectY;
    private int objectX;

    // get/set functions for coordinates
    /**
    *@return the X coordinate of the wall object. This is the very top left pixel
    */
    public int getX() { return x; }
    /**
    *@return the Y coordinate of the wall object. This is the very top left pixel
    */
    public int getY() { return y; }
    /**
    *@param newX the new X coordinate of the wall.
    */
    public void setX(int newX) { x = newX; }
    /**
    *@param newY the new Y coordinate of the wall.
    */
    public void setY(int newY) { y = newY; }

    // get/set functions for dimensions
    /**
    *@return the height of the wall. This is how far below the Y coordinate the wall extends.
    */
    public int getH() { return height; }
    /**
    *@return the width of the wall. This is how far to the right the wall extends past the X coordinate
    */
    public int getW() { return width; }
    /**
    *@param newH changes the height of the wall to the specified value
    */
    public void setH(int newH) { height = newH; }
    /**
    *@param newW changes the width of the wall to the specified value
    */
    public void setW(int newW) { width = newW; }

    /**
    *Constructor. Defaults the X and Y values to (5,5) and the height and width to 10x10
    */
    public wall() { //default constructor makes a 10x10 square extending right and down from the pixel located at 5,5
        x = 5;
        y = 5;
        height = 10;
        width = 10;
    }

    /**
    *Constructor. Defaults nothing
    *@param X the desired X coordinate for the wall object being created
    *@param Y the desired Y coordinate for the wall object being created
    *@param H the desired height for the wall object being created
    *@param W the desired width for the wall object being created
    */
    public wall(int X, int Y, int W, int H) {
        x = X;
        y = Y;
        height = H;
        width = W;
    }

    /**
    *This method pushes objects out of the wall should they find themselves inside a wall
    *@param objectX the X coordinate of the object that is being checked
    *@param objectY the Y coordinate of the object that is being checked
    */
    public boolean detectCollide(int objectX, int objectY, int objectW, int objectH) {
    	int north = (objectY + objectH) - y;
        int east = objectX - (x + width);
        int south = objectY - (y + height);
        int west = (objectX + objectW) - x;
        if(north >= 1 && east <= 1 && south <= 1 && west >= 1) { return true; }
        else { return false; }
    }
}