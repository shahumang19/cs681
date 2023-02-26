package edu.umb.cs681.hw03;

import java.util.Date;

public class Summary {
    private double open, close, high, low;
    private Date date;

    public Summary(Date date, double open, double high, double low, double close) {
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
    }

    public Date getDate() {
        return this.date;
    }

    public double getOpen() {
        return this.open;
    }

    public double getClose() {
        return this.close;
    }

    public double getHigh() {
        return this.high;
    }

    public double getLow() {
        return this.low;
    }

}
