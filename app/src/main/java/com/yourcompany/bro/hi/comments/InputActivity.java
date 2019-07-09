package com.yourcompany.bro.hi.comments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class InputActivity extends AppCompatActivity {

    Button inputData;
    EditText upperBound, lowerBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        setTitle("");
        inputData=findViewById(R.id.show_data);
        lowerBound= findViewById(R.id.lower_bound);
        upperBound= findViewById(R.id.upper_bound);




        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upperBound.length()>0 && lowerBound.length()>0 ) {
                    Intent intent = new Intent(InputActivity.this, CommentsActivity.class);
                    intent.putExtra("upperBound", upperBound.getText().toString());
                    intent.putExtra("lowerBound", lowerBound.getText().toString());
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(),"Please, enter two numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
