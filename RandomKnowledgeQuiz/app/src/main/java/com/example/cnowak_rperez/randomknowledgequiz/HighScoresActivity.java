package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HighScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        final TextView geographyHighScoreLabel = (TextView)findViewById(R.id.geographyHighScore);
        final TextView animalHighScoreLabel = (TextView)findViewById(R.id.animalHighScore);
        final TextView historyHighScoreLabel = (TextView)findViewById(R.id.historyHighScore);
        final TextView sportsHighScoreLabel = (TextView)findViewById(R.id.sportsHighScore);
        final TextView randomTriviaHighScoreLabel = (TextView)findViewById(R.id.randomTriviaHighScore);
        //get settings
        SharedPreferences settings = getSharedPreferences("QuizSettings", 0);
        //get high score names from settings
        String geographyHighScoreName = settings.getString("geographyHighScoreName", "ERROR");
        String animalHighScoreName = settings.getString("animalHighScoreName", "ERROR");
        String historyHighScoreName = settings.getString("historyHighScoreName", "ERROR");
        String sportsHighScoreName = settings.getString("sportsHighScoreName", "ERROR");
        String randomTriviaHighScoreName = settings.getString("randomTriviaHighScoreName", "ERROR");
        //get high score values from settings
        int geographyHighScoreValue = settings.getInt("geographyHighScoreValue", 1);
        int animalHighScoreValue = settings.getInt("animalHighScoreValue", 1);
        int historyHighScoreValue = settings.getInt("historyHighScoreValue", 1);
        int sportsHighScoreValue = settings.getInt("sportsHighScoreValue", 1);
        int randomTriviaHighScoreValue = settings.getInt("randomTriviaHighScoreValue", 1);
        //set high score labels according to what the current settings say they are
        geographyHighScoreLabel.setText(geographyHighScoreName + " - " + geographyHighScoreValue + "/10");
        animalHighScoreLabel.setText(animalHighScoreName + " - " + animalHighScoreValue + "/10");
        historyHighScoreLabel.setText(historyHighScoreName + " - " + historyHighScoreValue + "/10");
        sportsHighScoreLabel.setText(sportsHighScoreName + " - " + sportsHighScoreValue + "/10");
        randomTriviaHighScoreLabel.setText(randomTriviaHighScoreName + " - " + randomTriviaHighScoreValue + "/10");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
