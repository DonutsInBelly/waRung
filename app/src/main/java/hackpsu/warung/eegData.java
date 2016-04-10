package hackpsu.warung;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by MLH Admin on 4/10/2016.
 */
public class eegData implements Runnable {
    //constants
    private final int NPTS = 512;
    private final String[] channels = {"TP9", "FP1", "FP2", "TP10"};
    private final int channelCount = channels.length;
    private final int SPS = 220;
    private final double DT = 1/SPS;
    private final int Fn = SPS/2;
    private final int TWin = NPTS/SPS;
    private final double deltaF = 1/TWin;

    private Double[][] rawData = new Double[channelCount][NPTS];
    private Complex[][] complexData= new Complex[channelCount][NPTS];
    private int rawCounter;

    private Complex[][] fftData = new Complex[channelCount][NPTS];
    private Double[][] spectralPower = new Double[channelCount][NPTS];


    public eegData() {
        rawCounter = 0;
    }

    //pushes four new samples to the array
    public void pushData(Double[] newSample) {
        for(int i = 0; i < 4; i++) {
            rawData[i][rawCounter] = newSample[i];
            complexData[i][rawCounter] = new Complex(newSample[i],0);
        }
        rawCounter++;
        if(rawCounter == NPTS) {
            run();
            rawCounter = 0;
        }
    }

    public void run() {
        //copies complex data in case the old thread overwrites i
        Complex[][] nComplexData = complexData;
        //calculates fft and spectral power
        for(int i = 0; i<channelCount; i++) {
            fftData[i] = FFT.fft(nComplexData[i]);
            for(int j = 0; j < fftData[i].length-1; j++) {
                spectralPower[i][j]=(fftData[i][j].times(fftData[i][j].conjugate())).re();
            }
        }
        Double[] alphaPowers = new Double[channelCount];
        Double[] preAlphaPowers = new Double[channelCount];
        for(int i = 0; i <channelCount; i++) {
            alphaPowers[i] = mean(Arrays.copyOfRange(spectralPower[i],46,61));
            preAlphaPowers[i] = mean(Arrays.copyOfRange(spectralPower[i],31,46));
        }
        Double alphaPower = mean(alphaPowers);
        Double preAlphaPower = mean(preAlphaPowers);
        if(alphaPower > preAlphaPower) {
            Log.i("Messages","You need coffee");
        }
    }

    public static Double mean(Double[] m) {
        Double sum = 0.0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return sum / m.length;
    }


}
