package net.gitgudstudios.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // yellow = 0    red = 1
    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPostions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View v) {
        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        ImageView counter = (ImageView)v;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive == true) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for (int[] winningPostion : winningPostions){
                if (gameState[winningPostion[0]] == gameState[winningPostion[1]] &&
                        gameState[winningPostion[1]] == gameState[winningPostion[2]] &&
                        gameState[winningPostion[0]] != 2) {

                    gameIsActive = false;
                    String winner = "Red";

                    if (gameState[winningPostion[0]] == 0) {
                        winner = "Yellow";
                    }

                    winnerMessage.setText(winner + " has won!");

                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int num : gameState){
                        if (num == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        winnerMessage.setText("The game is a draw!");
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View v){
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;

        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.playField);
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
}
