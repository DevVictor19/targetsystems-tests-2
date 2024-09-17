package org.devvictor;

public class Distributor {
    private final String name;
    private final double monthlyRevenue;

    public Distributor (String name, double monthlyRevenue) {
        this.name = name;
        this.monthlyRevenue = monthlyRevenue;
    }

    public String getName() {
        return name;
    }

    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }
}