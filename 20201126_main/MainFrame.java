// Swing用
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private MusicModel musicModel;
    private ControlPanel controlPanel;

    public static void main(String argv[]) {

        new MainFrame();
    }

    public MainFrame() {
        
        InitFrameSettings();
        
        this.musicModel = new MusicModel();
        this.controlPanel = new ControlPanel(this.musicModel);

        this.add(this.controlPanel);
    }

    // JFrameの初期設定をする。
    private void InitFrameSettings() {
        
        this.setTitle("MusicPlayingTool - Demo");

        // (400, 500)の位置に720x480のウィンドウを表示
        this.setBounds(500, 250, 720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}