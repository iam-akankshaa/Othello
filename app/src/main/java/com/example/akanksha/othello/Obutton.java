package com.example.akanksha.othello;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class Obutton extends AppCompatButton{

    public Obutton(Context context) {

        super(context);


    }

    public  int player=-1;
    public  int row;
    public  int col;
    public  boolean isvalid=false;
    public  boolean reveal=false;
    public  int[] dirc={0,0,0,0,0,0,0,0};


    public void setPlayer(int p)
    {
        this.player=p;
    }

    public  int getPlayer()
    {
        return  this.player;
    }



}
