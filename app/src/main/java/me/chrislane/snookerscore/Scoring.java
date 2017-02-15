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
    private int pottedReds = 0;
    private TextView playerOneScoreView;
    private TextView playerTwoScoreView;
    private Player currentPlayer = Player.PLAYER_ONE;
    private BallColour currentBall = BallColour.RED;
    private boolean colourAfterRed = false;
    private String playerOneName;
    private String playerTwoName;

    private enum BallColour {
        RED, YELLOW, GREEN, BROWN, BLUE, PINK, BLACK
    }

    private ImageButton getBallButton(BallColour colour) {
        switch (colour) {
            case RED:
                return (ImageButton) findViewById(R.id.red_ball);
            case YELLOW:
                return (ImageButton) findViewById(R.id.yellow_ball);
            case GREEN:
                return (ImageButton) findViewById(R.id.green_ball);
            case BROWN:
                return (ImageButton) findViewById(R.id.brown_ball);
            case BLUE:
                return (ImageButton) findViewById(R.id.blue_ball);
            case PINK:
                return (ImageButton) findViewById(R.id.pink_ball);
            case BLACK:
        }
        return (ImageButton) findViewById(R.id.black_ball);
    }

    private void disableBallButtons(Boolean exceptRed) {
        if (!exceptRed) {
            getBallButton(BallColour.RED).setVisibility(View.INVISIBLE);
        }
        getBallButton(BallColour.YELLOW).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.GREEN).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.BROWN).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.BLUE).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.PINK).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.BLACK).setVisibility(View.INVISIBLE);
    }

    private void enableBallButtons(Boolean exceptRed) {
        if (!exceptRed) {
            getBallButton(BallColour.RED).setVisibility(View.VISIBLE);
        }
        getBallButton(BallColour.YELLOW).setVisibility(View.VISIBLE);
        getBallButton(BallColour.GREEN).setVisibility(View.VISIBLE);
        getBallButton(BallColour.BROWN).setVisibility(View.VISIBLE);
        getBallButton(BallColour.BLUE).setVisibility(View.VISIBLE);
        getBallButton(BallColour.PINK).setVisibility(View.VISIBLE);
        getBallButton(BallColour.BLACK).setVisibility(View.VISIBLE);
    }

    private void enableBallButton(BallColour colour) {
        switch (colour) {
            case RED:
                getBallButton(BallColour.RED).setVisibility(View.VISIBLE);
                break;
            case YELLOW:
                getBallButton(BallColour.YELLOW).setVisibility(View.VISIBLE);
                break;
            case GREEN:
                getBallButton(BallColour.GREEN).setVisibility(View.VISIBLE);
                break;
            case BROWN:
                getBallButton(BallColour.BROWN).setVisibility(View.VISIBLE);
                break;
            case BLUE:
                getBallButton(BallColour.BLUE).setVisibility(View.VISIBLE);
                break;
            case PINK:
                getBallButton(BallColour.PINK).setVisibility(View.VISIBLE);
                break;
            case BLACK:
                getBallButton(BallColour.BLACK).setVisibility(View.VISIBLE);
                break;
        }
    }

    private void disableBallButton(BallColour colour) {
        switch (colour) {
            case RED:
                getBallButton(BallColour.RED).setVisibility(View.INVISIBLE);
                break;
            case YELLOW:
                getBallButton(BallColour.YELLOW).setVisibility(View.INVISIBLE);
                break;
            case GREEN:
                getBallButton(BallColour.GREEN).setVisibility(View.INVISIBLE);
                break;
            case BROWN:
                getBallButton(BallColour.BROWN).setVisibility(View.INVISIBLE);
                break;
            case BLUE:
                getBallButton(BallColour.BLUE).setVisibility(View.INVISIBLE);
                break;
            case PINK:
                getBallButton(BallColour.PINK).setVisibility(View.INVISIBLE);
                break;
            case BLACK:
                getBallButton(BallColour.BLACK).setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void onClickBall(View view) {
        if (view.getId() == R.id.red_ball) {
            addScore(currentPlayer, 1);
            pottedReds++;
            disableBallButton(BallColour.RED);
            enableBallButtons(true);

            if (pottedReds >= 15) {
                colourAfterRed = true;
            }
        } else {
            switch (view.getId()) {
                case R.id.red_ball:
                    break;
                case R.id.yellow_ball:
                    addScore(currentPlayer, 2);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        currentBall = BallColour.GREEN;
                        disableBallButton(BallColour.YELLOW);
                    } else if (colourAfterRed){
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                case R.id.green_ball:
                    addScore(currentPlayer, 3);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        currentBall = BallColour.BROWN;
                        disableBallButton(BallColour.GREEN);
                    } else if (colourAfterRed){
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                case R.id.brown_ball:
                    addScore(currentPlayer, 4);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        currentBall = BallColour.BLUE;
                        disableBallButton(BallColour.BROWN);
                    } else if (colourAfterRed){
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                case R.id.blue_ball:
                    addScore(currentPlayer, 5);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        currentBall = BallColour.PINK;
                        disableBallButton(BallColour.BLUE);
                    } else if (colourAfterRed){
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                case R.id.pink_ball:
                    addScore(currentPlayer, 6);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        currentBall = BallColour.BLACK;
                        disableBallButton(BallColour.PINK);
                    } else if (colourAfterRed){
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                case R.id.black_ball:
                    addScore(currentPlayer, 7);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        openResults();
                    } else if (colourAfterRed){
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                default:
            }

            if (currentBall == BallColour.YELLOW) {
                colourAfterRed = false;
                disableBallButtons(false);
            }

            enableBallButton(currentBall);

            // Red balls still left. Show red button, hide non-reds
            if (pottedReds < 15 && currentBall == BallColour.RED) {
                disableBallButtons(true);
            }
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

        if (currentBall == BallColour.RED) {
            disableBallButtons(true);
            enableBallButton(BallColour.RED);
        }
    }

    private void openResults() {
        Intent intent = new Intent(this, Results.class);

        intent.putExtra("playerOneName", playerOneName);
        intent.putExtra("playerTwoName", playerTwoName);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        Intent pastIntent = getIntent();
        playerOneName = pastIntent.getStringExtra("playerOneName");
        playerTwoName = pastIntent.getStringExtra("playerTwoName");

        TextView playerOneNameView = (TextView) findViewById(R.id.player_one_name);
        TextView playerTwoNameView = (TextView) findViewById(R.id.player_two_name);

        playerOneNameView.setText(playerOneName);
        playerTwoNameView.setText(playerTwoName);

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
                break;
            case PLAYER_TWO:
                playerTwoScore += score;
                playerTwoScoreView.setText(String.format(Locale.ENGLISH, "%d", playerTwoScore));
        }
    }

    private enum Player {
        PLAYER_ONE, PLAYER_TWO
    }
}