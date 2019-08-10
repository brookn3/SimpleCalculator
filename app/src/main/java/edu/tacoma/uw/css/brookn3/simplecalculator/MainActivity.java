package edu.tacoma.uw.css.brookn3.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;
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

    private List<Object> expressionList;
    private double result;

    /* Once a user presses the equals sign,
     * all buttons except the Clear will be disabled.
     */
    private boolean isResultDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing fields:
        setupUIViews();

        expressionList = new LinkedList<Object>();
        result = 0;
        isResultDisplayed = false;



        // Defining a listener for each object:
        numZeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numZeroBtn.getText().toString());
            }
        });

        numOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numOneBtn.getText().toString());
            }
        });

        numTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numTwoBtn.getText().toString());
            }
        });

        numThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numThreeBtn.getText().toString());
            }
        });

        numFourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numFourBtn.getText().toString());
            }
        });

        numFiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numFiveBtn.getText().toString());
            }
        });

        numSixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numSixBtn.getText().toString());
            }
        });

        numSevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numSevenBtn.getText().toString());
            }
        });

        numEightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numEightBtn.getText().toString());
            }
        });

        numNineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(numNineBtn.getText().toString());
            }
        });

        decimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(decimalBtn.getText().toString());
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(addBtn.getText().toString());
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(subtractBtn.getText().toString());
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(multiplyBtn.getText().toString());
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHelper(divideBtn.getText().toString());
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(new String());

                isResultDisplayed = false;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isResultDisplayed) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please press the Clear button first.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    String current = calcEditText.getText().toString();
                    if (!current.isEmpty()) {
                        calcEditText.setText(current.substring(0, current.length() - 1));
                    }
                }
            }
        });













        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strExpression = calcEditText.getText().toString();

                if (!strExpression.isEmpty() && !isResultDisplayed) {

                    // Disabling every other button except the Clear button.
                    isResultDisplayed = true;

                    expressionList = new LinkedList<Object>();
                    result = 0;

                    boolean isDouble = false;
                    int exprSize = strExpression.length();
                    String[] operators = {"+", "-", "*", "/"};

                    // Capturing the first token:
                    String previous = strExpression.substring(0, 1);
                    String current;

                    // Traversing the strExpression to capture the operators and operands
                    // and building on the "expressionList" LinkedList:
                    for (int i = 1; i < exprSize; i++) {
                        current = strExpression.substring(i, i + 1);

                        if (current.equals(".")) { // The number is a decimal
                            isDouble = true;

                            previous = previous + current;
                        } else if (current.equals(operators[0]) || current.equals(operators[1]) ||
                                current.equals(operators[2]) || current.equals(operators[3])) {

                            expressionList.add(previous); // Still stored as a String Object.
                            expressionList.add(current);

                            previous = new String();

                        } else { // TODO: Make sure to check for all possible inputs later!!!
                            previous = previous + current;

                            if (i == exprSize - 1) { // last digit
                                expressionList.add(previous);
                            }
                        }
                    }


                    // Traversing the newly built list (LinkedList) and
                    // completing the calculation following the PEMDOS property:
                    Iterator leader = expressionList.iterator();




//                    Iterator follower = expressionList.iterator();
//                    Iterator ff = expressionList.iterator(); // The follower's follower.
//
//                    // Used to make sure the three iterators are in
//                    // one line instead of jumping ahead of one another.
//                    int counter = 0;
//
//                    // Computing any multiplication and division here:
//                    while (leader.hasNext()) {
//                        String currentNode = (String) leader.next();
//
//                        if (currentNode.equals(operators[2])) {
//
//
//                        } else if (currentNode.equals(operators[3])) {
//
//                        } else {
//                            if (counter >= 1) {
//                                follower.next();
//                            }
//                            if (counter >= 2) {
//                                ff.next();
//                            }
//                        }
//
//                        counter++;
//                    }



                    // Resetting iterators:
                    leader = expressionList.iterator();

                    double secondOperand = 0;
                    boolean isAddition = false;
                    boolean isSubtraction = false;

                    int indexCounter = 0;
                    String currentNode;

                    // Computing addition and subtraction here:
                    while (leader.hasNext()) {
                        currentNode = (String) leader.next();

                        if (currentNode.equals(operators[0])) { // Addition:
                            isAddition = true;
                        } else if (currentNode.equals(operators[1])) { // Subtraction:
                            isSubtraction = true;
                        } else {

                            // Capturing the values:
                            if (indexCounter == 0) {
                                result = Double.parseDouble(currentNode); // TODO: change code b/c this will destroy the value developed from the code above
                            } else if (indexCounter % 2 == 0) {
                                secondOperand = Double.parseDouble(currentNode);

                                // Calculating part of the expression:
                                if (isAddition) {
                                    result += secondOperand;
                                } else if (isSubtraction) {
                                    result -= secondOperand;
                                }


                                // Resetting the operands check:
                                isAddition = false;
                                isSubtraction = false;
                            }
                        }

                        indexCounter++;
                    }

                    // Result:
                    if (isDouble) { // TODO: Figure out how you want to display the result & make
                                    // TODO: sure to put an equals sign prior to displaying the result:
                        calcEditText.setText(strExpression + "=" + result);
                    } else {
                        calcEditText.setText(strExpression + "=" + ((int) result));
                    }
                }
            }
        });
    }


    private void onClickHelper(final String display) {
        if (isResultDisplayed) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please press the Clear button first.",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            calcEditText.setText(calcEditText.getText().toString() +
                    display);
        }
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