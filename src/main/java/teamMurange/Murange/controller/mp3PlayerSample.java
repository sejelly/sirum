package teamMurange.Murange.controller;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class mp3PlayerSample {

    //class mp3PlayerSample

    private String filename;
    private BufferedInputStream buffer;
    private AdvancedPlayer player;

    //constructor
    public mp3PlayerSample(String filename)
    {
        this.filename = filename;
    }

    //start method
    public void play()
    {
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.filename);
            buffer = new BufferedInputStream(fis);
            try {
                this.player=new AdvancedPlayer(buffer);
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //stop method
    public void stop()
    {
        if(player != null){
            player.close();
        }
    }
}
