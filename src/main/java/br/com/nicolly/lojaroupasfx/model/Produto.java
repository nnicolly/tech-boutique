package br.com.nicolly.lojaroupasfx.model;

public class Produto {
    private int id;
    private String nome;
    private String tamanho;
    private String cor;
    private double preco;
    private int estoque;

    public Produto() {
    }

    public Produto(int id, String nome, String tamanho, String cor, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.cor = cor;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto(String nome, String tamanho, String cor, double preco, int estoque) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.cor = cor;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return nome + " (" + tamanho + ", " + cor + ")";
    }
}
