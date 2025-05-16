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
            System.out.println("\n========== GERENCIAMENTO DE EMPRÉSTIMOS ==========");
            System.out.println("1 - Registrar novo empréstimo");
            System.out.println("2 - Consultar empréstimos cadastrados");
            System.out.println("3 - Atualizar informações de um empréstimo");

            System.out.println("0 - Retornar ao menu principal");
            System.out.println("===================================================");
            System.out.print("Selecione uma opção: ");
            while (!sc.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o ID do usuário: ");
                    int id_usuario = sc.nextInt();
                    System.out.print("Informe o ID do EPI: ");
                    int id_epi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Data da retirada (aaaa-mm-dd hh:mm:ss): ");
                    String data_retirada = sc.nextLine();
                    System.out.print("Data prevista para devolução (aaaa-mm-dd hh:mm:ss): ");
                    String data_prevista = sc.nextLine();
                    System.out.print("Confirmar retirada? (1 = Sim, 0 = Não): ");
                    int confirmacao = sc.nextInt();

                    Emprestimo novo = new Emprestimo(id_usuario, id_epi, data_retirada, data_prevista, confirmacao);
                    dao.cadastrarEmprestimo(novo);
                }

                case 2 -> {
                    ArrayList<Emprestimo> lista = (ArrayList<Emprestimo>) dao.buscarTodosEmprestimos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum empréstimo cadastrado.");
                    } else {
                        System.out.println("\n>>> Empréstimos Cadastrados:");
                        for (Emprestimo e : lista) {
                            System.out.printf("ID: %d | Usuário: %d | EPI: %d | Retirada: %s | Prevista Devolução: %s | Confirmado: %s%n",
                                    e.getId_emprestimo(), e.getId_usuario(), e.getId_epi(), e.getData_retirada(), e.getData_prevista_devolucao(),
                                    e.getConfirmacao_retirada() == 1 ? "Sim" : "Não");
                        }
                    }
                }

                case 3 -> {
                    ArrayList<Emprestimo> lista = (ArrayList<Emprestimo>) dao.buscarTodosEmprestimos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum empréstimo registrado.");
                    } else {
                        System.out.println("\n>>> Empréstimos Cadastrados:");
                        for (Emprestimo e : lista) {
                            System.out.printf("ID: %d | Usuário: %d | EPI: %d | Retirada: %s | Prevista Devolução: %s | Confirmado: %s%n",
                                    e.getId_emprestimo(), e.getId_usuario(), e.getId_epi(), e.getData_retirada(), e.getData_prevista_devolucao(),
                                    e.getConfirmacao_retirada() == 1 ? "Sim" : "Não");
                        }

                        System.out.print("\nInforme o ID do empréstimo a ser atualizado: ");
                        int id = sc.nextInt();
                        System.out.print("Novo ID do usuário: ");
                        int id_usuario = sc.nextInt();
                        System.out.print("Novo ID do EPI: ");
                        int id_epi = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova data da retirada (aaaa-mm-dd hh:mm:ss): ");
                        String data_retirada = sc.nextLine();
                        System.out.print("Nova data prevista para devolução (aaaa-mm-dd hh:mm:ss): ");
                        String data_prevista = sc.nextLine();
                        System.out.print("Confirmar retirada? (1 = Sim, 0 = Não): ");
                        int confirmacao = sc.nextInt();

                        Emprestimo atualizado = new Emprestimo(id, id_usuario, id_epi, data_retirada, data_prevista, confirmacao);
                        dao.atualizarEmprestimo(atualizado);
                    }
                }


                case 0 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }
}
