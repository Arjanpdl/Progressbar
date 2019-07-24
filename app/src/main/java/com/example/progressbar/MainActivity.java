package com.example.progressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    static  int progress;
    ProgressBar progressBar;
    int progressStatus =0;
    int MaxValue = 200;
    int MidValue = MaxValue/2;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress= 0;
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(MaxValue);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus<MidValue) {
                    progressStatus = doSomeWork();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    progressBar.setVisibility(View.GONE);
                    }
                });
            }

            private int doSomeWork(){
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();

    }
}
