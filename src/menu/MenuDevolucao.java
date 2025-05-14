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
            System.out.println("\n========= Menu Devolução =========");
            System.out.println("1. Registrar Devolução");
            System.out.println("2. Listar Devoluções");
            System.out.println("3. Atualizar Devolução");
            System.out.println("4. Remover Devolução");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("==================================");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do empréstimo: ");
                    int idEmprestimo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Data da devolução (aaaa-mm-dd hh:mm:ss): ");
                    String dataDevolucao = sc.nextLine();
                    Devolucao nova = new Devolucao(idEmprestimo, dataDevolucao);
                    dao.inserirDevolucao(nova);
                }
                case 2 -> {
                    ArrayList<Devolucao> lista = (ArrayList<Devolucao>) dao.listarDevolucoes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma devolução registrada.");
                    } else {
                        System.out.println("\nLista de Devoluções:");
                        for (Devolucao d : lista) {
                            System.out.println("ID: " + d.getId_devolucao() + " | ID Empréstimo: " + d.getId_emprestimo() + " | Data de Devolução: " + d.getData_devolucao());
                        }
                    }
                }
                case 3 -> {
                    ArrayList<Devolucao> lista = (ArrayList<Devolucao>) dao.listarDevolucoes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma devolução registrada.");
                    } else {
                        System.out.println("\nLista de Devoluções:");
                        for (Devolucao d : lista) {
                            System.out.println("ID: " + d.getId_devolucao() + " | ID Empréstimo: " + d.getId_emprestimo() + " | Data de Devolução: " + d.getData_devolucao()
                            );
                        }
                        System.out.print("\nID da devolução a atualizar: ");
                        int idDevolucao = sc.nextInt();
                        System.out.print("Novo ID do empréstimo: ");
                        int idEmprestimoNew = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova data da devolução (aaaa-mm-dd hh:mm:ss): ");
                        String dataDevolucaoNew = sc.nextLine();
                        Devolucao atualizada = new Devolucao(idDevolucao, idEmprestimoNew, dataDevolucaoNew);
                        dao.atualizarDevolucao(atualizada);
                    }
                }
                case 4 -> {
                    ArrayList<Devolucao> lista = (ArrayList<Devolucao>) dao.listarDevolucoes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma devolução registrada.");
                    } else {
                        System.out.println("\nLista de Devoluções:");
                        for (Devolucao d : lista) {
                            System.out.println("ID: " + d.getId_devolucao() + " | ID Empréstimo: " + d.getId_emprestimo() + " | Data da Devolução: " + d.getData_devolucao()
                            );
                        }
                        System.out.print("\nID da devolução a remover: ");
                        int id = sc.nextInt();
                        dao.excluirDevolucao(id);
                    }
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}

