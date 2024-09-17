package org.devvictor;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número para saber se ele existe na sequência Fibonacci:");

        int number = scanner.nextInt();

        Boolean numberExistsInFib = isIn(number);

        if (numberExistsInFib) {
            System.out.format("O número %d existe na sequência Fibonacci", number);
            return;
        }

        System.out.format("O número %d não existe na sequência Fibonacci", number);
    }

    public static boolean isIn(int number) {
        int sum = 0;
        int first = 0;
        int last = 1;

        while (sum < number) {
            sum = first + last;
            first = last;
            last = sum;
        }

        return sum == number;
    }
}