package backend.audio;

public enum CodecDecoders {
  PCMS32("pcm_s32le"),
  PCMU24("pcm_u24le"),
  PCMS8("pcms8"),
  LIBAMR("libamr_wb"),
  LIBF("libfaab"),
  MP3("mp3"),
  MP2("mp2"),
  PCMU8("pcmu8");


  private final String ty;

  private CodecDecoders(String s) {
    this.ty = s;
  }

  public String get() {
    return ty;
  }
}
