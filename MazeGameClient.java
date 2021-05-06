import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;

public class MazeGameClient extends Application
{
   //initializes different objects needed
   //root flowpane holding all objects
   FlowPane root = new FlowPane();
   //object i created in seperate class to draw the Maze Walls
   MazeGameWalls wall = new MazeGameWalls();
   
   //variables for the 2D array list in order to check if there is a wall there or not
   int[][] posBox = new int[21][21];
   int posX = 0;
   int posY = 0;
   int startPosX = 0;
   int startPosY = 0;
   
   //variables related to drawing the walls and player
   int drawX = 0;
   int drawY = 0;
   int startX = 0;
   int startY = 0;
   int endX = 0;
   int endY = 0;
   
   public void start (Stage stage)
   {  
      //set the size and background and add load object to main flowpane
      root.setPrefSize(525,525);
      //add the wall to the root flowpane
      root.getChildren().add(wall);
      try
      {
         //create scanner to read in the file
         Scanner scan = new Scanner(new File("MazeGame.txt"));
      
         //take in variables while there still are stuff to read
         while (scan.hasNext())
         {
            //draw color of wall based on whether the variable was read in as a 0 or 1
            int box = scan.nextInt();
            if (box == 0)
            {
               wall.drawWalls(drawX, drawY, Color.WHITE);
            }
            else
            {
               wall.drawWalls(drawX, drawY, Color.BLACK);
            }
            //sets up 2D array
            posBox[posX][posY] = box;
            
            //set the starting position
            if ((box == 0) && (drawY == 0))
            {
               startX = drawX;
               startY = drawY;
               startPosX = posX;
               startPosY = posY;
            }
            //set the endng position
            else if ((box == 0) && (drawY == 500))
            {
               endX = drawX;
               endY = drawY;
            }
            
            //increment the variables
            drawX += 25;
            posX += 1;
            if (drawX >= 525)
            {
               drawX = 0;
               drawY += 25;
               posX = 0;
               posY += 1;
            }
         }
      }
      //if file is not found
      catch(FileNotFoundException fnfe)
      {
         System.out.println("File not found!");
      }
      
      wall.drawStart(startX,startY);
      
      //create vbox in order to recieve keyboard inputs and add to main flowpane
      VBox key = new VBox();
      root.getChildren().add(key);
      
      //set the event of the key being pressed down
      key.setOnKeyPressed(new KeyListenerDown());
      
      //initialize the scene with the root flowpane
      Scene scene = new Scene(root, 525, 525);
      stage.setScene(scene);
      stage.setTitle("BorderPane");
      stage.show();
      
      //set the inputs already selected on the vbox
      key.requestFocus();
   }
   
   //start the program
   public static void main(String[] args)
   {
      launch(args);
   } 
   
   //object that checks to see what keyboard input is given when pressed down
   public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
      public void handle(KeyEvent event) 
      {
         //if the user presses Left and there is not a wall, move Left
         if (event.getCode() == KeyCode.LEFT) 
         {
            if (posBox[startPosX-1][startPosY] == 0)
            {
               wall.move(-25,0); 
               startPosX--;
            }
         }
         //if the user presses Right and there is not a wall, move Right
         else if (event.getCode() == KeyCode.RIGHT) 
         {
            if (posBox[startPosX+1][startPosY] == 0)
            {
               wall.move(25,0); 
               startPosX++;
            }
         }
         //if the user presses Up and there is not a wall, move Up
         if (event.getCode() == KeyCode.UP) 
         {
            if (startPosY-1 == -1)
            {
               
            }
            else
            {
               if (posBox[startPosX][startPosY-1] == 0)
               {
                  wall.move(0,-25); 
                  startPosY--;
               }
            }
         }
         //if the user presses Down and there is not a wall, move Down
         else if (event.getCode() == KeyCode.DOWN) 
         {
            if (startPosY+1 == 21)
            {
            
            }
            else
            {
               if (posBox[startPosX][startPosY+1] == 0)
               {
                  wall.move(0,25); 
                  startPosY++;
               } 
            }
         }
         
         //if you reach the end, type out win message
         if ((wall.returnX() == endX) && (wall.returnY() == endY))  
            System.out.println("You Win!");
      }
   }
}