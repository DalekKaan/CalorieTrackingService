package ru.r1b.calorietrackingservice.http.scheme.personstatistics;

public final class DailyReport {
    // для ревью:
    // использовал именно класс вместо record в качестве DTO так как с ним нативно работает BeanPropertyRowMapper из-за
    // чего внешний код выглядит лаконичнее, но в целом считаю обе структуры пригодными для использования как DTO
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
