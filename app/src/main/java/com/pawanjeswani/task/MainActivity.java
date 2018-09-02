package com.pawanjeswani.task;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;
    int count = 1 ;
    boolean stop = true;
    Thread r;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCounter = findViewById(R.id.textView);


       r = new Thread() {
            @Override
            public void run() {
                while (stop){
                try {
                    Thread.sleep(100);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tvCounter.setText("#"+String.valueOf(count));
                        count++;
                        if((String.valueOf(count).charAt(String.valueOf(count).length()-1)) == '9')
                            count=(count*10)+1;

                        }
                    });
                    if(count > 999999)
                    {
                        count=999999;
                        stop = false;
                        translatetv();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
        r.start();

}
    void translatetv(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tvshrink);
        animation.setFillAfter(true);
        tvCounter.startAnimation(animation);

    }
}
