package com.vmaudgalya.inbornerrorsofmetabolism;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LabActivity extends AppCompatActivity {

  public static final String LAB_FILTERED_DISEASES = "com.vmaudgalya.inbornerrorsofmetabolism.LAB_FILTERED_DISEASES";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lab);
    final Utility util = new Utility();
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
      }
    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    Intent intent = getIntent();
    final ArrayList<String> diseases = intent.getStringArrayListExtra(SymptomsActivity.SYMPTOM_FILTERED_DISEASES);
//    showToastNotification(diseases.toString(), 2, false);

    final String[] labResults = util.getLabResults();
    final ArrayList<String> selectedLabResults = new ArrayList<String>();

    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.content_lab, null);

    // Find the ScrollView
    ScrollView symptomsScrollView = (ScrollView) view.findViewById(R.id.labScrollView);

    // Create a LinearLayout element
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);

    TextView selectLabResultsTextView = new TextView(getApplicationContext());
    selectLabResultsTextView.setText("Select Lab Results");
    layout.addView(selectLabResultsTextView);

    for (final String result : labResults) {
      CheckBox checkBox = new CheckBox(getApplicationContext());
      checkBox.setText(result);
      checkBox.setVisibility(View.VISIBLE);
      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
            selectedLabResults.add(result);
//            showToastNotification("Added " + result + " selected: " + selectedLabResults.toString(), 1, false);
          } else {
            selectedLabResults.remove(result);
//            showToastNotification("Removed " + result + " selected: " + selectedLabResults.toString(), 1, false);
          }
        }

      });

      layout.addView(checkBox);
    }
    Button nextButton = new Button(getApplicationContext());
    nextButton.setText("NEXT");
    nextButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), DiseaseResultsActivity.class);
        ArrayList<String> filteredDiseases = util.filterByLabResults(diseases, selectedLabResults);
        intent.putStringArrayListExtra(LAB_FILTERED_DISEASES, filteredDiseases); // should actually be passing filtered diseases
        startActivity(intent);
      }
    });
    layout.addView(nextButton);
    symptomsScrollView.addView(layout);
    setContentView(view);

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
