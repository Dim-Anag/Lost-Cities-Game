package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private static Clip currentClip;




     /**
     * Plays the sound from the specified file path.
     * 
     * @param filePath the path to the sound file
     * @pre filePath is not null and points to a valid audio file
     * @post the sound is played in a loop
     */
    public static void playSound(String filePath) {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY); 
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }






    /**
     * Stops the currently playing sound.
     * 
     * @pre none
     * @post the currently playing sound is stopped
     */
    public static void stopSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }
}