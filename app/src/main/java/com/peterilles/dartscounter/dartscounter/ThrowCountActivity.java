package com.peterilles.dartscounter.dartscounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ThrowCountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.throw_count_main);

        final TextView dartsCount = findViewById(R.id.darts_count_text);
        final SeekBar numberSeekbar = findViewById(R.id.seekbar_number);

        final TextView minus = findViewById(R.id.minus);
        final TextView plus = findViewById(R.id.plus);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentdartsCount = Integer.parseInt(dartsCount.getText().toString());

                if (currentdartsCount > 0){
                    dartsCount.setText(Integer.toString(--currentdartsCount));
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentdartsCount = Integer.parseInt(dartsCount.getText().toString());

                if (currentdartsCount < 180){
                    dartsCount.setText(Integer.toString(++currentdartsCount));
                }
            }
        });

        dartsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("throw", Integer.parseInt(((TextView)view).getText().toString()));
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

        numberSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                dartsCount.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

