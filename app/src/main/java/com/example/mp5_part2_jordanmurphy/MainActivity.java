package com.example.mp5_part2_jordanmurphy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private TextView scoreboard;
    private float score;
    private float correct, numQuestions;

    private static final int REQ_CODE = 2468;
    private static final String[] topics = {"Baseball", "Basketball", "Football"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.mylist);
        Button reset = (Button) findViewById(R.id.reset);
        scoreboard = (TextView) findViewById(R.id.scoreboard);

        score = 0;
        correct = 0;
        numQuestions = 0;
        scoreboard.setText(String.format("%.0f%%", score));


        final Intent intent = new Intent(this, MainActivity2.class);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);

        list.setAdapter(adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick (AdapterView<?> list, View row, int index, long id){
                        String topic = list.getItemAtPosition(index).toString();
                        intent.putExtra("topic", topic);
                        startActivityForResult(intent, REQ_CODE);
                    }
                }
        );


        // Reset the scoreboard if the user clicks reset
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                correct = 0;
                numQuestions = 0;
                scoreboard.setText(String.format("%.0f%%", score));
            }
        });
    }

    // Control is returned here
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == REQ_CODE){
            String ans = intent.getStringExtra("answer");

            try {
                // score += Integer.parseInt(ans);
                ++numQuestions;
                correct += Float.parseFloat(ans);
                score = (correct / numQuestions) * 100;

            } catch (Exception e){
                e.printStackTrace();
            }
            scoreboard.setText(String.format("%.1f%%", score));
            // scoreboard.setText(Float.toString(score));
        }
    }
}