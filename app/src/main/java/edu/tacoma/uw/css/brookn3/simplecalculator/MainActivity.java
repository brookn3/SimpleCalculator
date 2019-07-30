package edu.tacoma.uw.css.brookn3.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button numZeroBtn;
    private Button numOneBtn;
    private Button numTwoBtn;
    private Button numThreeBtn;
    private Button numFourBtn;
    private Button numFiveBtn;
    private Button numSixBtn;
    private Button numSevenBtn;
    private Button numEightBtn;
    private Button numNineBtn;

    private Button decimalBtn;
    private Button addBtn;
    private Button subtractBtn;
    private Button multiplyBtn;
    private Button divideBtn;
    private Button clearBtn;
    private Button backBtn;
    private Button equalsBtn;

    private EditText calcEditText;

    private StringBuilder currentValue;
    private List<Double> inputValues;
    private List<String> arithmeticOperators;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing fields:
        setupUIViews();
        currentValue = new StringBuilder();
        inputValues = new LinkedList<Double>();
        arithmeticOperators = new LinkedList<String>();
        result = 0;


        // Defining a listener for each object:
        numZeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numZeroBtn.getText().toString());
            }
        });

        numOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numOneBtn.getText().toString());
            }
        });

        numTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numTwoBtn.getText().toString());
            }
        });

        numThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numThreeBtn.getText().toString());
            }
        });

        numFourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numFourBtn.getText().toString());
            }
        });

        numFiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numFiveBtn.getText().toString());
            }
        });

        numSixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numSixBtn.getText().toString());
            }
        });

        numSevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numSevenBtn.getText().toString());
            }
        });

        numEightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numEightBtn.getText().toString());
            }
        });

        numNineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        numNineBtn.getText().toString());
            }
        });

        decimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        decimalBtn.getText().toString());
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        addBtn.getText().toString());
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        subtractBtn.getText().toString());
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        multiplyBtn.getText().toString());
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(calcEditText.getText().toString() +
                        divideBtn.getText().toString());
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current = calcEditText.getText().toString();
                if (!current.isEmpty()) {
                    calcEditText.setText(current.substring(0, current.length() - 1));
                }
            }
        });

        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupUIViews() {
        numZeroBtn  = (Button) findViewById(R.id.numZeroBtn);
        numOneBtn = (Button) findViewById(R.id.numOneBtn);
        numTwoBtn = (Button) findViewById(R.id.numTwoBtn);
        numThreeBtn = (Button) findViewById(R.id.numThreeBtn);
        numFourBtn = (Button) findViewById(R.id.numFourBtn);
        numFiveBtn = (Button) findViewById(R.id.numFiveBtn);
        numSixBtn = (Button) findViewById(R.id.numSixBtn);
        numSevenBtn = (Button) findViewById(R.id.numSevenBtn);
        numEightBtn  = (Button) findViewById(R.id.numEightBtn);
        numNineBtn = (Button) findViewById(R.id.numNineBtn);

        decimalBtn = (Button) findViewById(R.id.decimalBtn);
        addBtn = (Button) findViewById(R.id.addBtn);
        subtractBtn = (Button) findViewById(R.id.subtractBtn);
        multiplyBtn = (Button) findViewById(R.id.multiplyBtn);
        divideBtn = (Button) findViewById(R.id.divideBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        equalsBtn = (Button) findViewById(R.id.equalsBtn);

        calcEditText = (EditText) findViewById(R.id.calcEditText);
    }
}
