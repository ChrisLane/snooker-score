package me.chrislane.snookerscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    private String playerOneName;
    private String playerTwoName;
    private int playerOneScore;
    private int playerTwoScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent pastIntent = getIntent();
        playerOneName = pastIntent.getStringExtra("playerOneName");
        playerTwoName = pastIntent.getStringExtra("playerTwoName");

        TextView playerOneNameView = (TextView) findViewById(R.id.player_one);
        TextView playerTwoNameView = (TextView) findViewById(R.id.player_two);

        playerOneNameView.setText(playerOneName);
        playerTwoNameView.setText(playerTwoName);

        playerOneScore = pastIntent.getIntExtra("playerOneScore", 0);
        playerTwoScore = pastIntent.getIntExtra("playerTwoScore", 0);

        TextView winningPlayerView = (TextView) findViewById(R.id.winning_player);

        if (playerOneScore > playerTwoScore) {
            winningPlayerView.setText(playerOneName);
        } else {
            winningPlayerView.setText(playerTwoName);
        }

    }
}
