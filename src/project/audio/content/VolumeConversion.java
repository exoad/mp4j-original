package project.audio.content;

public class VolumeConversion {
  private VolumeConversion() {}

  public static float convertVolume(float unConverted){ 
    // unConverted range from 0 to 100 convert it to a range from 0.05 to 0.25
    return unConverted / 100 * 0.12232132137484753475431f;
  }
}
