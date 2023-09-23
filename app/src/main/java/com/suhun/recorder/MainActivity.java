package com.suhun.recorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkUserPermissionAboutRecorder()){
            initRecorder();
        }else{//三個permission如果有一個user沒有同意就發出requestPermissions請求使用者同意
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED
                        &&grantResults[2]==PackageManager.PERMISSION_GRANTED)   {
                initRecorder();
            }else{
                finish();
            }
        }
    }

    private boolean checkUserPermissionAboutRecorder(){
        boolean result = true;
        //三個permission如果有一個user沒有同意就發出請求
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
            ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
            ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
            result = false;
        }
        return result;
    }



    private void initRecorder(){

    }

    public void startRecorderFun(View view){

    }

    public void stopRecorderFun(View view){

    }
}