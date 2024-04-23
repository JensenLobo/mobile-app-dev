package com.example.dealornodeal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView choose;
    Button resetButton;
    Button Deal;
    Button NoDeal;
    private final int [] imageViewCaseId = {R.id.suit1, R.id.suit2, R.id.suit3, R.id.suit4, R.id.suit5, R.id.suit6, R.id.suit7, R.id.suit8, R.id.suit9, R.id.suit10};
    private final int[] imageViewNumId = {R.id.deal1, R.id.deal10, R.id.deal50, R.id.deal100, R.id.deal300, R.id.deal1k, R.id.deal10k, R.id.deal50k, R.id.deal100k, R.id.deal500k};
    private final int[] drawableCloseCase = {R.drawable.suitcase_position_1, R.drawable.suitcase_position_2, R.drawable.suitcase_position_3, R.drawable.suitcase_position_4, R.drawable.suitcase_position_5, R.drawable.suitcase_position_6, R.drawable.suitcase_position_7, R.drawable.suitcase_position_8, R.drawable.suitcase_position_9, R.drawable.suitcase_position_10};
    private final int [] drawableCaseId = {R.drawable.suitcase_open_1, R.drawable.suitcase_open_10, R.drawable.suitcase_open_50, R.drawable.suitcase_open_100, R.drawable.suitcase_open_300, R.drawable.suitcase_open_1000, R.drawable.suitcase_open_10000, R.drawable.suitcase_open_50000, R.drawable.suitcase_open_100000, R.drawable.suitcase_open_500000};
    private final int[] drawableNumId = {R.drawable.reward_open_1, R.drawable.reward_open_10, R.drawable.reward_open_50, R.drawable.reward_open_100, R.drawable.reward_open_300, R.drawable.reward_open_1000, R.drawable.reward_open_10000, R.drawable.reward_open_50000, R.drawable.reward_open_100000, R.drawable.reward_open_500000};
    private final int[] drawableReward = {R.drawable.reward_1, R.drawable.reward_10, R.drawable.reward_50, R.drawable.reward_100, R.drawable.reward_300, R.drawable.reward_1000, R.drawable.reward_10000, R.drawable.reward_50000, R.drawable.reward_100000, R.drawable.reward_500000};

    public static HashMap<Integer, Integer> reward = new HashMap<>();
    public static HashMap<Integer, Integer> money = new HashMap<>();
    private int round = 1;
    private double offer;
    private int roundMoney, crossMoney, offerMoney, leftOverMoney, casesLeft;
    private int totalMoney = 661461;

    private final int [] rewardNum = {1, 10, 50, 100, 300, 1000, 10000, 50000, 100000, 500000};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choose = findViewById(R.id.choose);
        NoDeal = findViewById(R.id.Nodeal);
        Deal = findViewById(R.id.Deal);

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        resetGame();
    }

    private void resetGame() {
        NoDeal.setVisibility(View.INVISIBLE);
        Deal.setVisibility(View.INVISIBLE);

        ArrayList<SuitInfo> suitCases = new ArrayList<>();
        for(int i = 0; i <rewardNum.length; i++){
            SuitInfo suitInfo = new SuitInfo(rewardNum[i], false);
            suitCases.add(suitInfo);
        }
        Collections.shuffle(suitCases);
        /*ArrayList<Integer> shuffledNumIds = new ArrayList<>();
        for (int numId: drawableNumId) {
            shuffledNumIds.add(numId);
        }*/

        /*for(int i = 0; i < shuffledNumIds.size(); i++){

            SuitInfo suitInfo2 = new SuitInfo();
            ImageView imageView1 = findViewById(rewardId);
            imageView1.setImageResource(freshReward);
            imageView1.setTag(suitInfo2);
            //imageView1.setOnClickListener(this);
        }
                Collections.shuffle(shuffledDrawableIds);
        */


        for (int i = 0; i <suitCases.size() ; i++) {
            SuitInfo suitInfo = suitCases.get(i);
            int closeCase = imageViewCaseId[i];
            ImageView caseImage = findViewById(closeCase);
            caseImage.setImageResource(drawableCloseCase[i]);
            caseImage.setTag(suitInfo);
            caseImage.setOnClickListener(this);

            ImageView rewardImage = (ImageView) findViewById(imageViewNumId[i]);

            rewardImage.setImageResource(drawableReward[i]);
        }


            /*int imageViewId = imageViewCaseId[i];
            int drawableId = shuffledDrawableIds.get(i);
             //drawableCloseCase[i];
            int freshReward = drawableReward[i];
            int drawableNum = shuffledNumIds.get(i);
            int rewardId = imageViewNumId[i];

            SuitInfo suitInfo = new SuitInfo(imageViewId, drawableId, drawableNum, rewardId);
            ImageView imageView = findViewById(imageViewId);
            ImageView imageView1 = findViewById(rewardId);
            imageView1.setImageResource(freshReward);
            imageView.setImageResource(closeCase);
            imageView1.setTag(drawableNum, suitInfo);
            imageView.setTag(suitInfo);
            imageView.setOnClickListener(this);*/
        round = 1;
        casesToOpen = 4;
        offerMoney = 0;
        leftOverMoney = 0;
        casesLeft = 10;
        offer = 0;
        choose.setText("Choose " + casesToOpen + " Cases");
    }
    int casesToOpen = 4;

    @Override
    public void onClick(View v) {


        ImageView imageView = (ImageView) v;
        SuitInfo suitInfo = (SuitInfo) imageView.getTag();

        if (!suitInfo.isOpened() && casesToOpen != 0) {
            int reward = suitInfo.getReward();
            int index = findIndex(reward);
            // Open briefcase
            imageView.setImageResource(drawableCaseId[index]);
            //remove money
            ImageView rewardImage = (ImageView) findViewById(imageViewNumId[index]);
            rewardImage.setImageResource(drawableNumId[index]);
            //Set case as opened
            suitInfo.setOpened(true);
            casesToOpen -=1;
            casesLeft -=1;
            choose.setText("Choose " + casesToOpen + " Cases");

            roundMoney = getRewards(drawableCaseId[index]);
            Log.d("demo2", "onClick: " + roundMoney);
            crossMoney = getMoney(roundMoney);
            offerMoney += roundMoney;


        } else{
            NoDeal.setVisibility(View.VISIBLE);
            Deal.setVisibility(View.VISIBLE);
            leftOverMoney = totalMoney - offerMoney;
            if(casesLeft != 1){
                offer = makeBankOffer(leftOverMoney);
            }
            else{
                offer = leftOverMoney;
                choose.setText("Offer is: " + offer);
            }

            findViewById(R.id.Deal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choose.setText("You won $" + offer);
                }
            });
            findViewById(R.id.Nodeal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoDeal.setVisibility(View.INVISIBLE);
                    Deal.setVisibility(View.INVISIBLE);
                    round +=1;
                    if(round == 2){
                        casesToOpen = 4;
                        choose.setText("Choose " + casesToOpen + " Cases");
                    } else{
                        casesToOpen = 1;
                        choose.setText("Choose " + casesToOpen + " Cases");
                    }
                }
            });
        }
        if(round == 3){
            //int amount = suitInfo.getDrawableId();
            leftOverMoney = leftOverMoney - roundMoney;
            choose.setText("You have won: $" + leftOverMoney);



        }
    }
    public int findIndex(int value){
        return Arrays.binarySearch(rewardNum, value);
    }

    private double makeBankOffer(int offer) {

        double newOffer = calculateBankOffer(offer);
        choose.setText("Bank Offer : $" + newOffer);
        return newOffer;

    }
    // needs implementing.
    private double calculateBankOffer(int money) {
        int average = money / casesLeft;
        double percent = average * 0.6;

        return percent; // needs updating
    }

    private static void createHashMap(){
        reward.put(R.drawable.suitcase_open_1, 1);
        reward.put(R.drawable.suitcase_open_10, 10);
        reward.put(R.drawable.suitcase_open_50, 50);
        reward.put(R.drawable.suitcase_open_100, 100);
        reward.put(R.drawable.suitcase_open_300, 300);
        reward.put(R.drawable.suitcase_open_1000, 1000);
        reward.put(R.drawable.suitcase_open_10000, 10000);
        reward.put(R.drawable.suitcase_open_50000, 50000);
        reward.put(R.drawable.suitcase_open_100000, 100000);
        reward.put(R.drawable.suitcase_open_500000, 500000);
    }

    static int getRewards(int code){
        if(reward.size() == 0){
            createHashMap();
        }
        return reward.get(code);
    }

    private static void moneyHashMap(){
        money.put(1, R.drawable.reward_open_1);
        money.put(10, R.drawable.reward_open_10);
        money.put(50, R.drawable.reward_open_50);
        money.put(100, R.drawable.reward_open_100);
        money.put(300, R.drawable.reward_open_300);
        money.put(1000, R.drawable.reward_open_1000);
        money.put(10000, R.drawable.reward_open_10000);
        money.put(50000, R.drawable.reward_open_50000);
        money.put(100000, R.drawable.reward_open_100000);
        money.put(500000, R.drawable.reward_open_500000);
    }

    static int getMoney(int num){
        if(money.size() == 0){
            moneyHashMap();
        }
        return money.get(num);
    }
}