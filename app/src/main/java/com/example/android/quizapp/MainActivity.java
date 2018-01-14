package com.example.android.quizapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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
    public void increment(View view) {
        if (age == 121) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot enter more than 120 years", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        age = age + 1;
        displayAge(age);
    }

    private void displayAge(int number) {
        TextView ageTextView = findViewById(R.id.age_view);
        ageTextView.setText("" + number);
    }

    // This lets the user to lower the age, but not below 7.
    public void decrement(View view) {

        if (age == 7) {
            // Show an error message as a toast
            Toast.makeText(this, "You have to be at least 7 years old", Toast.LENGTH_SHORT).show();
            return;
        }

        age = age - 1;
        displayAge(age);

    }

// This method is called when the quiz button is clicked.

    public void submitQuiz(View view) {

        CharSequence resultsDisplay;

        int answer1_score;
        int answer2_score;
        int answer3_score;
        int answer4_score;
        int answer5_score;
        int final_score;

        //------------------------------------------------------------------------------------------
        // Question 1 - Correct Answer is Everest
        //------------------------------------------------------------------------------------------
        EditText nameText = findViewById(R.id.your_name_view);
        String name = nameText.getText().toString();

        boolean answer1;
        boolean answer2;
        RadioButton mountEverest = findViewById(R.id.radioButton1);
        RadioButton mountK2 = findViewById(R.id.radioButton2);
        String mountainSolution = "";
        if (mountEverest.isChecked()) {
            mountainSolution = mountEverest.getText().toString();
        }
        answer1 = mountEverest.isChecked();
        answer2 = mountK2.isChecked();
        if (answer1) {
            answer1_score = 1;
        } else {
            answer1_score = 0;
        }
        if (answer2) {
            System.out.println("Your answer is incorrect");
        }
        //------------------------------------------------------------------------------------------
        // Question 2 - Correct Answer is #1 and #3
        //------------------------------------------------------------------------------------------
        boolean answer2_choice1;
        boolean answer2_choice2;
        boolean answer2_choice3;
//////////////////////////////////////////////////////////////////////
        CheckBox saiga = findViewById(R.id.checkBox1);
        String saigaSolution = "";
        if (saiga.isChecked()) {
            saigaSolution = saiga.getText().toString();
        }
        answer2_choice1 = saiga.isChecked();
//////////////////////////////////////////////////////////////////////
        CheckBox koala = findViewById(R.id.checkBox2);
        answer2_choice2 = koala.isChecked();
//////////////////////////////////////////////////////////////////////
        CheckBox wildBoar = findViewById(R.id.checkBox3);
        String wildBoarSolution = "";
        if (wildBoar.isChecked()) {
            wildBoarSolution = wildBoar.getText().toString();
        }

        answer2_choice3 = wildBoar.isChecked();

        if (answer2_choice1 && answer2_choice3) {
            answer2_score = 1;
        } else {
            answer2_score = 0;
        }
        if (answer2_choice2) {
            System.out.println("Your answer is incorrect");
        }

//------------------------------------------------------------------------------------------
// Question 3  - Correct Answers "solid", "liquid" and "gas"
//------------------------------------------------------------------------------------------

        String answer3;

        EditText correctAnswer = findViewById(R.id.your_input);
        answer3 = correctAnswer.getText().toString().toLowerCase();
        if ((answer3.contains("liquid") && answer3.contains("gas") && answer3.contains("solid"))) {
            answer3_score = 1;
        } else {
            answer3_score = 0;
        }

        //------------------------------------------------------------------------------------------
        // Question 4  - Correct Answers #1 and # 3
        //------------------------------------------------------------------------------------------

        boolean answer4_choice1;
        boolean answer4_choice2;
        boolean answer4_choice3;

        CheckBox lions = findViewById(R.id.animal_question);
        String lionSolution = "";
        if (lions.isChecked()) {
            lionSolution = lions.getText().toString();
        }
        answer4_choice1 = lions.isChecked();
//////////////////////////////////////////////////////////////////////
        CheckBox cows = findViewById(R.id.animal_question1);
        answer4_choice2 = cows.isChecked();
//////////////////////////////////////////////////////////////////////
        CheckBox piranhas = findViewById(R.id.animal_question2);
        String piranhasSolution = "";
        if (piranhas.isChecked()) {
            piranhasSolution = piranhas.getText().toString();
        }
        answer4_choice3 = piranhas.isChecked();

        if (answer4_choice1 && !answer4_choice2 && answer4_choice3) {
            answer4_score = 1;
        } else {
            answer4_score = 0;
        }


        //------------------------------------------------------------------------------------------
        // Question 5  - Correct Answers 3
        //------------------------------------------------------------------------------------------
        boolean answer5;
        RadioButton fellowshipOfRings = findViewById(R.id.question_5_3);
        String fellowshipSolution = "";
        if (fellowshipOfRings.isChecked()) {
            fellowshipSolution = fellowshipOfRings.getText().toString();
        }
        answer5 = fellowshipOfRings.isChecked();
        if (answer5) {
            answer5_score = 1;
        } else {
            answer5_score = 0;

        }
//------------------------------------------------------------------------------------------
        // Final Score
//------------------------------------------------------------------------------------------

        final_score = answer1_score + answer2_score + answer3_score + answer4_score + answer5_score;
        if (final_score == 5) {
            resultsDisplay = "Perfect! You scored 5 out of 5";
        } else {
            resultsDisplay = "Try again. You scored " + final_score + " out of 5";
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, resultsDisplay, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


        String quizAnswers;
        quizAnswers = quizSummary(age, name, mountainSolution, saigaSolution, wildBoarSolution, answer3, lionSolution, piranhasSolution, fellowshipSolution, final_score);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,

                getString(R.string.quiz_summary_email_subject) + name);
        intent.putExtra(Intent.EXTRA_TEXT, quizAnswers);
        if (intent.resolveActivity(

                getPackageManager()) != null)

        {
            startActivity(intent);
        }
    }


    public String quizSummary(int age, String name, String mountainSolution, String saigaSolution, String wildBoarSolution, String answer3, String lionSolution, String piranhasSolution, String fellowshipSolution, int final_score) {
        return getString(R.string.name) + " : " + name + "\n\n" + getString(R.string.age) + " : " + age + "\n\n" +
                getString(R.string.question_1) + "\n" + mountainSolution + "\n\n" + getString(R.string.question_2) + "\n\n" +
                saigaSolution + "\n\n" + wildBoarSolution + "\n\n" + getString(R.string.question_3) + answer3 + "\n\n" +
                getString(R.string.question_4) + lionSolution + "\n\n" + "\n\n" + piranhasSolution + "\n\n" + getString(R.string.question_5) + "\n\n" +
                fellowshipSolution + "\n\n" + getString(R.string.final_score1) + " "+ final_score;
    }
}



