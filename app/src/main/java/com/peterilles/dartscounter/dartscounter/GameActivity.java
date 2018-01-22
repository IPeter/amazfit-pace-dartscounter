package com.peterilles.dartscounter.dartscounter;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    protected int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = getIntent().getIntExtra("game", 501);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

