package edu.tacoma.uw.css.brookn3.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Rounding precision:
    private static final int ROUNDING_PRECISION_TO_DISPLAY = 15;
    private static final int ROUNDING_WHILE_CALCULATING = 50;

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
    private BigDecimal bdResult;

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
        bdResult = BigDecimal.ZERO;
        isResultDisplayed = false;


        // Defining a few of the listeners for some objects:
        decimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String current = calcEditText.getText().toString();
                /* Special checks placed to place a zero in front
                 * of the decimal, when needed.
                 */
                String display = current.isEmpty() || isLastCharAnOperator(current)
                        ? "0" : "";
                display += decimalBtn.getText().toString();

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
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcEditText.setText(new String());
                bdResult = BigDecimal.ZERO;
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

                    if (isLastCharAnOperator(strExpression)) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Please select another number first.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else if (strExpression.length() == 1) { // Only one number pressed:
                        isResultDisplayed = true;
                        double currentValue = Double.parseDouble(strExpression);
                        bdResult = BigDecimal.valueOf(currentValue);

                        calcEditText.setText(strExpression + "=" + ((int) bdResult.doubleValue()));
                    } else {

                        // Disabling every other button except the Clear button.
                        isResultDisplayed = true;

                        // If the user tries to divide by zero;
                        boolean isAnUndefinedStatement = false;
                        String undefinedStatement = "";

                        expressionList = new LinkedList<String>();
                        bdResult = BigDecimal.ZERO;

                        boolean isDouble = false;
                        int exprSize = strExpression.length();

                        /* In the case a division is executed in the
                         * given expression, it may lead to the number
                         * being rounded.
                         */
                        boolean hasBeenRounded = false;


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

                                if (i == exprSize - 1) { // Last token:
                                    expressionList.add(previous);
                                }
                            } else if (isLastCharAnOperator(current)) {

                                expressionList.add(previous); // Still stored as a String Object.
                                expressionList.add(current);

                                previous = new String();

                            } else {
                                previous = previous + current;

                                if (i == exprSize - 1) { // Last digit:
                                    expressionList.add(previous);
                                }
                            }
                        }


                        // Traversing the newly built list (LinkedList) and
                        // completing the calculation following the PEMDOS property:
                        Iterator leader = expressionList.iterator();
                        Iterator follower = expressionList.iterator();

                        List<String> newList = new LinkedList<String>();

                        BigDecimal bdFirstOperand = BigDecimal.ZERO;
                        BigDecimal bdSecondOperand = BigDecimal.ZERO;

                        BigDecimal bdTempResult;

                        /* Incremented when the operator is a
                         * multiplication or division and then
                         * the next operator is either a multiplication
                         * or division.
                         */
                        int backToBackCounter = 0;


                        boolean isMultiplication = false;
                        boolean isDivision = false;

                        // Computing any multiplication and division here:
                        while (leader.hasNext() && !isAnUndefinedStatement) {
                            String currentNode = (String) leader.next();

                            if (currentNode.equals(operators[0]) ||
                                    currentNode.equals(operators[1])) { // Addition or subtraction:

                                ((LinkedList<String>) newList).add((String) follower.next());

                                if (backToBackCounter == 0) {
                                    ((LinkedList<String>) newList).add(currentNode);

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
                                Double currentValue;

                                if (backToBackCounter > 0) {
                                    currentValue = Double.parseDouble( (String) ((LinkedList<String>) newList).removeLast());

                                } else {
                                    currentValue = Double.parseDouble((String) follower.next());
                                }

                                bdFirstOperand = BigDecimal.valueOf(currentValue);

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
                                    bdTempResult = BigDecimal.ZERO;

                                    Double currentValue = Double.parseDouble((String) follower.next());
                                    bdSecondOperand = BigDecimal.valueOf(currentValue);

                                    if (isMultiplication) {
                                        bdTempResult = bdFirstOperand.multiply(bdSecondOperand);

                                        isMultiplication = false;
                                    } else if (isDivision) {

                                        // Covering undefined cases:
                                        if (((int) bdSecondOperand.doubleValue()) == 0) {
                                            isAnUndefinedStatement = true;
                                            if (((int) bdFirstOperand.doubleValue()) == 0) {
                                                undefinedStatement = "Undefined";
                                            } else {
                                                undefinedStatement = "Cannot divide by zero";
                                            }
                                        } else {

                                            /* Making sure to deal with the case if the
                                             * division results in a very very long
                                             * decimal value.
                                             */
                                            try {
                                                bdTempResult = bdFirstOperand.divide(bdSecondOperand);
                                            } catch (ArithmeticException e) {
                                                bdTempResult = bdFirstOperand.divide(bdSecondOperand,
                                                        ROUNDING_WHILE_CALCULATING,
                                                        BigDecimal.ROUND_HALF_UP);

                                                hasBeenRounded = true;
                                            }
                                        }

                                        isDivision = false;
                                    }

                                    // Storing new result:
                                    ((LinkedList<String>) newList).add("" + bdTempResult);

                                } else if (follower.hasNext() && !leader.hasNext()) {
                                    /* Capturing the last numerical value if
                                     * the last operation was either an
                                     * addition or subtraction.
                                     */

                                    ((LinkedList<String>) newList).add((String) follower.next());
                                }
                            }
                        } // End of while()

                        expressionList = new LinkedList<String>(newList);


                        // Resetting iterators:
                        leader = expressionList.iterator();

                        bdSecondOperand = BigDecimal.ZERO;
                        boolean isAddition = false;
                        boolean isSubtraction = false;

                        int indexCounter = 0;
                        String currentNode;

                        // Computing addition and subtraction here:
                        while (leader.hasNext() && !isAnUndefinedStatement) {
                            currentNode = (String) leader.next();

                            if (currentNode.equals(operators[0])) { // Addition:
                                isAddition = true;
                            } else if (currentNode.equals(operators[1])) { // Subtraction:
                                isSubtraction = true;
                            } else {

                                // Capturing the values:
                                if (indexCounter == 0) {
                                    Double currentValue = Double.parseDouble(currentNode);
                                    bdResult = BigDecimal.valueOf(currentValue);

                                } else if (indexCounter % 2 == 0) {
                                    Double currentValue = Double.parseDouble(currentNode);
                                    bdSecondOperand = BigDecimal.valueOf(currentValue);

                                    // Calculating part of the expression:
                                    if (isAddition) {
                                        bdResult = bdResult.add(bdSecondOperand);
                                    } else if (isSubtraction) {
                                        bdResult = bdResult.subtract(bdSecondOperand);
                                    }

                                    // Resetting the operators check:
                                    isAddition = false;
                                    isSubtraction = false;
                                }
                            }

                            indexCounter++;
                        }

                        double resultAsDouble = bdResult.doubleValue();

                        // Result:
                        if (isAnUndefinedStatement) {
                            calcEditText.setText(strExpression + "=" + undefinedStatement);
                        } else if (hasBeenRounded) {
                            bdResult = bdResult.setScale(ROUNDING_PRECISION_TO_DISPLAY,
                                    BigDecimal.ROUND_HALF_UP);

                            calcEditText.setText(strExpression + "=" + bdResult);
                        } else if (isDouble || resultAsDouble != Math.floor(resultAsDouble)) {
                            calcEditText.setText(strExpression + "=" + bdResult);
                        } else {
                            calcEditText.setText(strExpression + "=" + ((int) bdResult.doubleValue()));
                        }
                    }
                }
            }
        });
    }


    public void onClickOperandHelper(View v) {
        if (isResultDisplayed) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.warning_message_press_clear_first,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            String display = ((Button) findViewById(v.getId())).getText().toString();

            calcEditText.setText(calcEditText.getText().toString() +
                    display);
        }
    }

    public void onClickOperatorHelper(View v) {
        Toast toast = new Toast(getApplicationContext());
        boolean isReadyToShow = false;

        final String current = calcEditText.getText().toString();
        String display = ((Button) findViewById(v.getId())).getText().toString();


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
            if (isLastCharAnOperator(lastChar)) {

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

    private boolean isLastCharAnOperator(final String theChar) {
        boolean isOperator = false;

        if (theChar != null && !theChar.isEmpty()) {
            final String lastChar = theChar.substring(theChar.length() - 1);

            if (lastChar.equals(operators[0]) || lastChar.equals(operators[1]) ||
                    lastChar.equals(operators[2]) || lastChar.equals(operators[3])) {
                isOperator = true;
            }
        }

        return isOperator;
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