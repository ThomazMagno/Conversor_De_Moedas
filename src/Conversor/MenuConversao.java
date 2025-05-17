package Conversor;

import java.util.Scanner;

public class MenuConversao {
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        String[][] opcoes = {
                {"USD", "BRL"},
                {"BRL", "USD"},
                {"EUR", "BRL"},
                {"BRL", "EUR"},
                {"USD", "EUR"},
                {"EUR", "USD"}
        };

        while (true) {
            System.out.println("==== CONVERSOR DE MOEDAS ====");
            for (int i = 0; i < opcoes.length; i++) {
                System.out.printf("%d - %s -> %s\n", i + 1, opcoes[i][0], opcoes[i][1]);
            }
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            if (escolha == 0) {
                System.out.println("Saindo...");
                break;
            }

            if (escolha < 1 || escolha > opcoes.length) {
                System.out.println("Opção inválida.\n");
                continue;
            }

            String from = opcoes[escolha - 1][0];
            String to = opcoes[escolha - 1][1];

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            double resultado = ConversorDeMoedas.converter(from, to, valor);
            if (resultado != -1) {
                System.out.printf("%.2f %s = %.2f %s\n\n", valor, from, resultado, to);
            } else {
                System.out.println("Erro ao obter taxa de câmbio.\n");
            }
        }

        scanner.close();
    }
}
