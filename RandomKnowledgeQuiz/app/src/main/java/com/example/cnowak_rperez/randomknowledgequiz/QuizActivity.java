package com.example.cnowak_rperez.randomknowledgequiz;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.logging.Handler;

public class QuizActivity extends Activity {

    private static final String TAG = "QuestionActivity";
    private static int count = 0;
    private static int nextOrder = 0;
    private static int score = 0;
    private static String answer;
    private Button c1, c2, c3, c4, done, next;
    private TextView questionTxt, timeTxt, progressTxt;
    private int status = 0;
    private ProgressBar progressBar;
    private Handler mHandler;
    private CountDownTimer timer;
    private boolean instantFeedbackEnabled;
    private int timeLimit;

    private Quiz quiz;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_quiz);


        questionTxt = (TextView) findViewById(R.id.question);
        timeTxt = (TextView) findViewById(R.id.timer);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setProgress(count);
        progressTxt = (TextView) findViewById(R.id.processTxt);
        progressTxt.setText(String.valueOf(count * 10) + "%");
        /*
        new Thread(new Runnable() {
            public void run() {
                while(count < 10){
                   progressBar.setProgress(count * 10);
                    progressTxt.setText(String.valueOf(count * 10) + "%");
                }
            }
        }).start();
        */
        c1 = (Button) findViewById(R.id.choice1);
        c2 = (Button) findViewById(R.id.choice2);
        c3 = (Button) findViewById(R.id.choice3);
        c4 = (Button) findViewById(R.id.choice4);

        done = (Button) findViewById(R.id.done);
        next = (Button) findViewById(R.id.next);

        final View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                String btnValue = ((Button) v).getText().toString();
                //Check if answer was correct
                if(instantFeedbackEnabled){
                    feedback(btnValue, v);
                }
                else{
                    noFeedback(btnValue, v);
                }
            }
        };



        c1.setOnClickListener(listener);
        c2.setOnClickListener(listener);
        c3.setOnClickListener(listener);
        c4.setOnClickListener(listener);

        done.setOnClickListener(listener);
        next.setOnClickListener(listener);
        System.out.println(timeLimit);



        Intent intent = getIntent();
        if(intent != null){
            quiz = (Quiz) intent.getSerializableExtra("Quiz");
            question = quiz.getQuestion(count);
            questionTxt.setText(question.getQuestion());
            String[] array = question.getPossible_answers();
            answer = question.getAnswer();
            instantFeedbackEnabled = intent.getBooleanExtra("instantFeedbackEnabled", false);
            timeLimit = intent.getIntExtra("timeLimit", 10)*1000 + 1000;
            c1.setText(array[0]);
            c2.setText(array[1]);
            c3.setText(array[2]);
            c4.setText(array[3]);


        }
        timer = new CountDownTimer(this.timeLimit, 1000) {

            public void onTick(long millisUntilFinished) {
                timeTxt.setText( millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                if(instantFeedbackEnabled){
                    Button[] buttons = {c1, c2, c3, c4};
                    int counter = 0;
                    while(counter < buttons.length){
                        Button currentButton = buttons[counter];
                        if(currentButton.getText().equals(answer)){
                            //currentButton.setBackgroundColor(Color.parseColor("#01DF01"));
                            currentButton.getBackground().setColorFilter(Color.parseColor("#01DF01"), PorterDuff.Mode.DARKEN);
                        }
                        counter++;
                    }
                    Toast.makeText(QuizActivity.this, "TIMED OUT!", Toast.LENGTH_SHORT).show();
                    c1.setClickable(false);
                    c2.setClickable(false);
                    c3.setClickable(false);
                    c4.setClickable(false);
                    timeTxt.setText("0s");
                    if(count == 9){
                        next.setVisibility(View.INVISIBLE);
                        done.setVisibility(View.VISIBLE);
                    }
                    else{
                        next.setVisibility(View.VISIBLE);
                        done.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    if(count == 9){
                        //INTENT FOR SCOREBOARD
                        timeTxt.setText("0s");
                    }
                    else{
                        timeTxt.setText("0s");
                        count++;
                        //Set up next question
                        System.out.println(count);
                        question = quiz.getQuestion(count);
                        questionTxt.setText(question.getQuestion());
                        String[] array = question.getPossible_answers();
                        answer = question.getAnswer();
                        c1.setText(array[0]);
                        c2.setText(array[1]);
                        c3.setText(array[2]);
                        c4.setText(array[3]);
                        timer.start();
                    }

                }



            }


        }.start();

        Log.d(TAG, "OnCreate");
    }

    private class ProgressTask extends AsyncTask<Void, Integer, Void>{
        private void doWork(){
            try{
                while(count < 10) {
                    Thread.currentThread().sleep(100);
                }
            }catch(InterruptedException e){
            }
        }
        @Override protected Void doInBackground(Void... params){
            status = 0;
            while(status <= 100){
                doWork();
                publishProgress(status);
            }
            return null;
        }
        @Override protected void onProgressUpdate(Integer...progress){
            progressBar.setProgress(progress[0]);
            progressTxt.setText(String.valueOf(status) + "%");
        }
        @Override protected void onPreExecute(){

        }
    }
/*
    @Override
    protected void onStart(){
        Log.d(TAG, "onStart");

        super.onStart();

    }
  */
    @Override
    public void onBackPressed(){
        count = 0;
        nextOrder = 0;
        score = 0;
        answer = null;
        c1 = null;
        c2 = null;
        c3 = null;
        c4 = null;
        done = null;
        next = null;
        questionTxt = null;
        timeTxt = null;
        progressTxt = null;
        status = 0;
        progressBar = null;
        mHandler = null;
        timer.cancel();
        timer = null;
        //instantFeedbackEnabled = true;
        //timeLimit = 10;
        quiz = null;
        question = null;


        super.onBackPressed();
    }
    public void noFeedback(String btnValue, View v){
        if(btnValue.equals(answer)){
            timer.cancel();
            score++;
            if(count == 9){
                //ADD INTENT TO THE SCOREBOARD ACTIVITY
            }
            else{
                timer.start();
                count++;
                //Set up next question
                question = quiz.getQuestion(count);
                questionTxt.setText(question.getQuestion());
                String[] array = question.getPossible_answers();
                answer = question.getAnswer();
                c1.setText(array[0]);
                c2.setText(array[1]);
                c3.setText(array[2]);
                c4.setText(array[3]);
            }
        }
        else{
            timer.cancel();
            if(count == 9){
                //ADD INTENT TO THE SCOREBOARD ACTIVITY
            }
            else{
                timer.start();
                count++;
                //Set up next question
                question = quiz.getQuestion(count);
                questionTxt.setText(question.getQuestion());
                String[] array = question.getPossible_answers();
                answer = question.getAnswer();
                c1.setText(array[0]);
                c2.setText(array[1]);
                c3.setText(array[2]);
                c4.setText(array[3]);
            }
        }
    }


    public void feedback(String btnValue, View v){
        Button[] buttons = {c1, c2, c3, c4};
        int counter = 0;
        if(btnValue.equals(answer)){

            timer.cancel();

            score++;
            //v.setBackgroundColor(Color.parseColor("#01DF01"));
            v.getBackground().setColorFilter(Color.parseColor("#01DF01"), PorterDuff.Mode.DARKEN);

            if(count == 9){
                //CREATE INTENT that leads to next activity after last question
                Toast.makeText(QuizActivity.this, String.valueOf(score), Toast.LENGTH_SHORT).show();
                next.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);

            }
            else{
                Toast.makeText(QuizActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                next.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);

            }
            c1.setClickable(false);
            c2.setClickable(false);
            c3.setClickable(false);
            c4.setClickable(false);
        }
        else if(btnValue.equals("Next")){
            timer.start();
            count++;
            //Set up next question
            question = quiz.getQuestion(count);
            questionTxt.setText(question.getQuestion());
            String[] array = question.getPossible_answers();
            answer = question.getAnswer();
            next.setVisibility(View.INVISIBLE);
            done.setVisibility(View.INVISIBLE);
            c1.setClickable(true);
            c2.setClickable(true);
            c3.setClickable(true);
            c4.setClickable(true);
            c1.setText(array[0]);
            c2.setText(array[1]);
            c3.setText(array[2]);
            c4.setText(array[3]);
            c1.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.DARKEN);
            c2.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.DARKEN);
            c3.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.DARKEN);
            c4.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.DARKEN);
        }
        else if(btnValue.equals("Done")){
            //CREATE INTENT that leads to player score
        }
        else{
            //v.setBackgroundColor(Color.parseColor("#DF0101"));

            timer.cancel();


            v.getBackground().setColorFilter(Color.parseColor("#DF0101"), PorterDuff.Mode.DARKEN);
            while(counter < buttons.length){
                Button currentButton = buttons[counter];
                if(currentButton.getText().equals(answer)){
                    //currentButton.setBackgroundColor(Color.parseColor("#01DF01"));
                    currentButton.getBackground().setColorFilter(Color.parseColor("#01DF01"), PorterDuff.Mode.DARKEN);
                }
                counter++;
            }
            if(count == 9){
                //Set up "Done" buttons
                Toast.makeText(QuizActivity.this, String.valueOf(score), Toast.LENGTH_SHORT).show();
                next.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
            }
            else{
                //Set up "Next" button
                Toast.makeText(QuizActivity.this, "WRONG", Toast.LENGTH_SHORT).show();
                next.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);
            }
            c1.setClickable(false);
            c2.setClickable(false);
            c3.setClickable(false);
            c4.setClickable(false);
        }
    }
}
