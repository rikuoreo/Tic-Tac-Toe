package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    int currentUser = 0;
    int[] played = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsOver = false;

    public void fadeIn(View view){
        ImageView counter = (ImageView) view;

        if((played[Integer.parseInt(counter.getTag().toString())]==2) && !gameIsOver) {
            if (currentUser == 0) {
                counter.setImageResource(R.drawable.ttt_x);
                played[Integer.parseInt(counter.getTag().toString())] = currentUser;
                currentUser = 1;
            } else {
                counter.setImageResource(R.drawable.ttt_o);
                played[Integer.parseInt(counter.getTag().toString())] = currentUser;
                currentUser = 0;
            }

            counter.animate().alpha(1f).setDuration(500);
        }

        String winner;
        if(currentUser==0)
        {
            winner = "Player 2";
        }else{
            winner = "Player 1";
        }


        //Checking if someone has won
        for(int[] position:winningPositions){
            if(played[position[0]]==played[position[1]] && played[position[1]]==played[position[2]] && played[position[0]]!=2){
                Toast.makeText(this, winner + " has won the game!!", Toast.LENGTH_LONG).show();
                gameIsOver = true;
            }
        }

        //Checking for a draw!
        if(!gameIsOver) {
            gameIsOver = true;
            for (int i = 0; i < 9; i++) {
                if (played[i] == 2) {
                    gameIsOver = false;
                    break;
                }
            }
            if (gameIsOver) {
                Toast.makeText(this, "It's a draw!!!", Toast.LENGTH_LONG).show();
            }
        }

        if(gameIsOver) {
            Button btn = (Button) findViewById(R.id.playAgainButton);
            btn.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view){
        GridLayout grid = findViewById(R.id.GridLayout);

        String s = String.valueOf(grid.getChildCount());
        int i= Integer.parseInt(s);

        ImageView image;
        for(int j=0; j<i; j++)
        {
            image = (ImageView) grid.getChildAt(j);
            image.setImageResource(0);
            played[j] = 2;
            image.setAlpha(0f);
        }

        gameIsOver = false;
        Button btn = (Button) findViewById(R.id.playAgainButton);
        btn.setVisibility(View.INVISIBLE);
        currentUser=0;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
