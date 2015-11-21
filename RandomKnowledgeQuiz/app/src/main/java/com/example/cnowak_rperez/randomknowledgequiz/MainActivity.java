package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
                        Intent intent = new Intent(MainActivity.this, QuizListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.viewHighScoresButton:
                        System.out.println("VIEW HIGH SCORES BUTTON TAPPED");
                        break;
                    case R.id.settingsButton:
                        System.out.println("SETTINGS ");
                        break;
                }


            }
        };
        chooseAQuizButton.setOnClickListener(listener);



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
