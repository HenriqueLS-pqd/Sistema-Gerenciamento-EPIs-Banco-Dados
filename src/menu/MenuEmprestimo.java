package menu;

import dao.EmprestimoDao;
import modelo.Emprestimo;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuEmprestimo {

    public static void exibirMenuEmprestimo() {
        Scanner sc = new Scanner(System.in);
        EmprestimoDao dao = new EmprestimoDao();
        int opcao;

        do {
            System.out.println("\n======== Menu Empréstimo =========");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Listar Empréstimos");
            System.out.println("3. Atualizar Empréstimo");
            System.out.println("4. Remover Empréstimo");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("==================================");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do usuário: ");
                    int id_usuario = sc.nextInt();
                    System.out.print("ID do EPI: ");
                    int id_epi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Data retirada (aaaa-mm-dd hh:mm:ss): ");
                    String data_retirada = sc.nextLine();
                    System.out.print("Data prevista devolução (aaaa-mm-dd hh:mm:ss): ");
                    String data_prevista_retirada = sc.nextLine();
                    System.out.print("Confirmar retirada? (1 = Sim, 0 = Não): ");
                    int confirmacao_retirada = sc.nextInt();
                    Emprestimo novo = new Emprestimo(id_usuario, id_epi, data_retirada, data_prevista_retirada, confirmacao_retirada);
                    dao.inserirEmprestimo(novo);

                }
                case 2 -> {
                    ArrayList<Emprestimo> lista = (ArrayList<Emprestimo>) dao.listarEmprestimos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum empréstimo cadastrado.");
                    } else {
                        System.out.println("\nLista de Empréstimos:");
                        for (Emprestimo e : lista) {
                            System.out.println("ID: " + e.getId_emprestimo() + " | ID Usuário: " + e.getId_usuario() + " | ID EPI: " + e.getId_epi() + " | Retirada: " + e.getData_retirada() + " | Prevista Devolução: " + e.getData_prevista_devolucao() + " | Confirmação Retirada: " + e.getConfirmacao_retirada());
                        }
                    }
                }
                case 3 -> {
                    ArrayList<Emprestimo> lista = (ArrayList<Emprestimo>) dao.listarEmprestimos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum empréstimo registrado.");
                    } else {
                        System.out.println("\nLista de Empréstimos:");
                        for (Emprestimo e : lista) {
                            System.out.println("ID: " + e.getId_emprestimo() + " | ID Usuário: " + e.getId_usuario() + " | ID EPI: " + e.getId_epi() + " | Retirada: " + e.getData_retirada() + " | Prevista Devolução: " + e.getData_prevista_devolucao() + " | Confirmação Retirada: " + e.getConfirmacao_retirada());
                        }
                        System.out.print("\nID do empréstimo a atualizar: ");
                        int id_emprestimo = sc.nextInt();
                        System.out.print("Novo ID do usuário: ");
                        int id_usuario = sc.nextInt();
                        System.out.print("Novo ID do EPI: ");
                        int id_epi = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova data retirada (aaaa-mm-dd hh:mm:ss): ");
                        String data_retirada = sc.nextLine();
                        System.out.print("Nova data prevista devolução (aaaa-mm-dd hh:mm:ss): ");
                        String data_prevista_retirada = sc.nextLine();
                        System.out.print("Confirmar retirada? (1 = Sim, 0 = Não): ");
                        int confirmacao_retirada = sc.nextInt();
                        Emprestimo atualizado = new Emprestimo(id_emprestimo, id_usuario, id_epi, data_retirada, data_prevista_retirada, confirmacao_retirada);
                        dao.atualizarEmprestimo(atualizado);
                    }
                }
                case 4 -> {
                    ArrayList<Emprestimo> lista = (ArrayList<Emprestimo>) dao.listarEmprestimos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum empréstimo cadastrado.");
                    } else {
                        System.out.println("\nLista de Empréstimos:");
                        for (Emprestimo e : lista) {
                            System.out.println("ID: " + e.getId_emprestimo() + " | ID Usuário: " + e.getId_usuario() + " | ID EPI: " + e.getId_epi() + " | Retirada: " + e.getData_retirada() + " | Prevista Devolução: " + e.getData_prevista_devolucao() + " | Confirmação Retirada: " + e.getConfirmacao_retirada());
                        }
                        System.out.print("\nID do empréstimo a remover: ");
                        int id = sc.nextInt();
                        dao.excluirEmprestimo(id);
                    }
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}