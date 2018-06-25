package com.example.akanksha.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public int currentstatus;
    LinearLayout rootlayout;
    int size=8;
    ArrayList<LinearLayout> rows;
    Obutton [][] board;

    public static final int INCOMPLETE = 0;
    public static final int WON = 1;
    public  static final int LOST=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootlayout=findViewById(R.id.root);

        setupBoard();


    }

    public void setupBoard()
    {
        rows=new ArrayList<>();
        board=new Obutton[size][size];

        rootlayout.removeAllViews();

        for(int i=0;i<size;i++)
        {
            LinearLayout linearLayout=new LinearLayout(this);
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(param);

            rootlayout.addView(linearLayout);
            rows.add(linearLayout);



        }


        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                Obutton b = new Obutton(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                layoutParams.rightMargin = -8;
                layoutParams.leftMargin = -8;
                layoutParams.topMargin = -12;
                layoutParams.bottomMargin = -12;
                b.setLayoutParams(layoutParams);
                b.setOnClickListener(this);

                LinearLayout row = rows.get(i);

                row.addView(b);
                board[i][j] = b;
            }


        }


    }


    @Override
    public void onClick(View view) {



    }
}
