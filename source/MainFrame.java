import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import controller;

public class MainFrame extends JFrame {

    private MainFrame() {
        initWindowSettings();
    }

    private void initWindowSettings() {
        this.setBounds(500, 250, 720, 480); // (400, 500)の位置に720x480のウィンドウを表示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String argv[]) {
        new MainFrame();
    }
}