package modelo;

public class Usuario {
    public enum Perfil {

        ADMINISTRADOR,
        COLABORADOR
    }

    private int id_usuario;
    private String nome;
    private String email;
    private String senha;
    private Perfil perfil;

    public Usuario(String nome, String email, String senha, Perfil perfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Usuario(int id_usuario, String nome, String email, Perfil perfil, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.senha = senha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
