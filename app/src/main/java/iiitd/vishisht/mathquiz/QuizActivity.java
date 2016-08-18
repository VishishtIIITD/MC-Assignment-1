package iiitd.vishisht.mathquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    //Buttons
    private Button TrueButton;
    private Button FalseButton;
    private Button NextButton;
    //TextView
    private TextView Question;
    private TextView Score;

    private final Random random = new Random();
    private final Prime prime = new Prime();
    private int number = random.nextInt(1000);
    private int score = 0;
    private boolean button_state = true;
    private String temporaryString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        TrueButton = (Button) findViewById(R.id.true_button);
        FalseButton = (Button) findViewById(R.id.false_button);
        NextButton = (Button) findViewById(R.id.next_button);
        Question = (TextView) findViewById(R.id.question_box);
        Score = (TextView) findViewById(R.id.score_box);

        if(savedInstanceState!= null){
            score = savedInstanceState.getInt("score");
            number = savedInstanceState.getInt("number");
            button_state = savedInstanceState.getBoolean("button_state");
            temporaryString = "Score:"+ Integer.toString(score);
            Score.setText(temporaryString);
            FalseButton.setEnabled(button_state);
            TrueButton.setEnabled(button_state);
        }

        temporaryString = "Is " + Integer.toString(number) + " Prime ?" ;
        Question.setText(temporaryString);

        TrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(prime.isPrime(number)){
                    Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                    score++;
                    Log.d(TAG, "Clicked True");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect", Toast.LENGTH_SHORT).show();
                    score--;
                }
                button_state = false;
                TrueButton.setEnabled(false);
                FalseButton.setEnabled(false);
                temporaryString = "Score:"+ Integer.toString(score);
                Score.setText(temporaryString);
            }
        });

        FalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (!prime.isPrime(number)){
                    Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Clicked False");
                    score++;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                    score--;
                }
                button_state = false;
                TrueButton.setEnabled(false);
                FalseButton.setEnabled(false);
                temporaryString = "Score:"+ Integer.toString(score);
                Score.setText(temporaryString);
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = random.nextInt(1000);
                temporaryString = "Is " + Integer.toString(number) + " Prime ?";
                Question.setText(temporaryString);
                button_state = true;
                TrueButton.setEnabled(true);
                FalseButton.setEnabled(true);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("score",score);
        savedInstanceState.putInt("number",number);
        savedInstanceState.putBoolean("button_state",button_state);
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
        Log.d(TAG,"Inside OnResume");

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
