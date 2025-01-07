// The Model performs all the calculations needed and that is it.
// It doesn't know the View exists.

public class CalculatorModel {

    // Holds the value of the sum of the numbers entered in the view.

    private int calculationValue;

    // Adds the 2 numbers together.
    public void addTwoNumbers(int firstNumber, int secondNumber) {

        calculationValue = firstNumber + secondNumber;
    }

    // Retrieves the calculation solution (summation).
    public int getCalculationValue() {

        return calculationValue;
    }
}
