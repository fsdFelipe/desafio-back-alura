import utils.CurrencyConverter;
import utils.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                int option = Menu.showMenu(reader);

                if (option == 0) {
                    System.out.println("Encerrando o programa...");
                    break;
                }

                System.out.print("Digite o valor a ser convertido: ");
                double amount = Double.parseDouble(reader.readLine());

                String sourceCurrency, targetCurrency;
                switch (option) {
                    case 1:
                        sourceCurrency = "USD";
                        targetCurrency = "EUR";
                        break;
                    case 2:
                        sourceCurrency = "EUR";
                        targetCurrency = "USD";
                        break;
                    case 3:
                        sourceCurrency = "USD";
                        targetCurrency = "GBP";
                        break;
                    case 4:
                        sourceCurrency = "GBP";
                        targetCurrency = "USD";
                        break;
                    case 5:
                        sourceCurrency = "EUR";
                        targetCurrency = "GBP";
                        break;
                    case 6:
                        sourceCurrency = "GBP";
                        targetCurrency = "EUR";
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        continue;
                }

                double convertedAmount = CurrencyConverter.convertCurrency(amount, sourceCurrency, targetCurrency);
                System.out.println(amount + " " + sourceCurrency + " = " + convertedAmount + " " + targetCurrency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}