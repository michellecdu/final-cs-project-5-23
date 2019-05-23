import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.Color;
/******************************************************************
* The Scoreboard class is a JPanel that displays and can update the
* score.
* @authors Michelle Du, Shreya Singh
*******************************************************************/
public class Scoreboard extends JPanel 
{
   //displays the score on the JLabel at the bottom
   public JLabel scoreLabel;
   double totalScore = 0;
   public Scoreboard() {
      setLayout(new BorderLayout());
      scoreLabel = new JLabel("Score: ");//add scorelater
      scoreLabel.setForeground(new Color(50, 100, 255)) ;
      scoreLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
      add(scoreLabel);
      add(scoreLabel, BorderLayout.SOUTH);
   }
   /**
   * getScoreLabel is an accessor that allows other classes to get
   * scoreLabel (the JLabel that displays the score).
   * @return scoreLabel 
   */
   
   public JLabel getScoreLabel() { //returns the scoreLabel
      return scoreLabel;
   }
   /**
   * getScore is an accessor that allows other classes to get
   * the total score. 
   * @return totalScore
   */
   public double getScore() { //returns the totalScore
      return totalScore;
   }
   /**
   * update is a method that changes the score depending on the
   * time at which the arrow key was clicked. update also changes
   * the scoreLabel.
   * @param m   the Display containing the game
   * @param label   the JLabel containing the score
   * @param first   determines if the arrow is the first (the score is calculated
   * differently if this is the case
   */
   public void update(Display m, JLabel label, boolean first) { //updates the score based on accuracy of user's click
      if (first) {
         totalScore = totalScore + 0.0; // start off with 0 points
         totalScore = totalScore + (m.getDifference()/100);
         System.out.println("total score, first " + totalScore);
         
      }
      else {
         totalScore = totalScore + (m.getDifference()/10); //loops after program has started with 5000
         System.out.println("total score " + totalScore);     
      }
      totalScore = Math.round(totalScore); //rounds the score
      label.setText("Score: " + totalScore);   //sets the score on display
   }

}