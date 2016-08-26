package iiitd.vishisht.mathquiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheatActivity extends AppCompatActivity {

    private Button dontCheatButton;

    private final Prime prime = new Prime();
    private String number;
    private boolean button_status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Button cheatButton = (Button) findViewById(R.id.doCheat_Button);
        dontCheatButton = (Button) findViewById(R.id.dontCheat_Button);
        Intent intent = getIntent();
        number = intent.getStringExtra(QuizActivity.NUMBER);

        if(savedInstanceState != null){
            button_status = savedInstanceState.getBoolean("button_status");
            number = savedInstanceState.getString("number");
            if (!button_status){
                dontCheatButton.setEnabled(false);
                button_status = false;
                intent.putExtra("cheat_status","true");
                setResult(RESULT_OK, intent);
            }
        }
    }

    public void cheat(View view){
        int temp = Integer.parseInt(number);
        if(prime.isPrime(temp)){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Yes the number is Prime");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No the number is not Prime");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        dontCheatButton.setEnabled(false);
        button_status = false;
        Intent intent = new Intent();
        intent.putExtra("cheat_status","true");
        setResult(RESULT_OK, intent);
    }

    public void dontCheat(View view){
        Intent intent = new Intent();
        intent.putExtra("cheat_status","false");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("button_status",button_status);
        savedInstanceState.putString("number",number);
    }

}
