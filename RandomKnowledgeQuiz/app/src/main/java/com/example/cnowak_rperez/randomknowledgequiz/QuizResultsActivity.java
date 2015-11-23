package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuizResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        final TextView scoreValueLabel = (TextView)findViewById(R.id.scoreValueLabel);
        final Button backToQuizListButton = (Button)findViewById(R.id.backToQuizListButton);
        final Button homeButton = (Button)findViewById(R.id.homeButton);
        Intent intent = getIntent();
        scoreValueLabel.setText(Integer.toString(intent.getIntExtra("Score", 10)) + "/10");
        final View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                Button tappedButton = (Button)v;
                switch (tappedButton.getId()) {
                    case R.id.backToQuizListButton:
                        Intent backToQuizListIntent = new Intent(QuizResultsActivity.this, QuizListActivity.class);
                        backToQuizListIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(backToQuizListIntent);
                        break;
                    case R.id.homeButton:
                        Intent backToHomeActivityIntent = new Intent(QuizResultsActivity.this, MainActivity.class);
                        backToHomeActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(backToHomeActivityIntent);
                        break;
                }
            }
        };
        backToQuizListButton.setOnClickListener(listener);
        homeButton.setOnClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SpinningTextView spinningTextView = (SpinningTextView) findViewById(R.id.spinningTextView);
        spinningTextView.startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SpinningTextView spinningTextView = (SpinningTextView) findViewById(R.id.spinningTextView);
        spinningTextView.stopAnimation();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_results, menu);
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
