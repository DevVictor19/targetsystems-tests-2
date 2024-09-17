package org.devvictor;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        System.out.println("Insira uma frase:");
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        System.out.println(reverse(text));
    }

    public static String reverse(String text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i--) {
            stringBuilder.append(text.charAt(i));
        }

        return stringBuilder.toString();
    }
}
