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
            System.out.println("\n=========== Menu Usuário ===========");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Remover usuário");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("====================================");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Perfil (administrador ou colaborador): ");
                    String perfilStr = sc.nextLine().toUpperCase();

                    Perfil perfil = Perfil.valueOf(perfilStr);

                    Usuario novo = new Usuario(nome, email, senha, perfil);
                    dao.inserirUsuario(novo);
                }
                case 2 -> {
                    ArrayList<Usuario> lista = dao.listarUsuarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\nLista de Usuários:");
                        for (Usuario u : lista) {
                            System.out.println("ID: " + u.getId_usuario() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail() + " | Perfil: " + u.getPerfil());
                        }
                    }
                }
                case 3 -> {
                    ArrayList<Usuario> lista = dao.listarUsuarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\nLista de Usuários:");
                        for (Usuario u : lista) {
                            System.out.println("ID: " + u.getId_usuario() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail() + " | Perfil: " + u.getPerfil());
                        }
                        System.out.print("\nID do usuário a atualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Novo email: ");
                        String email = sc.nextLine();
                        System.out.print("Nova senha: ");
                        String senha = sc.nextLine();
                        System.out.print("Novo perfil (administrador ou colaborador): ");
                        String perfilStr2 = sc.nextLine().toUpperCase();
                        Perfil perfil2 = Perfil.valueOf(perfilStr2);
                        Usuario atualizado = new Usuario(id, nome, email, perfil2, senha);
                        dao.atualizarUsuario(atualizado);
                    }
                }
                case 4 -> {
                    ArrayList<Usuario> lista = dao.listarUsuarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("\nLista de Usuários:");
                        for (Usuario u : lista) {
                            System.out.println("ID: " + u.getId_usuario() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail() + " | Perfil: " + u.getPerfil());
                        }
                        System.out.print("ID do usuário a remover: ");
                        int id = sc.nextInt();



                            dao.excluirUsuario(id);


                    }

                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}

