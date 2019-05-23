import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
   double score = 0.0;
   public void paint (Graphics g) {
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, 1000, 1000);
      ImageIcon background = new ImageIcon("images/background.png");
      g.drawImage(background.getImage(), 0, 0, null);
      Font font = new Font("Geneva CY", Font.BOLD, 70);
      g.setFont(font);
      Graphics2D g2d = (Graphics2D) g;
      g.setColor(Color.WHITE);
      g2d.drawString("YOUR SCORE: ", 50, 100);
      g2d.drawString(Double.toString(score), 75, 175);
   }
   public void setScore (double myScore) {
      score = myScore;
   }
}