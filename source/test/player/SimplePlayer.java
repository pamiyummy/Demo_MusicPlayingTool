import java.io.*;
import java.util.Map;
import javazoom.jlgui.basicplayer.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class SimplePlayer extends JFrame implements ActionListener,BasicPlayerListener{
	
	//オブジェクトの生成
	JFrame frame = new JFrame();
	JFileChooser chooser = new JFileChooser();
	BasicPlayer player = new BasicPlayer();
	BasicController control = (BasicController) player;
	
	//コンストラクタ
	SimplePlayer(){
		//開くボタン
		JButton open = new JButton("開く");
		//コマンドとして登録する
		open.setActionCommand("open");
		//反映させる
		open.addActionListener(this);
		
		//開くボタンを中心にセットする
		frame.add(open, BorderLayout.CENTER);
		//ウインドウサイズの設定
		frame.setSize(150, 150);
		//ウインドウの可視性を設定
		frame.setVisible(true);
		
		//プレイヤーの設定を反映させる
		player.addBasicPlayerListener(this);
		
		//ウインドウを閉じるときの処理
		frame.addWindowListener (
			new WindowAdapter() {
				public void windowClosing( WindowEvent e ) {
					System.exit(0);	//俗にいう終了
				}
			}
		);
		
	}
	
	//再生
	public void play(String filename){
		try{
			//ファイルを開いて再生、音量、パンなどを設定する
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
	
	/*これ自体ははっきり言って何に使用するかわからないけれど、
	 * control.play()の直前、直後にコンソールを行った結果、
	 * javazoom.jlgui.basicplayer.BasicPlayer@～
	 * のような文字列が出力された。
	 * javazoomのドキュメントには、
	 * A handle to the BasicPlayer, plugins may control the player through the controller (play, stop, ...)
	 * と書かれているので、どうやらBasicPlayerをcontrollerを通じて制御するための何か(プラグイン?)らしい。
	 */
	public void setController(BasicController controller){
		display("Controll: " + controller.toString());
	}
	
	//音量やパン、プレイヤーの再生などの現在の状況を取得する
	public void stateUpdated(BasicPlayerEvent event){
		display("stateUpdated: " + event.toString());
	}
	
	//サウンドの進捗状況を取得する
	public void progress(int read, long time, byte[] data, Map map){
		//display("progress :" + map.toString());
	}
	
	//開かれたサウンドファイルの詳細を取得する
	public void opened(Object object, Map map){
		display("opened :" + map.toString());
	}
	
	//コンソールに文字列を出力する
	public void display(String str){
		if(str != null)
			System.out.println(str);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getActionCommand() == "open"){
			/*
			 * ここで、ファイルダイアログが開かれる。
			 * choiceにはshowOpenDialogの戻り値が格納される。
			 * ファイルが選ばれた場合はAPPROVE_OPTION、選ばれなかった場合はCANCEL_OPTIONになる。
			 */
			int choice = chooser.showOpenDialog(this);
			try {
				if (choice == JFileChooser.APPROVE_OPTION){
					//選択されたファイルのパスを取得する
					File file = chooser.getSelectedFile();
					//ファイル名の読み込みをする
					FileReader fr = new FileReader(file);
					//タイトルを選ばれたファイルの名前にする(絶対パス)
					frame.setTitle(file.getAbsolutePath());
					//ファイルの解放
					fr.close();
					//指定されたサウンドファイルの絶対パスを取得し、再生する
					play(file.getAbsolutePath());
				}
			}catch(Exception e){
				System.out.println("ファイルダイアログの展開に失敗しました。");
			}
		}
	}
}