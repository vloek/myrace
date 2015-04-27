package ru.tetris.app;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lex on 26.04.15.
 */
public class Enemy {

    int x = 0;
    int y = 0;
    int speed = 0;
    Image img = new ImageIcon(getClass().getClassLoader()
            .getResource("images/enemy.png")).getImage();
    Road road;

    public Enemy(int x, int y, int speed, Road road) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;

    }


    public Rectangle getRect() {
        return new Rectangle(x, y, 60, 90);
    }

    public void move() {
        x = x - road.p.speed + speed;
    }
}
