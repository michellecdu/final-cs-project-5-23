import java.util.concurrent.TimeUnit; // for the timers
import javax.swing.*;
import java.awt.event.*; // for the listener
import java.awt.*;
/******************************************************************
* The Driver class contains the main method and runs the final game.
* @authors Michelle Du, Shreya Singh
*******************************************************************/
public class Driver
  {
  /*****************************
  * Main method (runs the game).
  ******************************/
  public static void main(String[] args) throws Exception
   {
      /**
      * current time, used to figure out how long the song should play for
      */
      long start = System.currentTimeMillis();
             
      // create JFrame
      JFrame frame = new JFrame("Frozen Dance Dance Revolution");
      frame.setSize(600, 600);
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // create Scoreboard
      Scoreboard scoreboard = new Scoreboard();
      frame.setContentPane(scoreboard);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // create start button and add to JFrame
      JButton b=new JButton("START");  
      frame.add(b, BorderLayout.NORTH);
      
      // create display and add to JFrame
      Display m = new Display();
      StartScreen startscreen = new StartScreen();
      frame.add(startscreen);
      //frame.add(m); 
      frame.setVisible(true);
      
      b.addActionListener(new ActionListener()
      {
        /***********************************
        * Plays song when button is pressed.
        ************************************/
        public void actionPerformed(ActionEvent e)
        {
            Sound.play("Frozen - Do You Want to Build a Snowman.wav"); // plays song
            setMusicStarted(true); // tells rest of program the music has started
            frame.remove(startscreen);
            frame.add(m);
        }
      });    
      b.setFocusable(false); // allows focus to go to the Display
      boolean first = true; // tells scoreboard that this was the first key (scoring is different)
      while (!musicStarted)
      {
         TimeUnit.MILLISECONDS.sleep(100); // wait for the music to start
      }
      
      while (System.currentTimeMillis() - start < 95000) { // only play a portion of the song
         long startTime = System.currentTimeMillis(); // keeps track of when the arrow started moving
         TimeUnit.MILLISECONDS.sleep(4000); // wait for arrow to get to the top of the screen
         scoreboard.update(m, scoreboard.getScoreLabel(), first); // update score
         first = false; // change first (for the remainder of the program)
         m.setY(1000); // make the arrow go back to the bottom of the screen
         m.changeArrow(); // change which arrow is displayed
         m.timer.restart(); // restart timer
      } 
      
      m.setFinished(true);
      EndScreen e = new EndScreen();
      e.setScore(scoreboard.getScore());
      frame.remove(m);
      frame.add(e);
      frame.setVisible(true);
      //System.exit(0); // exit JFrame after song is finished 
      //System.out.println("Congrats! Your final score was: " + scoreboard.getScore());  
   }
      /***********************************
        * musicStarted returns whether or
        * not the music has started.
        ************************************/
      private static boolean musicStarted = false; // used to determine if arrows should start moving
      public static void setMusicStarted(boolean started)
      {
         musicStarted = started;
      } 
}