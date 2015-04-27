package ru.tetris.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by lex on 26.04.15.
 */
public class Player {

    public static final int MAX_SPEED  = 50;
    public static final int MAX_TOP    = 10;
    public static final int MAX_BOTTOM = 350;


    Image img_c = new ImageIcon(getClass().getClassLoader()
            .getResource("images/mila.jpg")).getImage();
    Image img_l = new ImageIcon(getClass().getClassLoader()
            .getResource("images/mila_l.jpg")).getImage();
    Image img_r = new ImageIcon(getClass().getClassLoader()
            .getResource("images/mila_r.jpg")).getImage();

    Image img   = img_c;


    int speed  = 0;
    int dv     = 0;
    int s      = 0;

    int x      = 100;
    int y      = 50;

    int dy     = 0;

    int layer1 = 0;
    int layer2 = 1200;


    public void move() {
        s     += speed;
        speed += dv;

        if (speed <= 0) speed = 0;
        if (speed >= MAX_SPEED) speed = MAX_SPEED;


        y     -= dy;

        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;

        if (layer2 - speed <= 0) {
            layer1 = 0;
            layer2 = 1200;
        } else {
            layer1 -= speed;
            layer2 -= speed;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT)
            dv = 1;

        if (key == KeyEvent.VK_LEFT) {
            dv = -1;
        }


        if (key == KeyEvent.VK_UP) {
            img = img_l;
            dy = 10;
        }


        if (key == KeyEvent.VK_DOWN) {
            img = img_r;
            dy = -10;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            img = img_c;
        }

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 60, 90);
    }
}
