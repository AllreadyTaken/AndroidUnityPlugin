package com.hypnolords.audiovisplugin;
import android.media.audiofx.Visualizer;

/**
 * Created by rh on 30.03.2018.
 */

public class AndroidAudioVis  {

    private Visualizer mVisualizer;
    private byte[] mWaveBytes, mFFTBytes;
    private boolean mInit = false;
    private String mError = "NO ERROR";

    public AndroidAudioVis()
    {
        mInit = setupVisualizer();

    }

    public boolean setupVisualizer()
    {
        try
        {
            mVisualizer = new Visualizer(0);
            mVisualizer.setEnabled(true);
            mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
            mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
                public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                                  int samplingRate) {
                    setWaveBytes(bytes);
                }

                public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                    setFFTBytes(bytes);
                }
            }, Visualizer.getMaxCaptureRate() / 2, true, true);
            return true;
        }
        catch (Exception e)
        {
            mError=e.toString();
            return false;
        }

    }


    public boolean isInitialized()
    {
        return mInit;
    }

    public String getErrorMessage()
    {
        return mError;
    }

    public void Destroy()
    {
        mVisualizer.setEnabled(false);
        mVisualizer.release();
    }

    public byte[] getWaveBytes()
    {
        return mWaveBytes;
    }

    private void setWaveBytes(byte[] bytes)
    {
        mWaveBytes = bytes;
    }

    public byte[] getFFTBytes()
    {
        return mFFTBytes;
    }

    private void setFFTBytes(byte[] bytes)
    {
        mFFTBytes = bytes;
    }


}