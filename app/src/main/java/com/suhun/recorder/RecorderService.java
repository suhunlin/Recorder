package com.suhun.recorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class RecorderService extends Service {
    private String tag = RecorderService.class.getSimpleName();
    private MediaRecorder mediaRecorder;
    private File saveDir, rfile;
    private Timer timer;
    private int second;

    public RecorderService() {
        long time = System.currentTimeMillis();
        String childName = time+".3gp";
        saveDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        rfile = new File(saveDir, childName);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        second = 0;
        timer = new Timer();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(rfile);

        try{
            mediaRecorder.prepare();
            mediaRecorder.start();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    second++;
                    Intent intent = new Intent("startRecord");
                    intent.putExtra("second", second);
                    sendBroadcast(intent);
                }
            }, 100, 1000);
            Log.d(tag, "-----Start recorder..... -----");
        }catch (Exception e){
            Log.d(tag, "-----Exception occer "+e.toString()+"-----");
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaRecorder!=null){
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            Log.d(tag, "-----Stop recorder..... -----");
        }

        if(timer!=null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}