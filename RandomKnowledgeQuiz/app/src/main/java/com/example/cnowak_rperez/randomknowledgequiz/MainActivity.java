package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean instantFeedbackEnabled = false;
    private int timeLimit = 10;
    private static final int req_code = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chooseAQuizButton = (Button)findViewById(R.id.chooseAQuizButton);
        Button viewHighScoresButton = (Button)findViewById(R.id.viewHighScoresButton);
        Button settingsButton = (Button)findViewById(R.id.settingsButton);

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
                        if (MainActivity.this.instantFeedbackEnabled) {
                            System.out.println("INSTANT FEEDBACK IS ON THO");
                        }
                        else {
                            System.out.println("INSTANT FEEDBACK IN OFF THO");
                        }
                        break;
                    case R.id.settingsButton:
                        System.out.println("SETTINGS BUTTON TAPPED");
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivityForResult(settingsIntent, req_code);
                        break;
                }
            }
        };
        chooseAQuizButton.setOnClickListener(listener);
        viewHighScoresButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == req_code) {
            if (resultCode == RESULT_OK) {
                MainActivity.this.timeLimit = data.getIntExtra("newTimeLimit", 10);
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
