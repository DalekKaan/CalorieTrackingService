package ru.r1b.calorietrackingservice.http.scheme.personstatistics;

public final class DailyReport {
    private double caloriesReceived;
    private int eating;

    public double getCaloriesReceived() {
        return caloriesReceived;
    }

    public void setCaloriesReceived(double caloriesReceived) {
        this.caloriesReceived = caloriesReceived;
    }

    public int getEating() {
        return eating;
    }

    public void setEating(int eating) {
        this.eating = eating;
    }
}
