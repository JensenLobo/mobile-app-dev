package edu.uncc.evaluation01;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewSelectedTip, textViewTipAmount, textViewTotalBill, textViewBillAmount, textViewTipPercentage;
    EditText editTextBillAmount;
    String tip;
    final public static String BILL_KEY = "bill";
    private ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            textViewSelectedTip = findViewById(R.id.textViewSelectedTip);
            if(o.getResultCode() == RESULT_OK ){
                Intent data = o.getData();
                if(data != null && data.hasExtra(SelectTipActivity.TIP_KEY)){
                    tip = data.getStringExtra(SelectTipActivity.TIP_KEY);
                    textViewSelectedTip.setText(tip);
                } else{
                    tip = null;
                    textViewSelectedTip.setText("N/A");
                }
            } else{
                tip = null;
                textViewSelectedTip.setText("N/A");
            }
        }
    });

    private ActivityResultLauncher<Intent> startBillResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            textViewBillAmount = findViewById(R.id.textViewBillAmount);
            textViewTipAmount = findViewById(R.id.textViewTipAmount);
            /*if(o.getResultCode() == RESULT_OK){
                Intent data = o.getData();
                if(data != null && data.hasExtra())
            }*/
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBillAmount = findViewById(R.id.editTextBillAmount);
        textViewSelectedTip = findViewById(R.id.textViewSelectedTip);

        findViewById(R.id.buttonSelectTip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectTipActivity.class);
                startForResult.launch(intent);

            }
        });

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = editTextBillAmount.getText().toString();
                String tip = textViewSelectedTip.getText().toString();

                if(amount.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter an Amount!", Toast.LENGTH_SHORT).show();
                } else if (tip.toString().equals("N/A")) {
                    Toast.makeText(MainActivity.this, "Select a Tip", Toast.LENGTH_SHORT).show();
                } else{
                    Bill bill = new Bill(amount, tip);
                    Intent intent1 = new Intent(MainActivity.this, BillSummaryActivity.class);
                    intent1.putExtra(BILL_KEY ,bill);
                    startActivity(intent1);

                }
            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBillAmount.setText(null);
                textViewSelectedTip.setText("N/A");
            }
        });
    }
}