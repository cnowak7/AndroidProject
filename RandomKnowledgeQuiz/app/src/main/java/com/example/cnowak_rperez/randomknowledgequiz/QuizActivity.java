package com.example.cnowak_rperez.randomknowledgequiz;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuizActivity extends Activity {

    private static final String TAG = "QuestionActivity";
    private static int count = 0;
    private static int score = 0;
    private static String answer;
    private Button c1, c2, c3, c4;
    private TextView questionTxt, timeTxt;
    private ProgressBar progressBar;
    private Quiz quiz;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTxt = (TextView) findViewById(R.id.question);
        timeTxt = (TextView) findViewById(R.id.timer);

        c1 = (Button) findViewById(R.id.choice1);
        c2 = (Button) findViewById(R.id.choice2);
        c3 = (Button) findViewById(R.id.choice3);
        c4 = (Button) findViewById(R.id.choice4);

        final View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                String btnValue = ((Button) v).getText().toString();
                if(btnValue.equals("")){

                }



            }
        };
        c1.setOnClickListener(listener);
        c2.setOnClickListener(listener);
        c3.setOnClickListener(listener);
        c4.setOnClickListener(listener);

        Log.d(TAG, "OnCreate");
    }

    @Override
    protected void onStart(){
        Log.d(TAG, "onStart");

        super.onStart();
        Intent intent = getIntent();
        if(intent != null){
            quiz = (Quiz) intent.getSerializableExtra("Quiz");
            question = quiz.getQuestion(count);
            questionTxt.setText(question.getQuestion());
            String[] array = question.getPossible_answers();
            c1.setText(array[0]);
            c2.setText(array[1]);
            c3.setText(array[2]);
            c4.setText(array[3]);

            //TextView name = (TextView) findViewById(R.id.q);
            //TextView description = (TextView) findViewById(R.id.questionDescription);
            /*ImageView icon = (ImageView) findViewById(R.id.imageDetail);
            ImageView image = (ImageView) findViewById(R.id.consoleImage);
            TextView year = (TextView) findViewById(R.id.nameYear);
            TextView gen = (TextView) findViewById(R.id.genMain);
            TextView link = (TextView) findViewById(R.id.link);*/
            //name.setText(intent.getCharSequenceExtra("Name"));
            //description.setText(
                //    intent.getCharSequenceExtra("Description"));
            /*icon.setImageResource(intent.getIntExtra("Icon", -1));
            image.setImageResource(intent.getIntExtra("Image", -1));
            year.setText(intent.getCharSequenceExtra("Year"));
            //gen.setText(intent.getCharSequenceExtra("Generation"));
            link.setText(intent.getIntExtra("Demo", -1));

            link.setMovementMethod(LinkMovementMethod.getInstance());*/

        }
    }
}
