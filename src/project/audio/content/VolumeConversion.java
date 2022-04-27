package project.audio.content;

public class VolumeConversion {
  private VolumeConversion() {}
  public static final float CONVERSION_FACTOR = 0.35223658984875845784834890100328132137484753475431f;
  public static float convertVolume(float unConverted){ 
    return unConverted / 100 * CONVERSION_FACTOR;
  }

  public static float convertPan(float unConverted){ 
    return unConverted / 100 * 2 - 1;
  }

  public static float convertSpeedFactor(float unConverted) {
    return unConverted / 100 * 2 - 1;
  }
}
