import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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

public class Controller implements MouseListener {

}

public class View extends JPanel {

}

public class AudioModel {

    private String audioPath;

    public AudioModel(String inputPath) {
        this.audioPath = inputPath;
    }

    public GetAudioPath() {
        return this.audioPath;
    }
}