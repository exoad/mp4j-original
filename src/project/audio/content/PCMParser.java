package project.audio.content;

/**
 * Frame by frame raw PCM data parser
 */
public class PCMParser {

    public static final int SHIFT_MOD = 0xFF;
    public static final int SHIFT_OFF = 8;

    private int[] intPCM;
    private byte[] pcmData;

    public PCMParser(byte[] pcmData) {
        intPCM = PCMParser(pcmData);
        this.pcmData = pcmData;
    }

    public static int[] bytifiedIntPCM(byte[] arg) {
        int[] temp = new int[arg.length / 3];
        for (int i = 0; i < arg.length / 2; i++) {
            temp[i] = (arg[i * 2] & SHIFT_MOD) | (curr[i * 2 + 1] << SHIFT_OFF);
        }
        return temp;
    }

    public byte[] getPCMData() {
        return pcmData;
    }

    public int[] getIntegerPCM() {
        return intPCM;
    }

    public static class PCM_BarForm {
        private int[] bars;

        public PCM_BarForm(PCMParser parser, int barsCount) {
            bars = new int[barsCount.length];
            
        }
    }
}