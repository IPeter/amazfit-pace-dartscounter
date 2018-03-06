package com.peterilles.dartscounter.dartscounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends Activity {

    protected int score = 0;
    protected int thrs = 0;

    protected TextView scoreView = null;
    protected TextView checkoutView = null;
    protected TextView avgView = null;

    protected ArrayList<Integer> throwList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        score = getIntent().getIntExtra("game", 501);

        scoreView = findViewById(R.id.score);
        scoreView.setText(Integer.toString(score));
        checkoutView = findViewById(R.id.checkout);
        avgView = findViewById(R.id.avg);

        scoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(), ThrowCountActivity.class), thrs);
            }
        });

        scoreView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                checkoutView.setText(getCheckout());
                avgView.setText(avgFromThrows());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            changeCounter(data.getIntExtra("throw", 0));
        }

        thrs++;
    }

    protected void changeCounter(int thr){
        int newscore = score - thr;

        if (newscore >= 0){
            throwList.add(thr);
            score = newscore;
        } else {
            throwList.add(0);
        }

        scoreView.setText(Integer.toString(score));
    }

    protected String avgFromThrows(){
        int sum = 0;
        for (int i = 0; i < throwList.size(); i++){
            sum += throwList.get(i);
        }

        return "avg: " + Integer.toString((int)Math.ceil(sum/throwList.size()));
    }

    protected String getCheckout(){
        try {
            int ressourceId = getResources().getIdentifier(
                    "checkout_"+score,
                    "string",
                    getBaseContext().getPackageName());

            return getResources().getString(ressourceId);
        } catch (Exception e){}

        return "-";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

