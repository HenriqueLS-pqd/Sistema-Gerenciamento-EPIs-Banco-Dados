package menu;

import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n========= Menu Principal =========");
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar EPIs");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("4. Gerenciar Devoluções");
            System.out.println("0. Sair");
            System.out.println("==================================");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1 -> MenuUsuario.exibirMenuUsuario();
                case 2 -> MenuEPI.exibirMenuEpi();
                case 3 -> MenuEmprestimo.exibirMenuEmprestimo();
                case 4 -> MenuDevolucao.exibirMenuDevolucao();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 0);

        sc.close();
    }
}
