package ru.tetris.app;

import javax.swing.*;

import static javax.swing.JFrame.*;

public class Main {
    String VERSION = "0.1";

    public static void main(String[] args) {

        JFrame f = new JFrame("Mila vs Lex");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(1100, 480);
        f.add(new Road());


        f.setVisible(true);

    }
}
