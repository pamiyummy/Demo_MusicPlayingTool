// Swing用
import java.awt.*;
import javax.swing.*;

// MouseListener用
import java.awt.event.*;

public class ControlPanel extends JPanel implements MouseListener {

    private MusicModel musicModel;

    public ControlPanel(MusicModel m) {
        
        this.musicModel = m; // MainFrameから渡された、MainFrameと共通のオブジェクト。
        this.addMouseListener(this);
    }

    //region MouseListenerインタフェースのメソッド群
    @Override
    public void mousePressed(MouseEvent e) {
        
        this.musicModel.PlayMusic();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //endregion
}