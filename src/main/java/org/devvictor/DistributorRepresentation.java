package org.devvictor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributorRepresentation {
    public static void main(String[] args) {
        Distributor distributor1 = new Distributor("SP", 67836.43);
        Distributor distributor2 = new Distributor("RJ", 36678.66);
        Distributor distributor3 = new Distributor("MG", 29229.88);
        Distributor distributor4 = new Distributor("ES", 27165.48);
        Distributor distributor5 = new Distributor("Outros", 19849.53);

        List<Distributor> distributors = new ArrayList<>(
                Arrays.asList(distributor1, distributor2, distributor3, distributor4, distributor5)
        );

        printRepresentationPercentage(distributors);
    }

    public static void printRepresentationPercentage(List<Distributor> distributors) {
        double totalRevenue = 0;

        for (int i = 0; i < distributors.size(); i++) {
            totalRevenue += distributors.get(i).getMonthlyRevenue();
        }

        for (int i = 0; i < distributors.size(); i++) {
            Distributor distributor = distributors.get(i);
            double perc = 100 * distributor.getMonthlyRevenue() / totalRevenue;
            System.out.format("Percentual de representação de %s | percentual: %.2f | arrecadação: R$ %.2f %n",
                    distributor.getName(), perc, distributor.getMonthlyRevenue());
        }
    }
}
