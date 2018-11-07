package com.example.lionellloh.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = basketball
    //1 = soccerball

    boolean gameIsActive = true;
    int activePlayer = 0;

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8},
                                {0,4,8},
                                {2,4,6}};


    public void dropIn(View view){

        ImageView ball = (ImageView) view;

        int tappedCounter = Integer.parseInt(ball.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            ball.setTranslationY(-1000f);

            if (activePlayer == 0) {
                ball.setImageResource(R.drawable.bball);
                activePlayer = 1;
            }

            else if (activePlayer == 1){
                ball.setImageResource(R.drawable.sball);
                activePlayer = 0;
            }

            ball.animate().translationYBy(1000f).rotation(360).setDuration(600);
        }

        for (int[] winningPosition : winningPositions){

            if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                    (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                    (gameState[winningPosition[0]] != 2)){

                //someone has won!

                String winner = "Soccerball";

                if (gameState[winningPosition[0]] == 0){
                    winner = "Basketball";
                }



                TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has won!");
                LinearLayout winningLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

                winningLayout.setVisibility(View.VISIBLE);
                gameIsActive = false;
            }
        }
    }

    public void playAgain(View view){

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i =0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout2);

        for (int i =0 ; i < gridLayout.getChildCount(); i++){

            //Reset the image to nothing
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
