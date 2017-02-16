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

    /**
     * Register a foul
     *
     * @param view View calling the method
     */
    public void onClickFoul(View view) {
        ImageButton arrow = (ImageButton) findViewById(R.id.arrow);

        switch (currentPlayer) {
            case PLAYER_ONE:
                arrow.setRotation(90);
                currentPlayer = Player.PLAYER_TWO;

                playerTwoScore += 4;
                playerTwoScoreView.setText(String.format(Locale.ENGLISH, "%d", playerTwoScore));
                break;
            case PLAYER_TWO:
                arrow.setRotation(-90);
                currentPlayer = Player.PLAYER_ONE;

                playerOneScore += 4;
                playerOneScoreView.setText(String.format(Locale.ENGLISH, "%d", playerOneScore));
                break;
        }
    }

    /**
     * Open the results activity
     *
     * @param view View calling the method
     */
    public void onClickEndFrame(View view) {
        openResults();
    }

    /**
     * Get a ball button by colour
     *
     * @param colour Colour of the ball button
     * @return Ball button of colour
     */
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

    /**
     * Disable all ball buttons
     *
     * @param exceptRed If the red ball button be disabled
     */
    private void disableBallButtons(Boolean exceptRed) {
        if (!exceptRed) {
            getBallButton(BallColour.RED).setVisibility(View.INVISIBLE);
            TextView remaining = (TextView) findViewById(R.id.remaining_red_balls);
            remaining.setVisibility(View.INVISIBLE);
        }
        getBallButton(BallColour.YELLOW).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.GREEN).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.BROWN).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.BLUE).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.PINK).setVisibility(View.INVISIBLE);
        getBallButton(BallColour.BLACK).setVisibility(View.INVISIBLE);
    }

    /**
     * Enable all ball buttons
     *
     * @param exceptRed If the red ball button should be disabled
     */
    private void enableBallButtons(Boolean exceptRed) {
        if (!exceptRed) {
            getBallButton(BallColour.RED).setVisibility(View.VISIBLE);
            TextView remaining = (TextView) findViewById(R.id.remaining_red_balls);
            remaining.setVisibility(View.VISIBLE);
        }
        getBallButton(BallColour.YELLOW).setVisibility(View.VISIBLE);
        getBallButton(BallColour.GREEN).setVisibility(View.VISIBLE);
        getBallButton(BallColour.BROWN).setVisibility(View.VISIBLE);
        getBallButton(BallColour.BLUE).setVisibility(View.VISIBLE);
        getBallButton(BallColour.PINK).setVisibility(View.VISIBLE);
        getBallButton(BallColour.BLACK).setVisibility(View.VISIBLE);
    }

    /**
     * Enable a ball button of a certain colour
     *
     * @param colour Colour of ball button to enable
     */
    private void enableBallButton(BallColour colour) {
        switch (colour) {
            case RED:
                getBallButton(BallColour.RED).setVisibility(View.VISIBLE);
                TextView remaining = (TextView) findViewById(R.id.remaining_red_balls);
                remaining.setVisibility(View.VISIBLE);
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

    /**
     * Disable a ball button of a certain colour
     *
     * @param colour Colour of a ball button to disable
     */
    private void disableBallButton(BallColour colour) {
        switch (colour) {
            case RED:
                getBallButton(BallColour.RED).setVisibility(View.INVISIBLE);
                TextView remaining = (TextView) findViewById(R.id.remaining_red_balls);
                remaining.setVisibility(View.INVISIBLE);
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

    /**
     * Adjust scores and view when a ball button is clicked
     *
     * @param view View that called the method
     */
    public void onClickBall(View view) {
        if (view.getId() == R.id.red_ball || view.getId() == R.id.remaining_red_balls) {
            addScore(currentPlayer, 1);
            pottedReds++;
            disableBallButton(BallColour.RED);
            enableBallButtons(true);
            TextView remaining = (TextView) findViewById(R.id.remaining_red_balls);
            remaining.setText(String.format(Locale.ENGLISH, "x%d", 15 - pottedReds));

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
                    } else if (colourAfterRed) {
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
                    } else if (colourAfterRed) {
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
                    } else if (colourAfterRed) {
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
                    } else if (colourAfterRed) {
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
                    } else if (colourAfterRed) {
                        currentBall = BallColour.YELLOW;
                    } else {
                        currentBall = BallColour.RED;
                    }
                    break;
                case R.id.black_ball:
                    addScore(currentPlayer, 7);

                    if (pottedReds >= 15 && !colourAfterRed) {
                        openResults();
                    } else if (colourAfterRed) {
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

    /**
     * Change the active player
     *
     * @param view View calling the method
     */
    public void changePlayer(View view) {
        ImageButton arrow = (ImageButton) findViewById(R.id.arrow);

        if (view == findViewById(R.id.player_one_name) || view == findViewById(R.id.player_one_score)) {
            arrow.setRotation(-90);
            currentPlayer = Player.PLAYER_ONE;
        } else if (view == findViewById(R.id.player_two_name) || view == findViewById(R.id.player_two_score)) {
            arrow.setRotation(90);
            currentPlayer = Player.PLAYER_TWO;
        }


        if (currentPlayer == Player.PLAYER_ONE && view == arrow) {
            arrow.setRotation(90);
            currentPlayer = Player.PLAYER_TWO;
        } else if (view == arrow) {
            arrow.setRotation(-90);
            currentPlayer = Player.PLAYER_ONE;
        }

        if (currentBall == BallColour.RED) {
            disableBallButtons(true);
            enableBallButton(BallColour.RED);
        }
    }

    /**
     * Open the results activity
     */
    private void openResults() {
        Intent intent = new Intent(this, Results.class);
        Intent pastIntent = getIntent();

        intent.putExtra("playerOneName", playerOneName);
        intent.putExtra("playerTwoName", playerTwoName);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);
        intent.putExtra("playerOneWins", pastIntent.getIntExtra("playerOneWins", 0));
        intent.putExtra("playerTwoWins", pastIntent.getIntExtra("playerTwoWins", 0));
        intent.putExtra("framesComplete", pastIntent.getIntExtra("framesComplete", 0));

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        if (savedInstanceState != null) {
            playerOneScore = savedInstanceState.getInt("playerOneScore", 0);
            playerTwoScore = savedInstanceState.getInt("playerTwoScore", 0);
            pottedReds = savedInstanceState.getInt("pottedReds", 0);
            colourAfterRed = savedInstanceState.getBoolean("colourAfterRed", false);

            switch (savedInstanceState.getInt("currentPlayer", 0)) {
                case 0:
                    currentPlayer = Player.PLAYER_ONE;
                    break;
                case 1:
                    currentPlayer = Player.PLAYER_TWO;
                    break;
            }

            switch (savedInstanceState.getInt("currentBall", 0)) {
                case 0:
                    currentBall = BallColour.RED;
                    break;
                case 1:
                    currentBall = BallColour.YELLOW;
                    break;
                case 2:
                    currentBall = BallColour.GREEN;
                    break;
                case 3:
                    currentBall = BallColour.BROWN;
                    break;
                case 4:
                    currentBall = BallColour.BLUE;
                    break;
                case 5:
                    currentBall = BallColour.PINK;
                    break;
                case 6:
                    currentBall = BallColour.BLACK;
                    break;
            }
        }

        Intent pastIntent = getIntent();
        playerOneName = pastIntent.getStringExtra("playerOneName");
        playerTwoName = pastIntent.getStringExtra("playerTwoName");

        TextView playerOneNameView = (TextView) findViewById(R.id.player_one_name);
        TextView playerTwoNameView = (TextView) findViewById(R.id.player_two_name);

        playerOneNameView.setText(playerOneName);
        playerTwoNameView.setText(playerTwoName);

        playerOneScoreView = (TextView) findViewById(R.id.player_one_score);
        playerTwoScoreView = (TextView) findViewById(R.id.player_two_score);

        TextView remaining = (TextView) findViewById(R.id.remaining_red_balls);
        remaining.setText(String.format(Locale.ENGLISH, "x%d", 15 - pottedReds));

        addScore(Player.PLAYER_ONE, 0);
        addScore(Player.PLAYER_TWO, 0);
    }

    /**
     * Add a score to a player
     *
     * @param player Player to add the score to
     * @param score Score to add to the player's score
     */
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("playerOneScore", playerOneScore);
        outState.putInt("playerTwoScore", playerTwoScore);
        outState.putInt("pottedReds", pottedReds);
        outState.putInt("currentPlayer", currentPlayer.getValue());
        outState.putInt("currentBall", currentBall.getValue());
        outState.putBoolean("colourAfterRed", colourAfterRed);
    }

    /**
     * Available ball colours
     */
    private enum BallColour {
        RED(0), YELLOW(1), GREEN(2), BROWN(3), BLUE(4), PINK(5), BLACK(6);

        private final int value;

        BallColour(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Available players
     */
    private enum Player {
        PLAYER_ONE(0), PLAYER_TWO(1);

        private final int value;

        Player(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}