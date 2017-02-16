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
    private String playerOneName;
    private String playerTwoName;
    private int winningScore;
    private int losingScore;
    private int playerOneWins;
    private int playerTwoWins;
    private int framesComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent pastIntent = getIntent();
        playerOneName = pastIntent.getStringExtra("playerOneName");
        playerTwoName = pastIntent.getStringExtra("playerTwoName");
        playerOneWins = pastIntent.getIntExtra("playerOneWins", 0);
        playerTwoWins = pastIntent.getIntExtra("playerTwoWins", 0);
        framesComplete = pastIntent.getIntExtra("framesComplete", 0);
        framesComplete += 1;

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
            playerOneWins += 1;
        } else if (playerTwoScore > playerOneScore) {
            winner = playerTwoName;
            winningScore = playerOneScore;
            loser = playerOneName;
            losingScore = playerTwoScore;
            playerTwoWins += 1;
        }

        winningPlayerView.setText(winner);

        if (playerOneScore == playerTwoScore) {
            winningPlayerView.setText(R.string.draw);
            TextView won = (TextView) findViewById(R.id.won);
            won.setText(R.string.no_winners);
        }

        TextView playerOneWinsView = (TextView) findViewById(R.id.player_one_wins);
        TextView playerTwoWinsView = (TextView) findViewById(R.id.player_two_wins);
        playerOneWinsView.setText(String.format(Locale.ENGLISH, "%d", playerOneWins));
        playerTwoWinsView.setText(String.format(Locale.ENGLISH, "%d", playerTwoWins));
    }

    public void onClickShare(View view) {
        String shareText = "%s won %d - %d against %s in a snooker frame";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Snooker Frame");
        intent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.ENGLISH, shareText, winner, winningScore, losingScore, loser));
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    public void onClickNextFrame(View view) {
        Intent intent = new Intent(this, Scoring.class);
        intent.putExtra("playerOneName", playerOneName);
        intent.putExtra("playerTwoName", playerTwoName);
        intent.putExtra("playerOneWins", playerOneWins);
        intent.putExtra("playerTwoWins", playerTwoWins);
        intent.putExtra("framesComplete", framesComplete);
        startActivity(intent);
    }
}
