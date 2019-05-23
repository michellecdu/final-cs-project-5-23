import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;
/******************************************************************
* The Display class is a JPanel that listens for key presses and
* displays and manipulates the content within the JPanel.
* @author Michelle Du
* @author Shreya Singh
*******************************************************************/  
public class Display extends JPanel implements ActionListener{
    /**
    * arrow positions
    */
    int x, y;
    /**
    * current time
    */ 
    long startTime = System.currentTimeMillis();
    /**
    * timer to start paintComponent
    */
    Timer timer;
    /**
    * will be used to figure out elapsed time
    */
    long difference;
    /**
    * shows no key has been pressed
    */
    boolean pressed = false;
    Random r = new Random(); 
    /**
    * random number used to determine the arrow that shows up
    */
    int rand = r.nextInt(4);
    int randMore = r.nextInt(16);
    Display() {
        x = 0;
        y = 1000; // start at bottom of screen
        timer = new Timer(2, this); // two millisecond delay before starting
    }
    /**********************************************************************
    * setY is a modifier that allows other classes to change the value of y
    * (the vertical component of the moving arrows).
    * @param a   desired value of y
    **********************************************************************/
    public void setY(int a) {
      y = a;
    }
    /***********************************************************************
    * getDifference is an accessor that allows other classes to retrieve the
    * value of difference (the amount of time elapsed from when the arrow
    * started moving to the current time).
    * @return difference
    ************************************************************************/
    public long getDifference() {
      long currentDiff = difference;
      difference = 0; // reset difference
      return currentDiff;
    }
    /*************************************************************************
    * changeArrow changes the value of rand, which is later used to change the
    * direction of the arrow.
    **************************************************************************/
    public void changeArrow() {
      startTime = System.currentTimeMillis(); // keeps track of when arrow starts moving
      pressed = false; // reset pressed
      rand = r.nextInt(4); // pick random arrow to display next
      randMore = r.nextInt(16);
    }
    /**************************************************************************
    * actionPerformed changes the y value of the arrow when the timer sends the
    * signal to do so.
    ***************************************************************************/ 
    public void actionPerformed(ActionEvent e) {
        // move arrow up screen
        y--; 
        repaint();
    }
    /*****************************************************************************
    * paintComponent displays the images on the panel and listens for key presses.
    ******************************************************************************/  
    public void paintComponent(Graphics g) {
    // clear screen
     g.setColor(Color.WHITE);
     g.fillRect(0, 0, 1000, 1000);
     
     //draw background images
     ImageIcon snow = new ImageIcon("images/fallingsnow.gif");
     g.drawImage(snow.getImage(), 0, 0, null);
     ImageIcon background = new ImageIcon("images/olaf.gif");
     g.drawImage(background.getImage(), 50, 200, null);
     ImageIcon arrow = new ImageIcon("images/arrowkeys.jpg");
     g.drawImage(arrow.getImage(), 175, 0, null);
     
     // listen for key presses
     //addKeyListener(new Key());
     //setFocusable(true); // puts focus on Display
     setKeyBindings();
     chooseArrow(g);
 }

/**
* The chooseArrow() method chooses a random arrow
*/ 
public void chooseArrow(Graphics g) {
     // array containing arrow images
  ImageIcon[] movingArrows = {
      new ImageIcon("images/leftarrow.png"),
      new ImageIcon("images/downarrow.png"),
      new ImageIcon("images/uparrow.png"),
      new ImageIcon("images/rightarrow.png") 
  }; 
  ImageIcon currArrow = movingArrows[rand];
  

  if ((randMore & 8) != 0 ) {
   g.drawImage(movingArrows[0].getImage(), 180, y, 50, 50, null); 
  }

  if ((randMore & 4) != 0) {
   g.drawImage(movingArrows[1].getImage(), 240, y, 50, 50, null); 
  }

   if ((randMore & 2) != 0) {
   g.drawImage(movingArrows[2].getImage(), 300, y, 50, 50, null); 
 }

   if ((randMore & 1) != 0) {
   g.drawImage(movingArrows[3].getImage(), 360, y, 50, 50, null); 
  }

} 
 
 /**
 * The setKeyBindings() method adds the correct key codes to a map (which will be used later).
 */
 private void setKeyBindings() {
      ActionMap actionMap = getActionMap();
      int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
      InputMap inputMap = getInputMap(condition );

      String vkLeft = "VK_LEFT";
      String vkRight = "VK_RIGHT";
      String vkUp = "VK_UP";
      String vkDown = "VK_DOWN";
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkDown);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkUp);
      
      actionMap.put(vkLeft, new KeyAction(vkLeft));
      actionMap.put(vkRight, new KeyAction(vkRight));
      actionMap.put(vkDown, new KeyAction(vkDown));
      actionMap.put(vkUp, new KeyAction(vkUp));

   }
/******************************************************************
* The KeyAction class tells the computer what to do when a certain key is
* pressed.
* @authors Michelle Du, Shreya Singh
*******************************************************************/
private class KeyAction extends AbstractAction {
      public KeyAction(String actionCommand) {
         putValue(ACTION_COMMAND_KEY, actionCommand);
      }

      @Override
      public void actionPerformed(ActionEvent actionEvt) {
         System.out.println(actionEvt.getActionCommand() + " pressed");
         if (rand == 0 && actionEvt.getActionCommand() == "VK_LEFT") {
            difference = difference(startTime, System.currentTimeMillis());
         }
         else if (rand == 1 && actionEvt.getActionCommand() == "VK_DOWN") {
            difference = difference(startTime, System.currentTimeMillis());
         }
         else if (rand == 2 && actionEvt.getActionCommand() == "VK_UP") {
            difference = difference(startTime, System.currentTimeMillis());
         }
         else if (rand == 3 && actionEvt.getActionCommand() == "VK_RIGHT") {
            difference = difference(startTime, System.currentTimeMillis());
         }
      }
   }

/************************************************************
 * difference returns the difference between the current time
 * and the time at which the current arrow started to move.
 * @param start   time when arrow started to move
 * @param end     current time
 * @return difference
 *************************************************************/ 
public long difference(long start, long end) {
   return end - start; // returns elapsed time
}
}

    
