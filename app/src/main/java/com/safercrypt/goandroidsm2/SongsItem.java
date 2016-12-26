package com.safercrypt.goandroidsm2;

/**
 * Created by pavelsafronov on 26.12.16.
 */

public class SongsItem {
    private int i = 1;
    private String s;

    public SongsItem(String s) {
        this.s = s;
    }

    // метод увеличивает занчение i на 1
    public void increaseMyI(){
        i++;
    }
    public void setI(int i) {
        this.i = i;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getS() {

        return s;
    }

    public int getI() {
        return i;
    }

    @Override
    public String toString() {
        return  i + " " + s + "\n";
    }
}

