package menu;

import dao.EPIDao;
import modelo.EPI;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
public class MenuEPI {

    public static void exibirMenuEpi() {
        Scanner sc = new Scanner(System.in);
        EPIDao dao = new EPIDao();
        int opcao;

        do {
            System.out.println("\n============= GERENCIAMENTO DE EPIs =============");
            System.out.println("1 - Cadastrar novo EPI");
            System.out.println("2 - Consultar EPIs cadastrados");
            System.out.println("3 - Atualizar dados de um EPI");
            System.out.println("4 - Remover um EPI");
            System.out.println("0 - Retornar ao menu principal");
            System.out.println("=================================================");
            System.out.print("Selecione uma opção: ");
            while (!sc.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o nome do EPI: ");
                    String nome = sc.nextLine();

                    LocalDate validade = null;
                    while (validade == null) {
                        System.out.print("Informe a validade (aaaa-mm-dd): ");
                        String validadeStr = sc.nextLine();

                        try {
                            validade = LocalDate.parse(validadeStr); // faz a validação
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida! Tente novamente no formato aaaa-mm-dd com valores reais.");
                        }
                    }

                    System.out.print("Informe a quantidade: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    EPI novoEpi = new EPI(nome, validade.toString(), quantidade); // ou passe LocalDate se o construtor aceitar
                    dao.cadastrarEPI(novoEpi);
                }


                case 2 -> {
                    ArrayList<EPI> lista = dao.buscarTodosEPIs();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum EPI cadastrado.");
                    } else {
                        System.out.println("\n>>> EPIs Cadastrados:");
                        for (EPI epi : lista) {
                            System.out.printf("ID: %d | Nome: %s | Validade: %s | Quantidade: %d%n",
                                    epi.getId_epi(), epi.getNome(), epi.getValidade(), epi.getQuantidade());
                        }
                    }
                }

                case 3 -> {
                    ArrayList<EPI> lista = dao.buscarTodosEPIs();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum EPI cadastrado.");
                    } else {
                        System.out.println("\n>>> EPIs Cadastrados:");
                        for (EPI epi : lista) {
                            System.out.printf("ID: %d | Nome: %s | Validade: %s | Quantidade: %d%n",
                                    epi.getId_epi(), epi.getNome(), epi.getValidade(), epi.getQuantidade());
                        }

                        System.out.print("\nInforme o ID do EPI a ser atualizado: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome do EPI: ");
                        String nome = sc.nextLine();
                        System.out.print("Nova validade (aaaa-mm-dd): ");
                        String validade = sc.nextLine();
                        System.out.print("Nova quantidade: ");
                        int quantidade = sc.nextInt();

                        EPI epiAtualizado = new EPI(id, nome, validade, quantidade);
                        dao.atualizarDadosEPI(epiAtualizado);
                    }
                }

                case 4 -> {
                    ArrayList<EPI> lista = dao.buscarTodosEPIs();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum EPI cadastrado.");
                    } else {
                        System.out.println("\n>>> EPIs Cadastrados:");
                        for (EPI epi : lista) {
                            System.out.printf("ID: %d | Nome: %s | Validade: %s | Quantidade: %d%n",
                                    epi.getId_epi(), epi.getNome(), epi.getValidade(), epi.getQuantidade());
                        }

                        System.out.print("\nInforme o ID do EPI a ser removido: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        dao.removerEPI(id);
                    }
                }

                case 0 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
