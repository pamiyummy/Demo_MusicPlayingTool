// DJがラジカセにCDをせっせと入れるイメージ。
public class MusicModel {
    
    private String[] filePath; // 音声ファイルのパスの配列
    private int musicNum; // 音声ファイルの数
    private final int MAX_FILE = 10; // 音声ファイルの最大数

    private WavPlayer[] musicPlayer; // 音声ファイルを再生するプレイヤーの配列
    private boolean[] isMusicPlayerAvailable;
    private final int MAX_PLAYER = 10;

    public MusicModel() {

        this.filepath = new String[this.MAX_FILE];
        this.musicNum = 0;

        this.musicPlayer = new WavPlayer[this.MAX_PLAYER];
        this.isMusicPlayerAvailable = new boolean[10];
        for(int i = 0; i < MAX_PLAYER; i++) {
            this.musicPlayer[i] = new WavPlayer(); // ラジカセの電源はすべてONにしておく。
            this.isMusicPlayerAvailable[i] = true;
        }
    }

    // 引数に渡された音声ファイルのパスを配列に保持する。
    // あらかじめCDを用意しておくイメージ。
    public void SetMusicFile(String str) {

        this.filePath[this.musicNum++] = str;
    }

    // 
    public boolean PlayMusic(int i) {

        // 不正な番号指定
        if(i < 0 || MAX_PLAYER <= i) {
            return false;
        }

        if()
        this.musicPlayer[i].
    }
}
