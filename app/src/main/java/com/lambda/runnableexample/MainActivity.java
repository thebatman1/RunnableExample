package com.lambda.runnableexample;

import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Button button;
    private int [] colors = new int []{Color.RED , Color.BLUE , Color.GREEN , Color.YELLOW ,
                                        Color.CYAN , Color.MAGENTA};
    private Handler timerHandler = new Handler();
    private Random random = new Random(System.nanoTime());
    private static boolean on = false;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            layout.setBackgroundColor(colors[random.nextInt(colors.length)]);
            timerHandler.postDelayed(this , 1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout) findViewById(R.id.layout);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!on) {
                    timerHandler.postDelayed(runnable , 0);
                    button.setText("Turn Off the Magic!");
                    on = true;
                } else {
                    timerHandler.removeCallbacks(runnable);
                    layout.setBackgroundColor(Color.WHITE);
                    button.setText("Turn it back On!");
                    on = false;
                }
            }
        });
    }
}
