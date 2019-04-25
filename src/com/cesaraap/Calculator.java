package com.cesaraap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;

public class Calculator extends JFrame{

    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JLabel results = new JLabel("Your BMI is:");
    private JLabel body = new JLabel("Your body type is:");
    private ButtonGroup units = new ButtonGroup();

    public Calculator(){
        this.setTitle("CALCULATOR");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(5,2));

        JLabel label1 = new JLabel("Enter your height");
        JLabel label2 = new JLabel("Enter your weight");
        JRadioButton rbut1 = new JRadioButton("Metric");
        rbut1.setActionCommand("Metric");
        JRadioButton rbut2 = new JRadioButton("Imperial");
        rbut2.setActionCommand("Imperial");
        JButton clean = new JButton("Clean input and results");
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                results.setText("Your BMI is:");
                body.setText("Your body type is:");
            }
        });

        units.add(rbut1);
        units.add(rbut2);

        rbut1.setSelected(true);
        rbut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Enter your height in cm");
                label2.setText("Enter your weight in kg");
                calculation();
            }
        });
        rbut2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("Enter your height in in");
                label2.setText("Enter your weight in lb");
                calculation();
            }
        });

        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculation();
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(label1);
        this.add(textField1);
        this.add(label2);
        this.add(textField2);
        this.add(rbut1);
        this.add(rbut2);
        this.add(results);
        this.add(body);
        this.add(clean);
        this.add(button);

        this.pack();
        this.setVisible(true);

    }

    private double calculator(double height, double weight){
        if(units.getSelection().getActionCommand().equals("Metric")){
            return weight / (height * height) * 10000;
        }else{
            return weight * 703 / (height * height);
        }
    }

    private void calculation(){
        String bodyType;
        double bmi = calculator(parseDouble(textField1.getText()), parseDouble(textField2.getText()));
        results.setText("Your bmi is: " + String.format("%.2f", bmi));

        if (bmi < 18.5) {
            bodyType = "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            bodyType = "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            bodyType = "Overweight";
        } else if (bmi >= 30 && bmi < 40) {
            bodyType = "Obesity";
        } else {
            bodyType = "Morbid obesity";
        }

        body.setText("Your body type: " + bodyType);
        this.pack();
    }
}
