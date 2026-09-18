package com.example.loancalculator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

    // User Inputs
    TextField interestField = new TextField();
    TextField yearsField = new TextField();
    TextField loanField = new TextField();

    // Calculated Fields
    TextField monthlyField = new TextField();
    TextField totalField = new TextField();

    public void start(Stage stage) {

        // Table layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        // interest rate
        grid.add(new Label("Annual Interest Rate:"), 0, 0);
        grid.add(interestField, 1, 0);

        // number of years
        grid.add(new Label("Number of Years:"), 0, 1);
        grid.add(yearsField, 1, 1);

        // loan amount
        grid.add(new Label("Loan Amount:"), 0, 2);
        grid.add(loanField, 1, 2);

        // monthly payment answer
        grid.add(new Label("Monthly Payment:"), 0, 3);
        grid.add(monthlyField, 1, 3);
        monthlyField.setEditable(false);

        // total payment answer
        grid.add(new Label("Total Payment:"), 0, 4);
        grid.add(totalField, 1, 4);
        totalField.setEditable(false);

        // Calculate button
        Button computeButton = new Button("Calculate");
        grid.add(computeButton, 1, 5);

        // this runs the code below when the button gets clicked
        computeButton.setOnAction(e -> {

            // grab what the user typed and turn it into numbers
            double interestRate = Double.parseDouble(interestField.getText());
            int years = Integer.parseInt(yearsField.getText());
            double loanAmount = Double.parseDouble(loanField.getText());

            double monthlyInterest = interestRate / 1200;

            // years to months payment
            int numberOfPayments = years * 12;

            // loan payment
            double top = monthlyInterest;
            double bottom = 1 - Math.pow(1 + monthlyInterest, -numberOfPayments);
            double monthlyPayment = loanAmount * (top / bottom);

            // total payment
            double totalPayment = monthlyPayment * numberOfPayments;

            // put the answers in the boxes
            monthlyField.setText("$" + String.format("%.2f", monthlyPayment));
            totalField.setText("$" + String.format("%.2f", totalPayment));
        });

        // put everything together and show the window
        Scene scene = new Scene(grid, 350, 200);
        stage.setTitle("LoanCalculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}