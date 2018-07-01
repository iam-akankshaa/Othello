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

    public  static final int Player_B=0;
    public  static final int Player_W=1;

    public static final int INCOMPLETE = 0;
    public static final int Player_B_Won = 1;
    public  static final int Player_W_Won = 2;
    public static final int DRAW = 4;

    public int Currentstatus;
    public int currentplayer;

    int[] one = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] two = {-1, 0, 1, -1, 1, -1, 0, 1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootlayout=findViewById(R.id.root);

        setupBoard();


    }

    public void setupBoard()
    {
        currentstatus=INCOMPLETE;
        currentplayer=Player_B;
        rows=new ArrayList<>();
        board=new Obutton[size][size];

        rootlayout.removeAllViews();

        for(int i=0;i<size;i++)
        {
            LinearLayout linearLayout=new LinearLayout(this);
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(param);

            //linearLayout.setBackgroundColor(this.getResources().getColor(R.color.green));
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
                b.setBackground(this.getResources().getDrawable(R.drawable.button_bg));
                b.setEnabled(false);
                b.row=i;
                b.col=j;

                LinearLayout row = rows.get(i);

                row.addView(b);
                board[i][j] = b;
            }


        }


        int[] startrow={3,3,4,4};
        int[] startcol={3,4,3,4};


        for(int i=0;i<4;i++) {

            if(i== 0  || i==3) {

                Obutton b = board[startrow[i]][startcol[i]];
                b.setBackground(this.getResources().getDrawable(R.drawable.white));
                b.reveal=true;
                b.setPlayer(Player_W);
                b.setEnabled(false);

            }

            if(i== 1 || i==2) {

                Obutton b = board[startrow[i]][startcol[i]];
                b.setBackground(this.getResources().getDrawable(R.drawable.black));
                b.reveal=true;
                b.setPlayer(Player_B);
                b.setEnabled(false);
            }


        }
        setValidMoveB(3,3);
        setValidMoveB(4,4);

    }

    public void setValidMoveB(int r, int c)
    {
        for(int i=0;i<8;i++)
        {
            int row=r+one[i];
            int col=c+two[i];
            if(row>=0 && row<size && col>=0 && col <size) {
                Obutton b = board[row][col];

                if (b.reveal == false && b.isvalid == false) {

                    if(neighboursB(b.row, b.col)) {
                        b.isvalid = true;
                        b.setEnabled(true);
                        b.setBackgroundColor(this.getResources().getColor(R.color.lightgreen));


                    }
                }

            }

        }

        return;

    }

    public void setValidMoveW(int r, int c)
    {
        for(int i=0;i<8;i++)
        {
            int row=r+one[i];
            int col=c+two[i];
            if(row>=0 && row<size && col>=0 && col <size) {
                Obutton b = board[row][col];

                if (b.reveal == false && b.isvalid == false) {

                    if(neighboursW(b.row, b.col)) {
                        b.isvalid = true;
                        b.setEnabled(true);
                        b.setBackgroundColor(this.getResources().getColor(R.color.lightgreen));


                    }
                }

            }

        }

        return;

    }

    public boolean neighboursB(int r,int c)
    {
        boolean valid = false;
        for(int i=0;i<8;i++)
        {
            boolean dir=false;
            int a=r+one[i];
            int b=c+two[i];

          if(a>=0 && a<size && b>=0 && b <size && board[a][b].player == 1) {
              //Obutton bt = board[a][b];

                  int neighrow = a + one[i];
                  int neighcol = b + two[i];
                  while (neighrow >= 0 && neighrow < size && neighcol >= 0 && neighcol < size) {
                      Obutton butn = board[neighrow][neighcol];
                      if (butn.player == 0) {

                          dir = true;
                          break;
                      }

                      if (butn.player == -1)
                          break;

                      neighrow = neighrow + one[i];
                      neighcol = neighcol + two[i];

                  }

              }



          if(dir == true) {
              board[r][c].dirc[i] = 1;
              valid = true;

          }


        }

        if(valid == true)
            return true;
        else
            return false;



    }


    public boolean neighboursW(int r,int c)
    {
        boolean valid = false;
        for(int i=0;i<8;i++)
        {
            boolean dir=false;
            int a=r+one[i];
            int b=c+two[i];

            if(a>=0 && a<size && b>=0 && b <size && board[a][b].player == 0) {
                //Obutton bt = board[a][b];

                int neighrow = a + one[i];
                int neighcol = b + two[i];
                while (neighrow >= 0 && neighrow < size && neighcol >= 0 && neighcol < size) {
                    Obutton butn = board[neighrow][neighcol];
                    if (butn.player == 1) {

                        dir = true;
                        break;
                    }

                    if (butn.player == -1)
                        break;

                    neighrow = neighrow + one[i];
                    neighcol = neighcol + two[i];

                }

            }



            if(dir == true) {
                board[r][c].dirc[i] = 1;
                valid = true;

            }


        }

        if(valid == true)
            return true;
        else
            return false;



    }

    public void setValidMove()
    {
        if(currentplayer == Player_B)
        {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Obutton b = board[i][j];

                    if(b.player == 1)
                        setValidMoveB(b.row, b.col);

                }
            }
        }

        else
        {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Obutton b = board[i][j];

                    if(b.player == 0)
                        setValidMoveW(b.row, b.col);


                }
            }
        }


    }


    @Override
    public void onClick(View view) {


        if(currentstatus == INCOMPLETE)
        {
            Obutton b = (Obutton) view;
            if(b.isvalid)
            {
                if(currentplayer == Player_B) {
                    b.isvalid=false;
                    b.setPlayer(Player_B);
                    b.reveal = true;
                    b.setEnabled(false);
                    b.setBackground(this.getResources().getDrawable(R.drawable.black));
                    changeBallB(b.row, b.col);
                }
                else{
                    b.isvalid=false;
                    b.setPlayer(Player_W);
                    b.reveal=true;
                    b.setEnabled(false);
                    b.setBackground(this.getResources().getDrawable(R.drawable.white));
                    changeBallW(b.row,b.col);
                }

                //checkGameStatus();
                togglePlayer();
                setValidMove();
            }
        }


    }

    public void changeBallB(int r, int c)
    {
        Obutton b =board[r][c];

        for(int i=0;i<8;i++)
       {
           if(b.dirc[i] == 1)
           {
               int neighrow = r + one[i];
               int neighcol = c + two[i];
               while (neighrow >= 0 && neighrow < size && neighcol >= 0 && neighcol < size) {
                   Obutton butn = board[neighrow][neighcol];
                   if (butn.player == 0 || butn.player == -1) {

                       break;
                   }

                   if (butn.player == 1)
                   {
                       butn.setPlayer(Player_B);
                       butn.reveal=true;
                       butn.setEnabled(false);
                       butn.setBackground(this.getResources().getDrawable(R.drawable.black));


                   }


                   neighrow = neighrow + one[i];
                   neighcol = neighcol + two[i];

               }

           }
       }



    }


    public void changeBallW(int r, int c)
    {
        Obutton b =board[r][c];

        for(int i=0;i<8;i++)
        {
            if(b.dirc[i] == 1)
            {
                int neighrow = r + one[i];
                int neighcol = c + two[i];
                while (neighrow >= 0 && neighrow < size && neighcol >= 0 && neighcol < size) {
                    Obutton butn = board[neighrow][neighcol];
                    if (butn.player == 1 || butn.player == -1) {

                        break;
                    }

                    if (butn.player == 0)
                    {
                        butn.setPlayer(Player_W);
                        butn.reveal=true;
                        butn.setEnabled(false);
                        butn.setBackground(this.getResources().getDrawable(R.drawable.white));


                    }


                    neighrow = neighrow + one[i];
                    neighcol = neighcol + two[i];

                }

            }
        }



    }


    public  void checkGameStatus()
    {
        int blackPoint=0;
        int whitePoint=0;

        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                Obutton b=board[i][j];


            }
        }



    }

    public void togglePlayer()
    {
        //Disabling of earlier setted Valid Moves
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                Obutton b=board[i][j];
                if(b.isvalid)
                {
                    b.isvalid=false;
                    b.setEnabled(false);
                    b.setBackground(this.getResources().getDrawable(R.drawable.button_bg));

                }
            }
        }

        if(currentplayer == Player_B)
            currentplayer=Player_W;
        else
            currentplayer=Player_B;

    }


}
