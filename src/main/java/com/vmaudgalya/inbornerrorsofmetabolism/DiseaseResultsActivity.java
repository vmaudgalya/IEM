package com.vmaudgalya.inbornerrorsofmetabolism;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiseaseResultsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disease_results);
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//
//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//      }
//    });
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent intent = getIntent();
    final ArrayList<String> diseases = intent.getStringArrayListExtra(LabActivity.LAB_FILTERED_DISEASES);
    TextView textView = (TextView) findViewById(R.id.diseaseResultsTextView);
    StringBuilder message = new StringBuilder("Potential Inborn Errors of Metabolism are:\n\n\n");
    for (int i = 7; i < diseases.size(); i++) {
      message.append(diseases.get(i) + (i == diseases.size()-1 ? "" : ", "));
    }
    textView.setText(message.toString());
//    showToastNotification(diseases.toString(), 2, false);
  }

  public void showToastNotification(String messageToDisplay, int seconds, boolean displayAtTop) {
    final Toast tag = Toast.makeText(getBaseContext(), messageToDisplay, Toast.LENGTH_SHORT);
    if (displayAtTop) {
      tag.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    tag.show();
    int time = 2;
    if (seconds == 1) {
      time = 1000;
    } else {
      time = (seconds - 1) * 1000;
    }
    new CountDownTimer(time, 1000) {
      public void onTick(long millisUntilFinished) {
        tag.show();
      }

      public void onFinish() {
        tag.show();
      }
    }.start();
  }

}
