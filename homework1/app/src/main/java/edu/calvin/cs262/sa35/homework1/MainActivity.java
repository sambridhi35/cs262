/*
* Homework 1, CS262
* @author Sambridhi Acharya
* Calculator, calculates different values based on the given operator.
*
* */
package edu.calvin.cs262.sa35.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;
import android.util.Log;


//mainactivity class
public class MainActivity extends AppCompatActivity {

    int val1;
    int val2;
    EditText aValue1;
    EditText aValue2;
    String operate;
    TextView result;
    Spinner operator;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning the values to the appropriate value ids
        aValue1 = findViewById(R.id.inp1);
        aValue2 = findViewById(R.id.inp2);
        operator = findViewById(R.id.chooseop);
        result = findViewById(R.id.answer);
        b = findViewById(R.id.calc) ;

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                val1 = Integer.parseInt(aValue1.getText().toString());
                val2 = Integer.parseInt(aValue2.getText().toString());
                operate = operator.getSelectedItem().toString();


                if (operate.equals("+")){
                    result.setText(String.valueOf(val1+val2));
                }
                if (operate.equals("-")){
                    result.setText(String.valueOf(val1-val2));
                }
                if (operate.equals("/")){
                    result.setText(String.valueOf(val1/val2));
                }
                if (operate.equals("*")){
                    result.setText(String.valueOf(val1*val2));
                }

                Log.e("Answer",result.getText().toString());
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (aValue1!=null){
            aValue1.setText(val1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
