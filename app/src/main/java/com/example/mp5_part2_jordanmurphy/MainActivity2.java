package com.example.mp5_part2_jordanmurphy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log; // for logging the state in logcat
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;



public class MainActivity2 extends AppCompatActivity {

    Button submit;
    TextView question;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        question = (TextView) findViewById(R.id.question);
        editText = (EditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.submit);

        Intent intent = getIntent();
        final String extra = intent.getStringExtra("topic"); // need to figure what the topic parameter is


        switch(extra){
            case "Baseball":
                question.setText("Who is Houston's baseball team?");
                break;
            case "Basketball":
                question.setText("Who is Houston's basketball team?");
                break;
            case "Football":
                question.setText("Who is Houston's football team?");
                break;
            default:
                break;
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent();
                String ans = editText.getText().toString();

                if (ans.equalsIgnoreCase("astros") && extra.equalsIgnoreCase("Baseball")){
                    backIntent.putExtra("answer", "1");

                } else if (ans.equalsIgnoreCase("rockets") && extra.equalsIgnoreCase("Basketball")){
                    backIntent.putExtra("answer", "1");

                } else if (ans.equalsIgnoreCase("texans") && extra.equalsIgnoreCase("Football")){
                    backIntent.putExtra("answer", "1");

                } else {
                    backIntent.putExtra("answer", "0");
                }

                setResult(RESULT_OK, backIntent);
                finish();
            }
        });

    }
}
