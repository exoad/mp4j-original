package main.advisors.test;

import java.io.File;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.advisors.Host;

import org.tritonus.share.sampled.convert.TFormatConversionProvider;
import org.tritonus.share.sampled.convert.TSimpleFormatConversionProvider;

public class HostTest {
  public static void main(String[] args) throws java.io.IOException, UnsupportedAudioFileException {
    Host host = new Host("");
    Host.mp3toWav(new File("D:/zip/projects/MusicPlayer/audio/「友　情」.mp3"));


  }
}
