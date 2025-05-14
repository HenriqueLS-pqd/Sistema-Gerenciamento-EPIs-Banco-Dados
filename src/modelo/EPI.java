package modelo;

public class EPI {
    private int id_epi;
    private String nome;
    private String validade;
    private int quantidade;

    public EPI(String nome, String validade, int quantidade) {
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
    }

    public EPI(int id_epi, String nome, String validade, int quantidade) {
        this.id_epi = id_epi;
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
    }

    public int getId_epi() {
        return id_epi;
    }

    public void setId_epi(int id_epi) {
        this.id_epi = id_epi;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
