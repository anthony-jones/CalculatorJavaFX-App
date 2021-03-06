package calculator;

import javafx.scene.control.Label;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller {
    private final ArrayList<String> tempEntry;
    private final ArrayList<String> fullEntry;
    private String lastButtonPressed;
    public Label digitDisplay;

    public Controller() {
        this.tempEntry = new ArrayList<>();
        this.fullEntry = new ArrayList<>();
        this.lastButtonPressed = "";
    }

    // Number button actions
    public void oneButtonPressed() {
        this.numButtonUpdate("1");
    }

    public void twoButtonPressed() {
        this.numButtonUpdate("2");
    }

    public void threeButtonPressed() {
        this.numButtonUpdate("3");
    }

    public void fourButtonPressed() {
        this.numButtonUpdate("4");
    }

    public void fiveButtonPressed() {
        this.numButtonUpdate("5");
    }

    public void sixButtonPressed() {
        this.numButtonUpdate("6");
    }

    public void sevenButtonPressed() {
        this.numButtonUpdate("7");
    }

    public void eightButtonPressed() {
        this.numButtonUpdate("8");
    }

    public void nineButtonPressed() {
        this.numButtonUpdate("9");
    }

    public void zeroButtonPressed() {
        this.numButtonUpdate("0");
    }

    // Operator button actions
    public void addButtonPressed() {
        this.operatorButtonUpdate("+");
    }

    public void subButtonPressed() {
        this.operatorButtonUpdate("-");
    }

    public void multButtonPressed() {
        this.operatorButtonUpdate("*");
    }

    public void divButtonPressed() {
        this.operatorButtonUpdate("/");
    }

    // Equal button action
    public void equalsButtonPressed() {
        if (!this.lastButtonPressed.equals("=")) {
            storeTempToFull();
            double result = Double.parseDouble(this.fullEntry.get(0));
            for (int i = 1; i < this.fullEntry.size(); i++) {
                switch (this.fullEntry.get(i)) {
                    case "+" -> {
                        result += Double.parseDouble(this.fullEntry.get(i + 1));
                        i++;
                    }
                    case "-" -> {
                        result -= Double.parseDouble(this.fullEntry.get(i + 1));
                        i++;
                    }
                    case "*" -> {
                        result *= Double.parseDouble(this.fullEntry.get(i + 1));
                        i++;
                    }
                    case "/" -> {
                        result /= Double.parseDouble(this.fullEntry.get(i + 1));
                        i++;
                    }
                }
            }
            this.fullEntry.clear();
            this.tempEntry.clear();
            this.fullEntry.add(String.valueOf(result));

            DecimalFormat numberFormat = new DecimalFormat(("#.#########"));
            this.digitDisplay.setText(String.valueOf(numberFormat.format(result)));
            this.lastButtonPressed = "=";
        }
    }

    // Negation button action
    public void negationButtonPressed() {
        if (!this.tempEntry.isEmpty() || lastButtonIsNotOperator()) {
            this.operatorButtonUpdate("*");
            this.numButtonUpdate("-1");
            this.equalsButtonPressed();
        }
    }

    // Decimal button action
    public void decimalButtonPressed() {
        this.numButtonUpdate(".");
    }

    // Clear button action
    public void clearButtonPressed() {
        resetDisplay();
        this.tempEntry.clear();
        this.fullEntry.clear();
    }

    // Private internal functions
    private void numButtonUpdate(String num) {
        this.tempEntry.add(num);
        this.lastButtonPressed = num;
        updateDisplayWithNewDigit();
    }

    private void operatorButtonUpdate(String operator) {
        if (lastButtonIsNotOperator()) {
            storeTempToFull();
            resetDisplay();
        } else {
            this.fullEntry.remove(this.fullEntry.size() - 1);
        }
        this.fullEntry.add(operator);
        this.lastButtonPressed = operator;
    }

    private void updateDisplayWithNewDigit() {
        StringBuilder newDisplay = new StringBuilder();
        for (String digit : this.tempEntry) {
            newDisplay.append(digit);
        }
        this.digitDisplay.setText(newDisplay.toString());
    }

    private void storeTempToFull() {
        StringBuilder tempNumber = new StringBuilder();
        for (String digit : this.tempEntry) {
            tempNumber.append(digit);
        }
        this.fullEntry.add(tempNumber.toString());
        this.tempEntry.clear();
    }

    private void resetDisplay() {
        this.digitDisplay.setText("");
    }

    private boolean lastButtonIsNotOperator() {
        return !this.lastButtonPressed.equals("+") && !this.lastButtonPressed.equals("-")
                && !this.lastButtonPressed.equals("*") && !this.lastButtonPressed.equals("/");
    }
}
