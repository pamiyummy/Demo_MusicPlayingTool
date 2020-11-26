// コード参照：https://mocha-java.com/play-wav/

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem; // ファイルの入出力
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

// ラジカセのイメージ。
public class WavPlayer {
    
    public void PlayMusicFile(String filePath) {
        try {
            PlayFile(new File(filePath));
        }
        catch(LineUnavailableException e) {
            System.out.println("ラインは使用不可のため開けません。");
        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("開けない形式のファイルです。");
        }
        catch(IOException e) {
            System.out.println("入出力処理の例外です。");
        }
    }

    private void PlayFile(File audioFile) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        
        // ストリームを読み込む
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

        // ストリームから音声のフォーマットを取得し、フォーマットからLineオブジェクトを取得する
        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

        // ファイルのフォーマットに対応した出力ラインをAudioSystemに対して要求し、取得したラインを開く
        line.open(audioFormat, AudioSystem.NOT_SPECIFIED);
        line.start();

        int bufferSize = 128000;
        int bytesRead = 0;
        byte[] abData = new byte[bufferSize];

        while(bytesRead != -1) {

            // ストリームからビットを最大abData.length個読んで、配列abDataの0番目から順に格納する。
            // 実際に読んだビット数をbytesReadに格納する。
            bytesRead = audioInputStream.read(abData, 0, abData.length);

            if(0 <= bytesRead) {

                // 配列abDataの0番目からbytesRead個のビットを、ライン（を介してミキサー（？））へ順に書き込む。
                line.write(abData, 0, bytesRead);
            }
        }

        // ラインに並んだデータを実際に連続的に吐き出して出力する的な
        line.drain();
        line.close();
    }
}