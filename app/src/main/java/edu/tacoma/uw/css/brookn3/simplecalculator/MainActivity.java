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
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String[] operators = {"+", "-", "*", "/"};

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

    private List<String> expressionList;
    private double result;

    /* Once a user presses the equals sign,
     * all buttons except the Clear button will be disabled.
     */
    private boolean isResultDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing fields:
        setupUIViews();


        expressionList = new LinkedList<String>();
        result = 0;
        isResultDisplayed = false;



        // Defining a listener for each object:
        numZeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numZeroBtn.getText().toString());
            }
        });

        numOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numOneBtn.getText().toString());
            }
        });

        numTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numTwoBtn.getText().toString());
            }
        });

        numThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numThreeBtn.getText().toString());
            }
        });

        numFourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numFourBtn.getText().toString());
            }
        });

        numFiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numFiveBtn.getText().toString());
            }
        });

        numSixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numSixBtn.getText().toString());
            }
        });

        numSevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numSevenBtn.getText().toString());
            }
        });

        numEightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numEightBtn.getText().toString());
            }
        });

        numNineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperandPlusHelper(numNineBtn.getText().toString());
            }
        });

        decimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String current = calcEditText.getText().toString();

                /* Special checks placed to place a zero in front
                 * of the decimal, when needed.
                 */
                if (current.isEmpty()) {
                    onClickOperandPlusHelper("0" + decimalBtn.getText().toString());
                } else {

                    String lastChar = current.substring(current.length() - 1);

                    if (lastChar.equals(operators[0]) || lastChar.equals(operators[1]) ||
                            lastChar.equals(operators[2]) || lastChar.equals(operators[3])) {
                        onClickOperandPlusHelper("0" + decimalBtn.getText().toString());
                    } else {
                        onClickOperandPlusHelper(decimalBtn.getText().toString());
                    }
                }
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperatorHelper(addBtn.getText().toString());
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperatorHelper(subtractBtn.getText().toString());
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperatorHelper(multiplyBtn.getText().toString());
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOperatorHelper(divideBtn.getText().toString());
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
                            R.string.warning_message_press_clear_first,
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

                    expressionList = new LinkedList<String>();
                    result = 0;

                    boolean isDouble = false;
                    int exprSize = strExpression.length();


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
                    Iterator follower = expressionList.iterator();

                    List<String> newList = new LinkedList<String>();

                    double firstOperand = 0;
                    double secondOperand = 0;

                    double tempResult;

                    /* Incremented when the operator is a
                     * multiplication or division and then
                     * the next operator is either a multiplication
                     * or division.
                     */
                    int backToBackCounter = 0;


                    boolean isMultiplication = false;
                    boolean isDivision = false;

                    // Computing any multiplication and division here:
                    while (leader.hasNext()) {
                        String currentNode = (String) leader.next();

                        if (currentNode.equals(operators[0]) ||
                                currentNode.equals(operators[1])) { // Addition or subtraction:

                            ((LinkedList<String>) newList).addFirst((String) follower.next());

                            if (backToBackCounter == 0) {
                                ((LinkedList<String>) newList).addFirst(currentNode);

                                /* Ignoring the addition/subtraction
                                 * operator and looking at the next
                                 * number which is exactly where the
                                 * leader iterator is currently at.
                                 */
                                follower.next();
                            } else {
                                backToBackCounter = 0;
                            }

                        }else if (currentNode.equals(operators[2]) ||
                                    currentNode.equals(operators[3])) {
                            // Multiplication and division:

                            isMultiplication = currentNode.equals(operators[2]);
                            isDivision = currentNode.equals(operators[3]);

                            if (backToBackCounter > 0) {
                                firstOperand = Double.parseDouble( (String) ((LinkedList<String>) newList).remove());
                            } else {
                                firstOperand = Double.parseDouble((String) follower.next());
                            }

                            follower.next();
                            backToBackCounter++;

                        } else { // This section means the currentNode is a numerical value:

                            /* This check ignores the numerical
                             * values, which are always kept track
                             * by the follower iterator, and when
                             * the operator is known, to be
                             * multiplication or division then the
                             * calculation is completed.
                             */
                            if (isMultiplication || isDivision) {
                                tempResult = 0;
                                secondOperand = Double.parseDouble((String) follower.next());

                                if (isMultiplication) {
                                    tempResult = firstOperand * secondOperand;
                                    isMultiplication = false;
                                } else if (isDivision) {
                                    tempResult = firstOperand / secondOperand;
                                    isDivision = false;
                                }

                                // Storing new result:
                                ((LinkedList<String>) newList).addFirst("" + tempResult);
                            } else if (follower.hasNext() && !leader.hasNext()) {
                                /* Capturing the last numerical value if
                                 * the last operation was either an
                                 * addition or subtraction.
                                 */

                                ((LinkedList<String>) newList).addFirst((String) follower.next());
                            }
                        }
                    } // End of while()

                    expressionList = new LinkedList<String>(newList);


                    // Resetting iterators:
                    leader = expressionList.iterator();

                    secondOperand = 0;
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
                                result = Double.parseDouble(currentNode);
                            } else if (indexCounter % 2 == 0) {
                                secondOperand = Double.parseDouble(currentNode);

                                // Calculating part of the expression:
                                if (isAddition) {
                                    result += secondOperand;
                                } else if (isSubtraction) {
                                    result -= secondOperand;
                                }

                                // Resetting the operators check:
                                isAddition = false;
                                isSubtraction = false;
                            }
                        }

                        indexCounter++;
                    }

                    // Result:
                    if (isDouble || result != Math.floor(result)) {
                        calcEditText.setText(strExpression + "=" + result);
                    } else {
                        calcEditText.setText(strExpression + "=" + ((int) result));
                    }
                }
            }
        });
    }

    /* It's named OperandPlus because all of the number buttons use
     * this method as well as the decimal button.
     */
    private void onClickOperandPlusHelper(final String display) {
        if (isResultDisplayed) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.warning_message_press_clear_first,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            calcEditText.setText(calcEditText.getText().toString() +
                    display);
        }
    }

    private void onClickOperatorHelper(final String display) {
        Toast toast = new Toast(getApplicationContext());
        boolean isReadyToShow = false;

        final String current = calcEditText.getText().toString();


        if (isResultDisplayed) {
            toast = Toast.makeText(getApplicationContext(),
                    R.string.warning_message_press_clear_first,
                    Toast.LENGTH_SHORT);
            isReadyToShow = true;

        } else if (current.isEmpty()) {
            toast = Toast.makeText(getApplicationContext(),
                    R.string.warning_message_press_num_first,
                    Toast.LENGTH_SHORT);
            isReadyToShow = true;
        } else {

            String lastChar = current.substring(current.length() - 1);

            // Preventing two, or more, operators from being adjacent to each other:
            if (lastChar.equals(operators[0]) || lastChar.equals(operators[1]) ||
                    lastChar.equals(operators[2]) || lastChar.equals(operators[3])) {

                if (!lastChar.equals(display)) {
                    calcEditText.setText(current.substring(0, current.length() - 1) +
                            display);
                }
            } else {
                calcEditText.setText(current + display);
            }
        }

        if (isReadyToShow) {
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
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