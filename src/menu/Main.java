package menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n========== SISTEMA DE GESTÃO ==========");
            System.out.println("1 - Acessar gerenciamento de usuários");
            System.out.println("2 - Acessar gerenciamento de EPIs");
            System.out.println("3 - Acessar gerenciamento de empréstimos");
            System.out.println("4 - Acessar gerenciamento de devoluções");
            System.out.println("0 - Encerrar o sistema");
            System.out.println("========================================");
            System.out.print("Digite a opção desejada: ");

            // Verificação básica para evitar exceções
            while (!sc.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1 -> MenuUsuario.exibirMenuUsuario();
                case 2 -> MenuEPI.exibirMenuEpi();
                case 3 -> MenuEmprestimo.exibirMenuEmprestimo();
                case 4 -> MenuDevolucao.exibirMenuDevolucao();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}
