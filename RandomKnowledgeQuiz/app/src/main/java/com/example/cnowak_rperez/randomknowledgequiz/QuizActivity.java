package com.example.cnowak_rperez.randomknowledgequiz;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class QuizActivity extends Activity {

    private static final String TAG = "QuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "OnCreate");
    }

    @Override
    protected void onStart(){
        Log.d(TAG, "onStart");

        super.onStart();
        Intent intent = getIntent();
        if(intent != null){
            TextView name = (TextView) findViewById(R.id.questionName);
            TextView description = (TextView) findViewById(R.id.questionDescription);
            /*ImageView icon = (ImageView) findViewById(R.id.imageDetail);
            ImageView image = (ImageView) findViewById(R.id.consoleImage);
            TextView year = (TextView) findViewById(R.id.nameYear);
            TextView gen = (TextView) findViewById(R.id.genMain);
            TextView link = (TextView) findViewById(R.id.link);*/
            name.setText(intent.getCharSequenceExtra("Name"));
            description.setText(
                    intent.getCharSequenceExtra("Description"));
            /*icon.setImageResource(intent.getIntExtra("Icon", -1));
            image.setImageResource(intent.getIntExtra("Image", -1));
            year.setText(intent.getCharSequenceExtra("Year"));
            //gen.setText(intent.getCharSequenceExtra("Generation"));
            link.setText(intent.getIntExtra("Demo", -1));

            link.setMovementMethod(LinkMovementMethod.getInstance());*/

        }
    }
}
