package edu.uncc.evaluation01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SelectTipActivity extends AppCompatActivity {

    final public static String TIP_KEY = "tip";
    TextView textViewProgress, textViewSelectedTip;
    RadioGroup radioGroup;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tip);
        textViewProgress = findViewById(R.id.textViewProgress);
        textViewSelectedTip = findViewById(R.id.textViewSelectedTip);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = findViewById(R.id.radioGroup);
                int selectedID = radioGroup.getCheckedRadioButtonId();
                String custom = getString(R.string.ten_percent);

                if(selectedID == R.id.radioButton10){
                    custom = getString(R.string.ten_percent);
                } else if (selectedID == R.id.radioButton15) {
                    custom = getString(R.string.fifteen_percent);
                } else if (selectedID == R.id.radioButton18) {
                    custom = getString(R.string.eighteen_percent);
                } else if (selectedID == R.id.radioButtonCustom) {
                    custom = textViewProgress.getText().toString();
                }


                Intent intent = new Intent();
                intent.putExtra(TIP_KEY, custom);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}