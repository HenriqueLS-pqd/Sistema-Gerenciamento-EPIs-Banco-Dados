package menu;

import dao.UsuarioDao;
import modelo.Usuario;
import modelo.Usuario.Perfil;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuUsuario {

    public static void exibirMenuUsuario() {
        Scanner sc = new Scanner(System.in);
        UsuarioDao dao = new UsuarioDao();
        int opcao;

        do {
            System.out.println("\n=============== GERENCIAMENTO DE USUÁRIOS ===============");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Listar usuários cadastrados");
            System.out.println("3 - Atualizar dados de um usuário");
            System.out.println("4 - Remover um usuário");
            System.out.println("0 - Retornar ao menu principal");
            System.out.println("==========================================================");
            System.out.print("Selecione uma opção: ");
            while (!sc.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome completo: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine().toLowerCase();;
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Perfil (administrador ou colaborador): ");
                    String perfilStr = sc.nextLine().toUpperCase();

                    try {
                        Perfil perfil = Perfil.valueOf(perfilStr);
                        Usuario novo = new Usuario(nome, email, senha, perfil);
                        dao.cadastrarUsuario(novo);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Perfil inválido. Use 'administrador' ou 'colaborador'.");
                    }
                }

                case 2 -> {
                    ArrayList<Usuario> lista = dao.buscarTodosUsuarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\n>>> Usuários Cadastrados:");
                        for (Usuario u : lista) {
                            System.out.printf("ID: %d | Nome: %s | Email: %s | Perfil: %s%n",
                                    u.getId_usuario(), u.getNome(), u.getEmail(), u.getPerfil());
                        }
                    }
                }

                case 3 -> {
                    ArrayList<Usuario> lista = dao.buscarTodosUsuarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\n>>> Usuários Cadastrados:");
                        for (Usuario u : lista) {
                            System.out.printf("ID: %d | Nome: %s | Email: %s | Perfil: %s%n",
                                    u.getId_usuario(), u.getNome(), u.getEmail(), u.getPerfil());
                        }

                        System.out.print("\nInforme o ID do usuário a ser atualizado: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Novo email: ");
                        String email = sc.nextLine();
                        System.out.print("Nova senha: ");
                        String senha = sc.nextLine();
                        System.out.print("Novo perfil (administrador ou colaborador): ");
                        String perfilStr = sc.nextLine().toUpperCase();

                        try {
                            Perfil perfil = Perfil.valueOf(perfilStr);
                            Usuario atualizado = new Usuario(id, nome, email, perfil, senha);
                            dao.atualizarDadosUsuario(atualizado);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Perfil inválido. Use 'administrador' ou 'colaborador'.");
                        }
                    }
                }

                case 4 -> {
                    ArrayList<Usuario> lista = dao.buscarTodosUsuarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\n>>> Usuários Cadastrados:");
                        for (Usuario u : lista) {
                            System.out.printf("ID: %d | Nome: %s | Email: %s | Perfil: %s%n",
                                    u.getId_usuario(), u.getNome(), u.getEmail(), u.getPerfil());
                        }

                        System.out.print("\nInforme o ID do usuário a ser removido: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        dao.removerUsuario(id);
                    }
                }

                case 0 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
