package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private boolean instantFeedbackEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //initalize UI elements
        final SeekBar timeLimitSeekBar = (SeekBar)findViewById(R.id.timeLimitSeekBar);
        final TextView timeLimitValueLabel = (TextView)findViewById(R.id.timeLimitValue);
        final Switch instantFeedbackSwitch = (Switch)findViewById(R.id.instantFeedbackSwitch);
        final Button cancelButton = (Button)findViewById(R.id.cancelButton);
        final Button saveButton = (Button)findViewById(R.id.saveButton);

        //get intent from main activity and set the settings as they should be, rather default or saved
        Intent intent = getIntent();
        //since the default time limit is 20 seconds but the minimum is 10, subtract 10 because the max of the seek bar is 20
        timeLimitSeekBar.setProgress(intent.getIntExtra("timeLimit", 10) - 10);
        timeLimitValueLabel.setText(Integer.toString(intent.getIntExtra("timeLimit", 10)));
        this.instantFeedbackEnabled = intent.getBooleanExtra("instantFeedbackEnabled", false);
        instantFeedbackSwitch.setChecked(intent.getBooleanExtra("instantFeedbackEnabled", false));


        //for changing time limit
        timeLimitSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        //since 10 seconds is the minimum time limit, add 10
                        timeLimitValueLabel.setText(Integer.toString(i+10));
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

        final View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.instantFeedbackSwitch:
                        if (SettingsActivity.this.instantFeedbackEnabled) {
                            SettingsActivity.this.instantFeedbackEnabled = false;
                        }
                        else {
                            SettingsActivity.this.instantFeedbackEnabled = true;
                        }
                        break;
                    case R.id.cancelButton:
                        setResult(RESULT_CANCELED);
                        finish();
                        break;
                    case R.id.saveButton:
                        Intent data = new Intent();
                        //pass new time limit and instant feedback settings to main activity
                        data.putExtra("timeLimit", Integer.parseInt(timeLimitValueLabel.getText().toString()));
                        data.putExtra("instantFeedbackEnabled", SettingsActivity.this.instantFeedbackEnabled);
                        setResult(RESULT_OK, data);
                        finish();
                        break;
                }
            }
        };

        instantFeedbackSwitch.setOnClickListener(listener);
        cancelButton.setOnClickListener(listener);
        saveButton.setOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
