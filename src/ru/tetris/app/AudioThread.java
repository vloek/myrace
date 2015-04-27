package ru.tetris.app;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioThread implements Runnable {

    @Override
    public void run() {
        try {
            javazoom.jl.player.Player p = new javazoom.jl.player.Player(
                    new FileInputStream(getClass().getClassLoader()
                            .getResource("music/moonrig.mp3").getPath()));
            p.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
