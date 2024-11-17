package com.example.megasuit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Ninteen extends AppCompatActivity {

    private TextView questionTextsm, scoreTextsm;
    private RadioGroup optionsGroupsm;
    private RadioButton option1sm, option2sm, option3sm, option4sm;
    private Button nextButtonsm;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninteen);

        // Initialize views
        questionTextsm = findViewById(R.id.questionTextsm);
        scoreTextsm = findViewById(R.id.scoreTextsm);
        optionsGroupsm = findViewById(R.id.optionsGroupsm);
        option1sm = findViewById(R.id.option1sm);
        option2sm = findViewById(R.id.option2sm);
        option3sm = findViewById(R.id.option3sm);
        option4sm = findViewById(R.id.option4sm);
        nextButtonsm = findViewById(R.id.nextButtonsm);

        // Initialize questions
        questionList = getQuestions();
        displayQuestion();

        // Handle Next button click
        nextButtonsm.setOnClickListener(view -> {
            int selectedOptionId = optionsGroupsm.getCheckedRadioButtonId();
            if (selectedOptionId == -1) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

                // Vibrate based on Android version
                if (vibrator != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(500); // For older devices
                    }
                }
                Toast.makeText(Ninteen.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedOption = findViewById(selectedOptionId);
            String selectedAnswer = selectedOption.getText().toString();
            checkAnswer(selectedAnswer);

            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                displayQuestion();
            } else {
                showFinalScore();
            }
        });
    }

    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What does CPU stand for?", "Central Processing Unit", "Central Programming Unit", "Control Processing Unit", "Computer Processing Unit", "Central Processing Unit"));
        questions.add(new Question("Which programming language is known as 'write once, run anywhere'?", "C++", "Python", "Java", "JavaScript", "Java"));
        questions.add(new Question("Which data structure uses LIFO (Last In, First Out)?", "Queue", "Stack", "Array", "Linked List", "Stack"));
        questions.add(new Question("Which company developed the Android operating system?", "Microsoft", "Apple", "Google", "IBM", "Google")); // New question
        questions.add(new Question("What is the primary protocol used for sending emails?", "HTTP", "SMTP", "FTP", "IMAP", "SMTP")); // New question
        return questions;
    }

    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        questionTextsm.setText(currentQuestion.getQuestionText());
        option1sm.setText(currentQuestion.getOption1());
        option2sm.setText(currentQuestion.getOption2());
        option3sm.setText(currentQuestion.getOption3());
        option4sm.setText(currentQuestion.getOption4());
        optionsGroupsm.clearCheck();
        updateScore();
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        if (currentQuestion.getCorrectAnswer().equals(selectedAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            // Vibrate based on Android version
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(500); // For older devices
                }
            }
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showFinalScore() {
        questionTextsm.setText("Quiz Over! Your final score is " + score + "/" + questionList.size());
        optionsGroupsm.setVisibility(View.GONE);
        nextButtonsm.setText("Finish");

        // Update the score display at the end
        updateScore(); // This will update the scoreTextsm with the final score

        nextButtonsm.setOnClickListener(view -> {
            // Navigate to Twelve.java
            Intent intent = new Intent(Ninteen.this, Eleven.class);
            intent.putExtra("finalScore", score); // Pass the score to Twelve.java
            startActivity(intent);
            finish(); // Optional: Close the current activity
        });
    }


    private void updateScore() {
        scoreTextsm.setText("Score: " + score + "/" + questionList.size());
    }

    private static class Question {
        private final String questionText, option1, option2, option3, option4, correctAnswer;

        public Question(String questionText, String option1, String option2, String option3, String option4, String correctAnswer) {
            this.questionText = questionText;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }

        public String getOption4() {
            return option4;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
