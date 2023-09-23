package com.suhun.recorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.sql.Time;

public class RecorderService extends Service {
    private String tag = RecorderService.class.getSimpleName();
    private MediaRecorder mediaRecorder;
    private File saveDir, rfile;

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
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(rfile);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}