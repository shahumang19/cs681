package edu.umb.cs681.hw02;

public class CarPriceResultHolder {
    private int numCarExamined; 
    private double average;


    public CarPriceResultHolder() {
        numCarExamined = 0;
        average = 0.0;
    }

    public int getNumCarExamined() {
        return this.numCarExamined;
    }

    public void setNumCarExamined(int numCarExamined) {
        this.numCarExamined = numCarExamined;
    }

    public double getAverage() {
        return this.average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
    
}
