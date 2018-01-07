package com.example.android.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int age = 7; // This is the initial age. It cannot go lower.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // This lets the user to raise the age
    public void increment (View view) {
        if (age==121){
        // Show an error message as a toast
            Toast.makeText(this, "You cannot enter more than 120 years", Toast.LENGTH_SHORT).show();
        // Exit this method early because there's nothing left to do
        return;}

        age = age + 1;
        displayAge(age);
    }

    private void displayAge (int number){
        TextView ageTextView = findViewById(R.id.age_view);
        ageTextView.setText("" + number);
    }

    // This lets the user to lower the age, but not below 7.
    public void decrement (View view){

        if (age==7) {
            // Show an error message as a toast
            Toast.makeText(this, "You have to be at least 7 years old", Toast.LENGTH_SHORT).show();
            return;
        }

        age = age - 1;
        displayAge(age);

    }

// This method is called when the quiz button is clicked.

    public void submitQuiz (View view){
        RadioButton mountEverest = findViewById(R.id.radioButton1);
        RadioButton mountK2 = findViewById(R.id.radioButton2);
        String mountainAnswer =  "";
        if (mountEverest.isChecked()) {
            mountainAnswer =  mountEverest.getText().toString();
        } else if (mountK2.isChecked()){
            mountainAnswer =  mountK2.getText().toString();
        }

        EditText nameText = findViewById(R.id.your_name_view);
        String name = nameText.getText().toString();

        CheckBox blueCheckBox = findViewById(R.id.checkBox1);
        boolean blue = blueCheckBox.isChecked();

        CheckBox redCheckBox = findViewById(R.id.checkBox2);
        boolean red = redCheckBox.isChecked();

        CheckBox purpleCheckBox = findViewById(R.id.checkBox3);
        boolean purple = purpleCheckBox.isChecked();

        EditText stateOpinion = findViewById(R.id.your_input);
        String opinion = stateOpinion.getText().toString();

        CheckBox catsCheckbox = findViewById(R.id.animal_question);
        boolean cats = catsCheckbox.isChecked();

        CheckBox dogsCheckBox = findViewById(R.id.animal_question1);
        boolean dogs = dogsCheckBox.isChecked();

        CheckBox lizardsCheckBox = findViewById(R.id.animal_question2);
        boolean lizards = lizardsCheckBox.isChecked();

        RadioButton twoTowers = findViewById(R.id.question_5_1);
        boolean towers = twoTowers.isChecked();

        RadioButton returnKing = findViewById(R.id.question_5_2);
        boolean returnOfKing = returnKing.isChecked();

        RadioButton FellowhipRing = findViewById(R.id.question_5_3);
        boolean fellowshipOfRing = FellowhipRing.isChecked();

        String quizAnswers = quizSummary(age, name, mountainAnswer, blue, red, purple, opinion, cats,dogs, lizards,towers,returnOfKing, fellowshipOfRing);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.quiz_summary_email_subject) + name);
        intent.putExtra(Intent.EXTRA_TEXT, quizAnswers);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }
    @SuppressLint("StringFormatInvalid")
    public String quizSummary (int age, String name, String mountainAnswer, boolean colorBlue, boolean colorRed, boolean colorPurple, String yourOpinion, boolean animalCats, boolean animalDogs, boolean animalLizards, boolean twoTowers, boolean returnOfKings, boolean fellowshipRing) {
        return getString(R.string.name) + " : " + name + "\n" + getString(R.string.age) + " : " + age + "\n" +
                getString(R.string.question_1) + "\n" + mountainAnswer + "\n\n" + getString(R.string.question_2) + "\n\n" +
                getString(R.string.blue)+ colorBlue + "\n" + getString(R.string.red) + colorRed +"\n\n" +
                getString(R.string.purple) + colorPurple + "\n"+ getString(R.string.question_3) + "\n\n" +
                getString(R.string.opinion) + yourOpinion + "\n" + getString(R.string.question_4)+
                getString(R.string.cats)+ animalCats +"\n" + getString(R.string.dogs) + animalDogs + "\n" +
                getString(R.string.lizards) + animalLizards + "\n" + getString(R.string.question_5) + "\n" +
                getString(R.string.two_towers) + twoTowers + "\n" + getString(R.string.return_king)+ returnOfKings
                + "\n" + getString(R.string.fellowship) + fellowshipRing;
    }

}
