package Conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorDeMoedas {
    private static final String API_KEY = "8d9db34ecef25de6b74d91f4";

    public static double converter(String from, String to, double amount) {
        try {
            String urlStr = String.format(
                    "https://v6.exchangerate-api.com/v6/%s/latest/%s",
                    API_KEY, from
            );

            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            StringBuilder respostaJson = new StringBuilder();
            while (scanner.hasNext()) {
                respostaJson.append(scanner.nextLine());
            }
            scanner.close();

            JsonObject jsonObject = JsonParser.parseString(respostaJson.toString()).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            double taxa = rates.get(to).getAsDouble();
            return amount * taxa;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return -1;
        }
    }
}
