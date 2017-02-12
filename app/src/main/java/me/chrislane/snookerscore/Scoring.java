package me.chrislane.snookerscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class Scoring extends AppCompatActivity {
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private TextView playerOneScoreView;
    private TextView playerTwoScoreView;
    private Player currentPlayer = Player.PLAYER_ONE;

    public void onClickBall(View view) {
        switch (view.getId()) {
            case R.id.red_ball:
                addScore(currentPlayer, 1);
                return;
            case R.id.yellow_ball:
                addScore(currentPlayer, 2);
                return;
            case R.id.green_ball:
                addScore(currentPlayer, 3);
                return;
            case R.id.brown_ball:
                addScore(currentPlayer, 4);
                return;
            case R.id.blue_ball:
                addScore(currentPlayer, 5);
                return;
            case R.id.pink_ball:
                addScore(currentPlayer, 6);
                return;
            case R.id.black_ball:
                addScore(currentPlayer, 7);
                return;
            default:
        }
    }

    public void onClickArrow(View view) {
        ImageButton arrow = (ImageButton) findViewById(R.id.arrow);

        if (currentPlayer == Player.PLAYER_ONE) {
            arrow.setRotation(90);
            currentPlayer = Player.PLAYER_TWO;
        } else {
            arrow.setRotation(-90);
            currentPlayer = Player.PLAYER_ONE;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        Intent pastIntent = getIntent();
        TextView playerOneName = (TextView) findViewById(R.id.player_one_name);
        TextView playerTwoName = (TextView) findViewById(R.id.player_two_name);

        playerOneName.setText(pastIntent.getStringExtra("playerOneName"));
        playerTwoName.setText(pastIntent.getStringExtra("playerTwoName"));

        playerOneScoreView = (TextView) findViewById(R.id.player_one_score);
        playerTwoScoreView = (TextView) findViewById(R.id.player_two_score);

        addScore(Player.PLAYER_ONE, 0);
        addScore(Player.PLAYER_TWO, 0);
    }

    private void addScore(Player player, int score) {
        switch (player) {
            case PLAYER_ONE:
                playerOneScore += score;
                playerOneScoreView.setText(String.format(Locale.ENGLISH, "%d", playerOneScore));
                return;
            case PLAYER_TWO:
                playerTwoScore += score;
                playerTwoScoreView.setText(String.format(Locale.ENGLISH, "%d", playerTwoScore));
        }
    }

    private enum Player {
        PLAYER_ONE, PLAYER_TWO
    }
}