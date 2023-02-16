package edu.umb.cs681.hw01;

import java.util.List;

public class Car {
    private String make, model;
    private int mileage, year;
    private float price;
    private int dominationCount;

    public Car(String make, String model, int year, int mileage, float price){
        if((make == null) | (model == null)){
            throw new NullPointerException("Make and Model should be string values!!!");
        }
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        this.dominationCount = 0;
    }

    public String getMake(){
        return this.make;
    }

    public String getModel(){
        return this.model;
    }

    public int getMileage(){
        return this.mileage;
    }

    public int getYear(){
        return this.year;
    }

    public float getPrice(){
        return this.price;
    }

    public void setDominationCount(List<Car> cars){
        this.dominationCount = 0;
        for(Car car: cars){
            if (car.mileage >= this.mileage && car.year >= this.year && car.price <= this.price){
                if (car.mileage > this.mileage || car.year > this.year || car.price < this.price){
                    this.dominationCount += 1;
                }
            }
        }
    }

    public int getDominationCount(){
        return this.dominationCount;
    }

    public String toString(){
        return "Car{" +"model=" + model + ", make=" + make + ", mileage=" + mileage +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        Car car = new Car("Mercedes", "A-Class", 2022, 25, 34000.0F);

        System.out.println(car);
    }
}