package edu.umb.cs681.hw04;

public class BostonHousingData {
    private double crimeRate, zoned, indus,	nox, numberOfRooms, houseAge,	distanceFromEmploymentCenters,
                	taxRate, ptRatio, blacksByTown, lStat, medv;
    private boolean chas;
    private int accessibilityToHighways;


    public BostonHousingData(double crimeRate, double zoned, double indus, boolean chas, double nox, double numberOfRooms, double houseAge, double distanceFromEmploymentCenters, int accessibilityToHighways, double taxRate, double ptRatio, double blacksByTown, double lStat, double medv) {
        this.crimeRate = crimeRate;
        this.zoned = zoned;
        this.indus = indus;
        this.nox = nox;
        this.numberOfRooms = numberOfRooms;
        this.houseAge = houseAge;
        this.distanceFromEmploymentCenters = distanceFromEmploymentCenters;
        this.accessibilityToHighways = accessibilityToHighways;
        this.taxRate = taxRate;
        this.ptRatio = ptRatio;
        this.blacksByTown = blacksByTown;
        this.lStat = lStat;
        this.medv = medv;
        this.chas = chas;
    }


    public double getCrimeRate() {
        return this.crimeRate;
    }

    public void setCrimeRate(double crimeRate) {
        this.crimeRate = crimeRate;
    }

    public double getZoned() {
        return this.zoned;
    }

    public void setZoned(double zoned) {
        this.zoned = zoned;
    }

    public double getIndus() {
        return this.indus;
    }

    public void setIndus(double indus) {
        this.indus = indus;
    }

    public double getNox() {
        return this.nox;
    }

    public void setNox(double nox) {
        this.nox = nox;
    }

    public double getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getHouseAge() {
        return this.houseAge;
    }

    public void setHouseAge(double houseAge) {
        this.houseAge = houseAge;
    }

    public double getDistanceFromEmploymentCenters() {
        return this.distanceFromEmploymentCenters;
    }

    public void setDistanceFromEmploymentCenters(double distanceFromEmploymentCenters) {
        this.distanceFromEmploymentCenters = distanceFromEmploymentCenters;
    }

    public int getAccessibilityToHighways() {
        return this.accessibilityToHighways;
    }

    public void setAccessibilityToHighways(int accessibilityToHighways) {
        this.accessibilityToHighways = accessibilityToHighways;
    }

    public double getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getPtRatio() {
        return this.ptRatio;
    }

    public void setPtRatio(double ptRatio) {
        this.ptRatio = ptRatio;
    }

    public double getBlacksByTown() {
        return this.blacksByTown;
    }

    public void setBlacksByTown(double blacksByTown) {
        this.blacksByTown = blacksByTown;
    }

    public double getLStat() {
        return this.lStat;
    }

    public void setLStat(double lStat) {
        this.lStat = lStat;
    }

    public double getMedv() {
        return this.medv;
    }

    public void setMedv(double medv) {
        this.medv = medv;
    }

    public boolean isChas() {
        return this.chas;
    }

    public boolean getChas() {
        return this.chas;
    }

    public void setChas(boolean chas) {
        this.chas = chas;
    }

    @Override
    public String toString() {
        return "{" +
            " crimeRate='" + getCrimeRate() + "'" +
            ", zoned='" + getZoned() + "'" +
            ", indus='" + getIndus() + "'" +
            ", nox='" + getNox() + "'" +
            ", numberOfRooms='" + getNumberOfRooms() + "'" +
            ", houseAge='" + getHouseAge() + "'" +
            ", distanceFromEmploymentCenters='" + getDistanceFromEmploymentCenters() + "'" +
            ", accessibilityToHighways='" + getAccessibilityToHighways() + "'" +
            ", taxRate='" + getTaxRate() + "'" +
            ", ptRatio='" + getPtRatio() + "'" +
            ", lStat='" + getLStat() + "'" +
            ", medv='" + getMedv() + "'" +
            ", chas='" + isChas() + "'" +
            ", blacksByTown='" + getBlacksByTown() + "'" +
            "}";
    }

}
