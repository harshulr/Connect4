package app.helloandroid.com.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow; 1 = red;
    int activePlayer = 0;
    boolean gameIsActive = true;
    // 2 means un played;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{20,27,34,41},{17,25,33,41},{38,39,40,41},{0,1,2,3},{1,2,3,4},{2,3,4,5},{3,4,5,6},{7,8,9,10},{8,9,10,11},{9,10,11,12},{10,11,12,13},{14,15,16,17},{15,16,17,18},{16,17,18,19},{17,18,19,20},{21,22,23,24},{22,23,24,25},{23,24,25,26},{24,25,26,27},{28,29,30,31},{29,30,31,32},{30,31,32,33},{31,32,33,34},{35,36,37,38},{36,37,38,39},{37,38,39,40},{0,7,14,21},{7,14,21,28},{14,21,28,35},{1,8,15,22},{8,15,22,29},{15,22,29,36},{2,9,16,23},{9,16,23,30},{16,23,30,37},{3,10,17,24},{10,17,24,31},{17,24,31,38},{4,11,18,25},{11,18,25,32},{18,25,32,39},{5,12,19,26},{12,19,26,33},{19,26,33,40},{6,13,20,27},{13,20,27,34},{0,8,16,24},{8,16,24,32},{16,24,32,40},{1,9,17,25},{9,17,25,33},{2,10,18,26},{10,18,26,34},{3,11,19,27},{7,15,23,31},{15,23,31,39},{14,22,30,38},{17,23,29,35},{11,17,23,29},{5,11,17,23},{3,9,15,21},{4,10,16,22},{10,16,22,28},{6,12,18,24},{12,18,24,30},{18,24,30,36},{13,19,25,31},{19,25,31,37},{20,26,32,38}};
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive) {

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
            for(int[] winningPosition : winningPositions){

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[2]] == gameState[winningPosition[3]] &&
                        gameState[winningPosition[0]] != 2){
                //Someone has won;
                gameIsActive = false;
                String winner = "";
                if(gameState[winningPosition[0]] == 0){
                    winner = "Yellow";
                }else if(gameState[winningPosition[0]] == 1){
                    winner = "Red";
                }
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
                }
                else {
                    boolean gameIsOver = true;
                    for(int counterState: gameState){
                        if(counterState == 2){
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }
    public void playAgain(View view){

        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for(int i=0; i<gameState.length;i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i<gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
