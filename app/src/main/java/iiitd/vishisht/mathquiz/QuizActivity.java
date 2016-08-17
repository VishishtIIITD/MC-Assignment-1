package iiitd.vishisht.mathquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    //Buttons
    private Button TrueButton;
    private Button FalseButton;
    private Button NextButton;
    private TextView Question;
    private TextView Score;
    Random random = new Random();
    Prime prime = new Prime();
    int number = random.nextInt(1000);
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TrueButton = (Button) findViewById(R.id.true_button);
        FalseButton = (Button) findViewById(R.id.false_button);
        NextButton = (Button) findViewById(R.id.next_button);
        Question = (TextView) findViewById(R.id.question_box);
        Score = (TextView) findViewById(R.id.score_box);

        Question.setText("Is " + Integer.toString(number) + " Prime ?");

        TrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(prime.isPrime(number)){
                    Question.setText("Correct");
                    score++;
                    Log.d(TAG, "Clicked True");
                }
                else{
                    Question.setText("False");
                    score--;
                }
                TrueButton.setEnabled(false);
                FalseButton.setEnabled(false);
                Score.setText("Score:"+ Integer.toString(score));
            }
        });

        FalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (!prime.isPrime(number)){
                    Question.setText("Correct");
                    Log.d(TAG, "Clicked False");
                    score++;
                }
                else{
                    Question.setText("False");
                    score--;
                }
                TrueButton.setEnabled(false);
                FalseButton.setEnabled(false);
                Score.setText("Score:"+ Integer.toString(score));
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = random.nextInt(1000);
                Question.setText("Is " + Integer.toString(number) + " Prime ?");
                TrueButton.setEnabled(true);
                FalseButton.setEnabled(true);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnREsume");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnSTop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }

}
