package org.devvictor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class CompanyRevenue {
    public static void main(String[] args) {
        try {
            Optional<JSONArray> dailyRevenue = extractDailyRevenueArray();

            if (dailyRevenue.isEmpty()) {
                System.out.println("Não foi possível ler o array de faturamentos diários");
                return;
            }

            JSONArray arr = dailyRevenue.get();

            printMonthlyRevenueInfo(arr, getMonthlyRevenueAverage(arr));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Optional<JSONArray> extractDailyRevenueArray() throws IOException {
            Path currentRelativePath = Paths.get("");
            String currentPath = currentRelativePath.toAbsolutePath().toString();
            String content = new String(Files.readAllBytes(Paths.get(currentPath, "revenue.json")));

            JSONObject jsonObject = new JSONObject(content);

            return Optional.of(jsonObject.getJSONArray("dailyRevenue"));
    }

    public static double getMonthlyRevenueAverage(JSONArray dailyRevenue) {
        double sum = 0;
        int length = dailyRevenue.length();

        for (int i = 0; i < length; i++) {
            sum += dailyRevenue.getJSONObject(i).getDouble("revenue");
        }

        return sum / length;
    }

    public static void printMonthlyRevenueInfo(JSONArray dailyRevenue, double monthlyRevenueAverage) {
        double lowerRevenueAmount = dailyRevenue
                .getJSONObject(0).getDouble("revenue");

        double higherRevenueAmount = dailyRevenue
                .getJSONObject(0).getDouble("revenue");

        int daysOfDailyRevenueHigherThanMonthlyAverage = 0;

        for (int i = 0; i < dailyRevenue.length(); i++) {
            JSONObject revenueObject = dailyRevenue.getJSONObject(i);

            double revenueAmount = revenueObject.getDouble("revenue");

            if (revenueAmount != 0 && revenueAmount < lowerRevenueAmount) {
                lowerRevenueAmount = revenueAmount;
            } else if (revenueAmount > higherRevenueAmount) {
                higherRevenueAmount = revenueAmount;
            }

            if (revenueAmount > monthlyRevenueAverage) {
                daysOfDailyRevenueHigherThanMonthlyAverage++;
            }
        }

        System.out.format("Valor do maior faturamento: %.2f %n", higherRevenueAmount);
        System.out.format("Valor do menor faturamento: %.2f %n", lowerRevenueAmount);
        System.out.format("Número de dias no mês em que o valor de faturamento diário foi superior à média mensal: %d",
                daysOfDailyRevenueHigherThanMonthlyAverage);
    }
}
