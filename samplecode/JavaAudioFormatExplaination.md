# Javaのオーディオフォーマット
参照
- [Javaのオーディオフォーマット](https://mocha-java.com/survey-audio-format/)
- [サンプルサイズに応じた最大値と最小値を取得する](https://mocha-java.com/sample-rate-max-min/)

## デジタル音声の品質を決める要素
### サンプルレート
音の波形の大きさを1秒間に何回記録するか（Hz）。
<br>画像に例えると解像度に対応。

### サンプルサイズ（＝bit depth）
記録（サンプル）するデータ1つ当たりの情報の細かさ（bit、実際に扱う単位はbyte）。
<br>音を発しているスピーカーの、振動盤の位置をどれだけ細かく指定できるか。
<br>画像に例えると色の多さに対応。

サンプルサイズがx bitの時、2^x通りの情報を保持できる。
中心を0とすると
- 最小値は -2^x/2 = -2^(x-1)
- 最大値は 2^x/2 - 1 = 2^(x-1) - 1

となる。

### チャンネル数
サンプルをいくつ同時に保持するか。
<br>通常はスピーカーの数と同じ。

## データ量
例：CD

- サンプルレート：44100 Hz
- サンプルサイズ：16 bit = 2 byte
- チャンネル数：2

よって、44100×2×2=176400 byte/s。
<br>音声編集時はwav形式（無圧縮）を使う。

## AudioFormat
Javaではオーディオの形式をAudioFormatクラスで定義する。
```
float sampleRate = 44100;
int sampleSize = 2;
int channels = 2;
bool signed = true; // 無音の状態を0とする。
bool bigEndian = false;

AudioFormat f = new AudioFormat(sampleRate, sampleSize*8, channels, signed, bigEndian);
```