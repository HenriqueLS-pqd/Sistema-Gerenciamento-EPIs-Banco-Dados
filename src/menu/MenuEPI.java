package menu;

import dao.EPIDao;
import modelo.EPI;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuEPI {

    public static void exibirMenuEpi() {
        Scanner sc = new Scanner(System.in);
        EPIDao dao = new EPIDao();
        int opcao;

        do

        {
            System.out.println("\n============ Menu EPI ============");
            System.out.println("1. Cadastrar EPI");
            System.out.println("2. Listar EPIs");
            System.out.println("3. Atualizar EPI");
            System.out.println("4. Remover EPI");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("==================================");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do EPI: ");
                    String nome = sc.nextLine();
                    System.out.print("Validade (aaaa-mm-dd): ");
                    String validade = sc.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = sc.nextInt();
                    EPI novoEpi = new EPI(nome, validade, quantidade);
                    dao.inserirEPI(novoEpi);
                }
                case 2 -> {
                    ArrayList<EPI> lista = dao.listarEPIs();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum EPI cadastrado.");
                    } else {
                        System.out.println("\nLista de EPIs:");
                        for (EPI epi : lista) {
                            System.out.println("ID: " + epi.getId_epi() + " | Nome: " + epi.getNome() + " | Validade: " + epi.getValidade() + " | Quantidade: " + epi.getQuantidade());
                        }
                    }
                }
                case 3 -> {
                    ArrayList<EPI> lista = dao.listarEPIs();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum EPI cadastrado.");
                    } else {
                        System.out.println("\nLista de EPIs:");
                        for (EPI epi : lista) {
                            System.out.println("ID: " + epi.getId_epi() + " | Nome: " + epi.getNome() + " | Validade: " + epi.getValidade() + " | Quantidade: " + epi.getQuantidade());
                        }
                        System.out.print("\nID do EPI a atualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome do EPI: ");
                        String nome = sc.nextLine();
                        System.out.print("Nova validade (aaaa-mm-dd): ");
                        String validade = sc.nextLine();
                        System.out.print("Nova quantidade: ");
                        int quantidade = sc.nextInt();
                        EPI epiAtualizado = new EPI(id, nome, validade, quantidade);
                        dao.atualizarEPI(epiAtualizado);
                    }
                }
                case 4 -> {
                    ArrayList<EPI> lista = dao.listarEPIs();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum EPI cadastrado.");
                    } else {
                        System.out.println("\nLista de EPIs:");
                        for (EPI epi : lista) {
                            System.out.println("ID: " + epi.getId_epi() + " | Nome: " + epi.getNome() + " | Validade: " + epi.getValidade() + " | Quantidade: " + epi.getQuantidade());
                        }
                        System.out.print("\nID do EPI a remover: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        dao.excluirEPI(id);
                    }

                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida");
            }
        } while(opcao !=0);
    }

}