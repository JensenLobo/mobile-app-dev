package com.example.dealornodeal;

public class SuitInfo {
   private  int reward;
   private int drawableId;
   private boolean isOpened = false;

    public SuitInfo(int reward, boolean isOpened) {
        this.reward = reward;
        this.isOpened = isOpened;
    }

    public int getReward() {
        return reward;
    }


    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
