package project.audio.content;

public class VolumeConversion {
  private VolumeConversion() {}
  public static final float CONVERSION_FACTOR = 0.372232132137484753475431f;
  public static float convertVolume(float unConverted){ 
    // unConverted range from 0 to 100 convert it to a range from 0.05 to 0.25
    return unConverted / 100 * CONVERSION_FACTOR;
  }
}
