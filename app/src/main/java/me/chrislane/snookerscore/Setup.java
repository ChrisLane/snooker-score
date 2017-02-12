package me.chrislane.snookerscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    public void startScoring(View view) {
        Intent intent = new Intent(this, Scoring.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        EditText playerOne = (EditText) findViewById(R.id.name_one_entry);
        EditText playerTwo = (EditText) findViewById(R.id.name_two_entry);
        String playerOneName = playerOne.getText().toString();
        String playerTwoName = playerTwo.getText().toString();

        if (playerOneName.isEmpty()) {
            playerOneName = "Player 1";
        }
        if (playerTwoName.isEmpty()) {
            playerTwoName = "Player 2";
        }

        intent.putExtra("playerOneName", playerOneName).putExtra("playerTwoName", playerTwoName);

        startActivity(intent);
    }
}
