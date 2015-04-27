package ru.tetris.app;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by lex on 26.04.15.
 */
public class Road extends JPanel implements ActionListener, Runnable {

    int PATH_TO_WIN = 50000;

    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon(getClass().getClassLoader()
            .getResource("images/road.gif")).getImage();

    Player p = new Player();

    Thread enemiesFactory = new Thread(this);
    Thread audioThread    = new Thread( new AudioThread() );

    java.util.List<Enemy> enemies = new ArrayList<Enemy>();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        Random rand = new Random();

        while(true) {
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200,
                        rand.nextInt(400),
                        rand.nextInt(50),
                        this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }

    public void paint(Graphics g) {
        drawRoad(g);
        drawPlayer(g);
        drawInfo(g);
        drawEnemies(g);
    }

    void drawEnemies(Graphics g) {

        enemies.removeIf(i -> {
            if (i.x >= 2400 || i.x <= -2400) return true;
            i.move();
            g.drawImage(i.img, i.x, i.y, null);
            return false;
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }



    void testCollisionWithEnemies() {

        for (Enemy e : enemies) {
            if (p.getRect().intersects(e.getRect())) {
                JOptionPane.showMessageDialog(null, "Жирчик!!!!!!");
                System.exit(1);
            }
        }

    }

    void drawInfo(Graphics g) {
        double speed = (200 / Player.MAX_SPEED) * p.speed;
        Font f = new Font("Arial", Font.ITALIC, 20);
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString("Пепяка: " + speed + " " + "км/ч", 100, 30);

        g.drawString("Проехали уже: " + p.s + " " + "км из " + PATH_TO_WIN, 500, 30);

    }

    void drawRoad(Graphics g) {
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
    }

    void drawPlayer(Graphics g) {
        g.drawImage(p.img, p.x, p.y, null);
    }

    void testWin() {
        if (p.s >= 50000) {
            JOptionPane.showMessageDialog(null, "Ты нипабедим:'((((000");
            System.exit(1);
        }
    }
}
