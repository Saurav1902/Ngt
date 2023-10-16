package com.nagarro.ngt.entity;

public class LicencePlateResult {
    private String licensePlateText;
    private double confidence;

    public LicencePlateResult(String licensePlateText, double confidence) {
        this.licensePlateText = licensePlateText;
        this.confidence = confidence;
    }

    public String getLicensePlateText() {
        return licensePlateText;
    }

    public double getConfidence() {
        return confidence;
    }
}
