import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
/******************************************************************
* The Sound class plays the background music for the game.
* @authors Michelle Du, Shreya Singh
*******************************************************************/   
public class Sound {
   /**
      * play runs the audio clip if said audio clip is found.
      */
   public static synchronized void play(final String fileName) 
   {
      // plays "do you want to build a snowman" from Frozen             
      new Thread(
         new Runnable() {  
            public void run() {
               try { //retrieves a clip
                  Clip clip = AudioSystem.getClip(); 
                  AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                  clip.open(inputStream); 
                  clip.start();  
               } catch (Exception e) { //catches any error and prints an error statement
                  System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
               }
            }
         }).start(); //starts playing the clip
   }
}