package edu.uncc.evaluation01;

import java.io.Serializable;

public class Bill implements Serializable {
    String amount, tip;

    public Bill(String amount, String tip) {
        this.amount = amount;
        this.tip = tip;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
