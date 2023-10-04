package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int correctAnswers=0;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private int correctAns = R.string.correct_answer;

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }
    private Question[] questions = new Question[]{
            new Question(R.string.q_pb,false),
            new Question(R.string.q_job,false),
            new Question(R.string.q_hit,false),
            new Question(R.string.q_throw,true),
            new Question(R.string.q_idk,true)
    };
    private int currentIndex = 0;

    private void checkAsnwerCorrectness (boolean userAnswer)
    {
        boolean correctAsnwer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        String result = "";
        if(userAnswer == correctAsnwer){
            resultMessageId=R.string.correct_answer;
            correctAnswers+=1;
            result = getString(resultMessageId)+" "+correctAnswers;

        }
        else{
            resultMessageId = R.string.incorrect_answer;
            result = getString(resultMessageId);
        }
        Toast.makeText(this,result , Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        trueButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick (View v){
               checkAsnwerCorrectness(true);
           }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkAsnwerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                currentIndex = (currentIndex+1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }

}
