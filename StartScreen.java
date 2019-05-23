import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
   public void paint (Graphics g) {
      Color myGreen = new Color(180, 255, 189);
      g.setColor(myGreen);
      g.fillRect(0, 0, 1000, 1000);
      
      Graphics2D g2d = (Graphics2D) g;
      g.setColor(Color.BLACK);
      Font font = new Font("Geneva CY", Font.PLAIN, 40);
      g.setFont(font);
      g2d.drawString("How To Play Frozen DDR!", 50, 50);
      
      font = new Font("Geneva CY", Font.PLAIN, 20);
      g.setFont(font);
      g2d.drawString("Press the start button and wait for the background to load", 15, 110);
      g2d.drawString("(there should be a picture of Olaf and a snowy background!)", 15, 135);
      
      g2d.drawString("As an arrow floats up the screen, press the corresponding", 15, 175);
      g2d.drawString("keyboard arrow key", 200, 195);
      
      g2d.drawString("Continue playing until you receive your score at the end of", 15, 235);
      g2d.drawString("the game!", 230, 260);
      
      ImageIcon olaf = new ImageIcon("images/frozen.png");
      g.drawImage(olaf.getImage(), 15, 270, null);
   }
}