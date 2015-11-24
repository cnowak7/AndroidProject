package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

public class QuizResultsActivity extends AppCompatActivity {

    // share button
    private ShareButton shareButton;
    private SendButton sendButton;
    private int quizScore;
    private String quizCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        final TextView scoreValueLabel = (TextView)findViewById(R.id.scoreValueLabel);
        final Button backToQuizListButton = (Button)findViewById(R.id.backToQuizListButton);
        final Button homeButton = (Button)findViewById(R.id.homeButton);
        final Button screenshotButton = (Button)findViewById(R.id.screenshotButton);
        Intent intent = getIntent();
        this.quizScore = intent.getIntExtra("Score", 10);
        scoreValueLabel.setText(Integer.toString(this.quizScore) + "/10");
        this.quizCategory = intent.getStringExtra("quizType");
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
                        backToHomeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(backToHomeActivityIntent);
                        break;
                    case R.id.screenshotButton:
                        takeScreenshotAndPrepareShareButton();
                        break;
                }
            }
        };
        backToQuizListButton.setOnClickListener(listener);
        homeButton.setOnClickListener(listener);
        screenshotButton.setOnClickListener(listener);
        //share and send buttons
        shareButton = (ShareButton) findViewById(R.id.share_btn);
        sendButton = (SendButton)findViewById(R.id.send_btn);
        //check to see if high score was achieved for the quiz category
        checkForHighScore();
    }

    private void checkForHighScore() {
        SharedPreferences settings = getSharedPreferences("QuizSettings", 0);
        int currentHighScore;
        switch (this.quizCategory) {
            case "Geography":
                //get high score for this category from settings
                currentHighScore = settings.getInt("geographyHighScoreValue", 0);
                //if the user's score is higher than the current high score, replace current high score name and value
                if (this.quizScore > currentHighScore) {
                    Toast.makeText(QuizResultsActivity.this, "You set the new high score for this quiz!", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("geographyHighScoreName", "You");
                    editor.putInt("geographyHighScoreValue", this.quizScore);
                    editor.commit();
                }
                break;
            case "Animals":
                //get high score for this category from settings
                currentHighScore = settings.getInt("animalHighScoreValue", 0);
                //if the user's score is higher than the current high score, replace current high score name and value
                if (this.quizScore > currentHighScore) {
                    Toast.makeText(QuizResultsActivity.this, "You set the new high score for this quiz!", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("animalHighScoreName", "You");
                    editor.putInt("animalHighScoreValue", this.quizScore);
                    editor.commit();
                }
                break;
            case "History":
                //get high score for this category from settings
                currentHighScore = settings.getInt("historyHighScoreValue", 0);
                //if the user's score is higher than the current high score, replace current high score name and value
                if (this.quizScore > currentHighScore) {
                    Toast.makeText(QuizResultsActivity.this, "You set the new high score for this quiz!", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("historyHighScoreName", "You");
                    editor.putInt("historyHighScoreValue", this.quizScore);
                    editor.commit();
                }
                break;
            case "Sports":
                //get high score for this category from settings
                currentHighScore = settings.getInt("sportsHighScoreValue", 0);
                //if the user's score is higher than the current high score, replace current high score name and value
                if (this.quizScore > currentHighScore) {
                    Toast.makeText(QuizResultsActivity.this, "You set the new high score for this quiz!", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("sportsHighScoreName", "You");
                    editor.putInt("sportsHighScoreValue", this.quizScore);
                    editor.commit();
                }
                break;
            case "Misc":
                //get high score for this category from settings
                currentHighScore = settings.getInt("randomTriviaHighScoreValue", 0);
                //if the user's score is higher than the current high score, replace current high score name and value
                if (this.quizScore > currentHighScore) {
                    Toast.makeText(QuizResultsActivity.this, "You set the new high score for this quiz!", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("randomTriviaHighScoreName", "You");
                    editor.putInt("randomTriviaHighScoreValue", this.quizScore);
                    editor.commit();
                }
                break;
        }
    }

    private void takeScreenshotAndPrepareShareButton() {
        //save the screenshot just in case the user wants to share it to facebook
        View screenView = getWindow().getDecorView().getRootView();
        screenView.setDrawingCacheEnabled(true);
        // creates immutable clone of image
        Bitmap image = Bitmap.createBitmap(screenView.getDrawingCache());
        System.out.println("GOT THIS FAR BRO");
        screenView.setDrawingCacheEnabled(false);
        // destroy
        screenView.destroyDrawingCache();
        //set share content of Facebook share button
        SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
        SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
        shareButton.setShareContent(content);
        sendButton.setShareContent(content);
        Toast.makeText(QuizResultsActivity.this, "Screen shot taken and sharing enabled", Toast.LENGTH_SHORT).show();
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
