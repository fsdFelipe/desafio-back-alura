package utils;

import java.io.BufferedReader;
import java.io.IOException;

public class Menu {
    public static int showMenu(BufferedReader reader) throws IOException {
        System.out.println("********************************");
        System.out.println("Selecione a conversão de moeda:");
        System.out.println("1. USD para EUR");
        System.out.println("2. EUR para USD");
        System.out.println("3. USD para GBP");
        System.out.println("4. GBP para USD");
        System.out.println("5. EUR para GBP");
        System.out.println("6. GBP para EUR");
        System.out.println("0. Sair");
        System.out.println("********************************");
        System.out.print("Opção: ");

        return Integer.parseInt(reader.readLine());
    }
}
