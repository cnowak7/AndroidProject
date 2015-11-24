package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //default time limit(seconds) per question is 20
    private int timeLimit;
    //default instant feedback setting is false
    private boolean instantFeedbackEnabled;
    static final int req_code = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("MAIN ACTIVITY CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences quizSettings = getSharedPreferences("QuizSettings", 0);
        this.timeLimit = quizSettings.getInt("timeLimit", 20);
        this.instantFeedbackEnabled = quizSettings.getBoolean("instantFeedbackEnabled", false);

        Button chooseAQuizButton = (Button)findViewById(R.id.chooseAQuizButton);
        Button viewHighScoresButton = (Button)findViewById(R.id.viewHighScoresButton);
        final Button settingsButton = (Button)findViewById(R.id.settingsButton);

        final View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                Button tappedButton = (Button)v;
                switch (tappedButton.getId()) {
                    case R.id.chooseAQuizButton:
                        Intent quizListIntent = new Intent(MainActivity.this, QuizListActivity.class);
                        quizListIntent.putExtra("timeLimit", MainActivity.this.timeLimit);
                        quizListIntent.putExtra("instantFeedbackEnabled", MainActivity.this.instantFeedbackEnabled);
                        startActivity(quizListIntent);
                        break;
                    case R.id.viewHighScoresButton:
                        Intent highScoresIntent = new Intent(MainActivity.this, HighScoresActivity.class);
                        startActivity(highScoresIntent);
                        break;
                    case R.id.settingsButton:
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        settingsIntent.putExtra("timeLimit", MainActivity.this.timeLimit);
                        settingsIntent.putExtra("instantFeedbackEnabled", MainActivity.this.instantFeedbackEnabled);
                        startActivityForResult(settingsIntent, req_code);
                        break;
                }
            }
        };
        chooseAQuizButton.setOnClickListener(listener);
        viewHighScoresButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);
        //set initial high scores via Preferences API
        setInitialHighScores();
    }

    private void setInitialHighScores() {
        SharedPreferences settings = getSharedPreferences("QuizSettings", 0);
        if (settings.getInt("geographyHighScoreValue", 0) == 0) {
            SharedPreferences.Editor editor = settings.edit();
            //set high score names
            editor.putString("geographyHighScoreName", "Rick Grimes");
            editor.putString("animalHighScoreName", "George Nowak");
            editor.putString("historyHighScoreName", "Homero Penuelas");
            editor.putString("sportsHighScoreName", "Rafael Perez");
            editor.putString("randomTriviaHighScoreName", "Stephen Colbert");
            //set high score values
            editor.putInt("geographyHighScoreValue", 5);
            editor.putInt("animalHighScoreValue", 4);
            editor.putInt("historyHighScoreValue", 6);
            editor.putInt("sportsHighScoreValue", 7);
            editor.putInt("randomTriviaHighScoreValue", 6);
            editor.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == req_code) {
            if (resultCode == RESULT_OK) {
                MainActivity.this.timeLimit = data.getIntExtra("timeLimit", 10);
                System.out.println("NEW TIME LIMIT THO: " + Integer.toString(MainActivity.this.timeLimit));
                System.out.println("INSTANT FEEDBACK ENABLED THO: " + data.getBooleanExtra("instantFeedbackEnabled", false));
                MainActivity.this.instantFeedbackEnabled = data.getBooleanExtra("instantFeedbackEnabled", false);
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
