package me.chrislane.snookerscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Results extends AppCompatActivity {
    private String winner;
    private String loser;
    private int winningScore;
    private int losingScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent pastIntent = getIntent();
        String playerOneName = pastIntent.getStringExtra("playerOneName");
        String playerTwoName = pastIntent.getStringExtra("playerTwoName");

        TextView playerOneNameView = (TextView) findViewById(R.id.player_one);
        TextView playerTwoNameView = (TextView) findViewById(R.id.player_two);

        playerOneNameView.setText(playerOneName);
        playerTwoNameView.setText(playerTwoName);

        int playerOneScore = pastIntent.getIntExtra("playerOneScore", 0);
        int playerTwoScore = pastIntent.getIntExtra("playerTwoScore", 0);

        TextView playerOneScoreView = (TextView) findViewById(R.id.player_one_score);
        TextView playerTwoScoreView = (TextView) findViewById(R.id.player_two_score);

        playerOneScoreView.setText(String.format(Locale.ENGLISH, "%d", playerOneScore));
        playerTwoScoreView.setText(String.format(Locale.ENGLISH, "%d", playerTwoScore));

        TextView winningPlayerView = (TextView) findViewById(R.id.winning_player);

        if (playerOneScore > playerTwoScore) {
            winner = playerOneName;
            winningScore = playerOneScore;
            loser = playerTwoName;
            losingScore = playerTwoScore;
        } else {
            winner = playerTwoName;
            winningScore = playerOneScore;
            loser = playerOneName;
            losingScore = playerTwoScore;
        }

        winningPlayerView.setText(winner);
    }

    public void onClickShare(View view) {
        String shareText = "%s won %d - %d against %s in a snooker frame";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Snooker Frame");
        intent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.ENGLISH, shareText, winner, winningScore, losingScore, loser));
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
