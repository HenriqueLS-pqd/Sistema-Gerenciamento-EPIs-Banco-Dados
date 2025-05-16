package menu;

import dao.DevolucaoDao;
import modelo.Devolucao;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuDevolucao {

    public static void exibirMenuDevolucao() {
        Scanner sc = new Scanner(System.in);
        DevolucaoDao dao = new DevolucaoDao();
        int opcao;

        do {
            System.out.println("\n========= GERENCIAMENTO DE DEVOLUÇÕES =========");
            System.out.println("1 - Registrar nova devolução");
            System.out.println("2 - Consultar devoluções registradas");
            System.out.println("3 - Atualizar uma devolução");
            System.out.println("0 - Retornar ao menu principal");
            System.out.println("===============================================");
            System.out.print("Selecione uma opção: ");
            while (!sc.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o ID do empréstimo: ");
                    int idEmprestimo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Informe a data da devolução (aaaa-mm-dd hh:mm:ss): ");
                    String dataDevolucao = sc.nextLine();
                    Devolucao nova = new Devolucao(idEmprestimo, dataDevolucao);
                    dao.adicionarDevolucao(nova);
                }
                case 2 -> {
                    ArrayList<Devolucao> lista = (ArrayList<Devolucao>) dao.buscarTodasDevolucoes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma devolução cadastrada.");
                    } else {
                        System.out.println("\n>>> Devoluções Registradas:");
                        for (Devolucao d : lista) {
                            System.out.println("ID: " + d.getId_devolucao() + " | ID Empréstimo: " + d.getId_emprestimo() + " | Data: " + d.getData_devolucao());
                        }
                    }
                }
                case 3 -> {
                    ArrayList<Devolucao> lista = (ArrayList<Devolucao>) dao.buscarTodasDevolucoes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma devolução cadastrada.");
                    } else {
                        System.out.println("\n>>> Devoluções Registradas:");
                        for (Devolucao d : lista) {
                            System.out.println("ID: " + d.getId_devolucao() + " | ID Empréstimo: " + d.getId_emprestimo() + " | Data: " + d.getData_devolucao());
                        }
                        System.out.print("\nInforme o ID da devolução a ser atualizada: ");
                        int idDevolucao = sc.nextInt();
                        System.out.print("Novo ID do empréstimo: ");
                        int idEmprestimoNovo = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova data da devolução (aaaa-mm-dd hh:mm:ss): ");
                        String novaData = sc.nextLine();
                        Devolucao atualizada = new Devolucao(idDevolucao, idEmprestimoNovo, novaData);
                        dao.editarDevolucao(atualizada);
                    }
                }

                case 0 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
