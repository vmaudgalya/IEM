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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SymptomsActivity extends AppCompatActivity {

  public static final String SYMPTOM_FILTERED_DISEASES = "com.vmaudgalya.inbornerrorsofmetabolism.SYMPTOM_FILTERED_DISEASES";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_symptoms);
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
    String[] symptoms = util.getSymptoms();
    final ArrayList<String> selectedSymptoms = new ArrayList<String>();

    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.content_symptoms, null);

    // Find the ScrollView
    ScrollView symptomsScrollView = (ScrollView) view.findViewById(R.id.symptomsScrollView);

    // Create a LinearLayout element
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);

    TextView selectSymptomsTextView = new TextView(getApplicationContext());
    selectSymptomsTextView.setText("Select Patient Symptoms");
    layout.addView(selectSymptomsTextView);

    for (final String symptom : symptoms) {
      CheckBox checkBox = new CheckBox(getApplicationContext());
      checkBox.setText(symptom);
      checkBox.setVisibility(View.VISIBLE);
      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
            selectedSymptoms.add(symptom);
//            showToastNotification("Added " + symptom + " selected: " + selectedSymptoms.toString(), 1, false);
          } else {
            selectedSymptoms.remove(symptom);
//            showToastNotification("Removed " + symptom + " selected: " + selectedSymptoms.toString(), 1, false);
          }
        }

      });

      layout.addView(checkBox);
    }
    Button nextButton = new Button(getApplicationContext());
    nextButton.setText("NEXT");
    nextButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), LabActivity.class);
        ArrayList<String> diseases = new ArrayList<String>(Arrays.asList(util.getAllDiseases()));
        diseases = util.filterBySymptoms(diseases, selectedSymptoms);
        intent.putStringArrayListExtra(SYMPTOM_FILTERED_DISEASES, diseases); // should actually be passing filtered diseases
        startActivity(intent);
      }
    });
    layout.addView(nextButton);
    symptomsScrollView.addView(layout);
    setContentView(view);
//        for(int i = 0; i < receiptItemsList.size(); i++){
//            String[] itemNameAndCost = receiptItemsList.get(i).split("_%_");
//            itemName = itemNameAndCost[0];
//            itemCost = itemNameAndCost[1];
//
//            TableRow newItemRow = new TableRow(getApplicationContext());
//            Random g = new Random();
//            newItemRow.setId(g.nextInt(20));
//
//            final EditText itemNameEditText = new EditText(getApplicationContext());
//            itemNameEditText.setText(itemName);
//            itemNameEditText.setVisibility(View.VISIBLE);
//            itemNameEditText.setTextColor(Color.BLACK);

//            final EditText itemPriceEditText = new EditText(getApplicationContext());
//            itemPriceEditText.setText("$"+itemCost);
//            itemPriceEditText.setVisibility(View.VISIBLE);
//            itemPriceEditText.setTextColor(Color.BLACK);
//            itemPriceEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
//
//            CheckBox checkBox = new CheckBox(getApplicationContext());
//            checkBox.setVisibility(View.VISIBLE);
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    String itemPriceNum = itemPriceEditText.getText().toString().replaceAll("\\$", "");
//                    if(isChecked == true){ //add to total if they selected it, otherwise subtract
//                        userCost = userCost.add(new BigDecimal(itemPriceNum));
//                        items.put(itemNameEditText.getText().toString(), new BigDecimal(itemPriceNum));
//                    } else {
//                        userCost = userCost.subtract(new BigDecimal(itemPriceNum));
//                        items.remove(itemNameEditText.getText().toString());
//                        //This will not work if they change the name of the item and then uncheck it.
//                    }
//                    userCostTextView.setText(currencyFormat(userCost));
//                }
//            });
//
//
//            newItemRow.addView(checkBox);
//            newItemRow.addView(itemNameEditText);
//            newItemRow.addView(itemPriceEditText);
//
//            newItemRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//            itemTable.addView(newItemRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
//        }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_symptoms, menu);
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
