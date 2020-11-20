import java.io.*;
import java.util.Map;
import javazoom.jlgui.basicplayer.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class SimplePlayer extends JFrame implements ActionListener,BasicPlayerListener{
	
	//�I�u�W�F�N�g�̐���
	JFrame frame = new JFrame();
	JFileChooser chooser = new JFileChooser();
	BasicPlayer player = new BasicPlayer();
	BasicController control = (BasicController) player;
	
	//�R���X�g���N�^
	SimplePlayer(){
		//�J���{�^��
		JButton open = new JButton("�J��");
		//�R�}���h�Ƃ��ēo�^����
		open.setActionCommand("open");
		//���f������
		open.addActionListener(this);
		
		//�J���{�^���𒆐S�ɃZ�b�g����
		frame.add(open, BorderLayout.CENTER);
		//�E�C���h�E�T�C�Y�̐ݒ�
		frame.setSize(150, 150);
		//�E�C���h�E�̉�����ݒ�
		frame.setVisible(true);
		
		//�v���C���[�̐ݒ�𔽉f������
		player.addBasicPlayerListener(this);
		
		//�E�C���h�E�����Ƃ��̏���
		frame.addWindowListener (
			new WindowAdapter() {
				public void windowClosing( WindowEvent e ) {
					System.exit(0);	//���ɂ����I��
				}
			}
		);
		
	}
	
	//�Đ�
	public void play(String filename){
		try{
			//�t�@�C�����J���čĐ��A���ʁA�p���Ȃǂ�ݒ肷��
			control.open(new File(filename));
			//System.out.println(control.toString());
			control.play();
			//System.out.println(control.toString());
			control.setGain(0.50);
			control.setPan(0.0);
		}catch (BasicPlayerException e){
			e.printStackTrace();
		}
	}
	
	/*���ꎩ�̂͂͂����茾���ĉ��Ɏg�p���邩�킩��Ȃ�����ǁA
	 * control.play()�̒��O�A����ɃR���\�[�����s�������ʁA
	 * javazoom.jlgui.basicplayer.BasicPlayer@�`
	 * �̂悤�ȕ����񂪏o�͂��ꂽ�B
	 * javazoom�̃h�L�������g�ɂ́A
	 * A handle to the BasicPlayer, plugins may control the player through the controller (play, stop, ...)
	 * �Ə�����Ă���̂ŁA�ǂ����BasicPlayer��controller��ʂ��Đ��䂷�邽�߂̉���(�v���O�C��?)�炵���B
	 */
	public void setController(BasicController controller){
		display("Controll: " + controller.toString());
	}
	
	//���ʂ�p���A�v���C���[�̍Đ��Ȃǂ̌��݂̏󋵂��擾����
	public void stateUpdated(BasicPlayerEvent event){
		display("stateUpdated: " + event.toString());
	}
	
	//�T�E���h�̐i���󋵂��擾����
	public void progress(int read, long time, byte[] data, Map map){
		//display("progress :" + map.toString());
	}
	
	//�J���ꂽ�T�E���h�t�@�C���̏ڍׂ��擾����
	public void opened(Object object, Map map){
		display("opened :" + map.toString());
	}
	
	//�R���\�[���ɕ�������o�͂���
	public void display(String str){
		if(str != null)
			System.out.println(str);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getActionCommand() == "open"){
			/*
			 * �����ŁA�t�@�C���_�C�A���O���J�����B
			 * choice�ɂ�showOpenDialog�̖߂�l���i�[�����B
			 * �t�@�C�����I�΂ꂽ�ꍇ��APPROVE_OPTION�A�I�΂�Ȃ������ꍇ��CANCEL_OPTION�ɂȂ�B
			 */
			int choice = chooser.showOpenDialog(this);
			try {
				if (choice == JFileChooser.APPROVE_OPTION){
					//�I�����ꂽ�t�@�C���̃p�X���擾����
					File file = chooser.getSelectedFile();
					//�t�@�C�����̓ǂݍ��݂�����
					FileReader fr = new FileReader(file);
					//�^�C�g����I�΂ꂽ�t�@�C���̖��O�ɂ���(��΃p�X)
					frame.setTitle(file.getAbsolutePath());
					//�t�@�C���̉��
					fr.close();
					//�w�肳�ꂽ�T�E���h�t�@�C���̐�΃p�X���擾���A�Đ�����
					play(file.getAbsolutePath());
				}
			}catch(Exception e){
				System.out.println("�t�@�C���_�C�A���O�̓W�J�Ɏ��s���܂����B");
			}
		}
	}
}