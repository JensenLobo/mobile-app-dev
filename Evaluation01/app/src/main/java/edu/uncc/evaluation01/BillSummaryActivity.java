package edu.uncc.evaluation01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BillSummaryActivity extends AppCompatActivity {

    TextView textViewTipAmount, textViewTotalBill, textViewBillAmount, textViewTipPercentage;
    String tipPercent, billAmount;
    Double tipAmount, totalBill, tipPercents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_summary);

        textViewTipAmount = findViewById(R.id.textViewTipAmount);
        textViewTotalBill = findViewById(R.id.textViewTotalBill);
        textViewBillAmount = findViewById(R.id.textViewBillAmount);
        textViewTipPercentage = findViewById(R.id.textViewTipPercentage);

        if(getIntent() != null && getIntent().hasExtra(MainActivity.BILL_KEY)){
            Bill bill = (Bill)getIntent().getSerializableExtra(MainActivity.BILL_KEY);


            textViewTipPercentage.setText(bill.getTip());
            textViewBillAmount.setText(bill.getAmount());

            tipPercent = bill.getTip();

            Double billAmounts = Double.parseDouble(textViewBillAmount.getText().toString());
            if(tipPercent.equals("10%")){
                tipAmount = 0.10;
            } else if (tipPercent.equals("18%")){
                tipAmount = 0.18;
            } else if (tipPercent.equals("15%")){
                tipAmount = 0.15;
            } else{
                tipAmount = 0.20;
            }

            tipPercents = (tipAmount * billAmounts);
            tipPercent = tipPercents.toString();

            textViewTipAmount.setText(tipPercent);

            totalBill = tipPercents + billAmounts;
            billAmount = totalBill.toString();

            textViewTotalBill.setText(billAmount);
        }

        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}