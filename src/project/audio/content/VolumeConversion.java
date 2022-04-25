package project.audio.content;

public class VolumeConversion {
  private VolumeConversion() {}
  public static final float CONVERSION_FACTOR = 0.23223658984875845784834890100328132137484753475431f;
  public static float convertVolume(float unConverted){ 
    return unConverted / 100 * CONVERSION_FACTOR;
  }
}
