import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.canvas.*;
import java.io.*;

public class MazeGameWalls extends Canvas
{
   //initialize variables needed
   //position of the cyan square for player
   int positionX = 0;
   int positionY = 0;
   //graphics context allowing for drawing
   GraphicsContext gc = getGraphicsContext2D();
   
   //constructor creating object
   public MazeGameWalls()
   {
      //sets the width and heigth to 525,525
      super(525,525);
   }
   
   //method that draws the initial maze walls
   public void drawWalls(int x, int y, Color c)
   {
      gc.setFill(c);
      gc.fillRect(x,y,25,25);
   }
   
   //method that draws the inital position of the player and sets the initial position variables as well
   public void drawStart(int x, int y)
   {
      gc.setFill(Color.CYAN);
      gc.fillRect(x,y,25,25);
      positionX = x;
      positionY = y;
   }
   
   //moves the player to its new coordinates
   public void move(int x, int y)
   {
      gc.setFill(Color.CYAN);
      gc.clearRect(positionX,positionY,25,25);
      positionX += x;
      positionY += y;
      gc.fillRect(positionX,positionY,25,25);
      
   }
   
   //accessors
   public int returnX()
   {
      return positionX;
   }
   public int returnY()
   {
      return positionY;
   }
}