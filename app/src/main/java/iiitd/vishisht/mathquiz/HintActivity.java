package iiitd.vishisht.mathquiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HintActivity extends AppCompatActivity {

    private Button no_hint_button;
    private boolean button_status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        no_hint_button = (Button) findViewById(R.id.noHint_button);
        if (savedInstanceState != null){
            button_status = savedInstanceState.getBoolean("button_status");
            no_hint_button.setEnabled(button_status);
            Intent intent = new Intent();
            intent.putExtra("hint_status","true");
            setResult(RESULT_OK, intent);
        }
    }

    public void takeHint(View view) {
        // Do something in response to button
        Intent intent = new Intent();
        intent.putExtra("hint_status","true");
        setResult(RESULT_OK, intent);
        no_hint_button.setEnabled(false);
        button_status = false;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Prime number is only divisible by 1 and itself");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void dontTakeHint(View view){
        Intent intent = new Intent();
        intent.putExtra("hint_status","false");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("button_status",button_status);
    }

}
