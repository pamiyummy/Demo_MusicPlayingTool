// DJがラジカセにCDをせっせと入れるイメージ。
public class MusicModel {
    
    // 後々パス（CD）もプレイヤー（ラジカセ）も配列にしたい。
    private String filePath; // 音声ファイルのパス
    private WavPlayer player; // 音声ファイルを再生するクラス

    public MusicModel() {
        
        this.player = new WavPlayer();
        SetFilePath("./conan.wav"); // 後々分離する？読み込むファイル群は決まってるから、コンストラクタで処理してもよさそう
    }

    public void SetFilePath(String str) {

        this.filePath = str;
    }

    public void PlayMusic() { // 後々は配列のインデックスを指定する？

        this.player.PlayMusicFile(this.filePath);
    }
}
