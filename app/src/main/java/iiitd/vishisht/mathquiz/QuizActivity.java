package iiitd.vishisht.mathquiz;

import android.content.Intent;
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
    static final String NUMBER = "iiitd.vishisht.mathquiz.number";

    //Buttons
    private Button TrueButton;
    private Button FalseButton;
    private Button NextButton;
    private Button HintButton;
    private Button CheatButton;
    //TextView
    private TextView Question;
    private TextView Score;

    private final Random random = new Random();
    private final Prime prime = new Prime();
    private int number = random.nextInt(1000);
    private int score = 0;
    private boolean true_false_state = true;
    private boolean cheat_state = true;
    private boolean hint_state = true;
    
    private String temporaryString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        TrueButton = (Button) findViewById(R.id.true_button);
        FalseButton = (Button) findViewById(R.id.false_button);
        NextButton = (Button) findViewById(R.id.next_button);
        HintButton = (Button) findViewById(R.id.hint_button);
        CheatButton = (Button) findViewById(R.id.cheat_button);
        Question = (TextView) findViewById(R.id.question_box);
        Score = (TextView) findViewById(R.id.score_box);


        if(savedInstanceState!= null){
            score = savedInstanceState.getInt("score");
            number = savedInstanceState.getInt("number");
            true_false_state = savedInstanceState.getBoolean("true_false_state");
            cheat_state = savedInstanceState.getBoolean("cheat_state");
            hint_state = savedInstanceState.getBoolean("hint_state");
            temporaryString = "Score:"+ Integer.toString(score);
            Score.setText(temporaryString);
            FalseButton.setEnabled(true_false_state);
            TrueButton.setEnabled(true_false_state);
            CheatButton.setEnabled(cheat_state);
            HintButton.setEnabled(hint_state);
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
                true_false_state = false;
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
                true_false_state = false;
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
                true_false_state = true;
                TrueButton.setEnabled(true);
                FalseButton.setEnabled(true);
                HintButton.setEnabled(true);
                CheatButton.setEnabled(true );
            }
        });


    }

    public void goToHintActivity(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, HintActivity.class);
        startActivityForResult(intent,1);
    }

    public void goToCheatActivity(View view){
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(NUMBER, Integer.toString(number));
        startActivityForResult(intent,2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if(intent.getStringExtra("hint_status").equals("true")){
                Toast.makeText(getApplicationContext(),"Hint Used",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Inside OnActivityResult");
                HintButton.setEnabled(false);
                hint_state = false;
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            if(intent.getStringExtra("cheat_status").equals("true")){
                Toast.makeText(getApplicationContext(),"Cheat Used",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Inside OnActivityResult");
                CheatButton.setEnabled(false);
                cheat_state = false;
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("score",score);
        savedInstanceState.putInt("number",number);
        savedInstanceState.putBoolean("true_false_state",true_false_state);
        savedInstanceState.putBoolean("cheat_state",cheat_state);
        savedInstanceState.putBoolean("hint_state",hint_state);
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
