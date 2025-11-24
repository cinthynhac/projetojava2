package model;

public class Produto {
    //atributos

    private String nomedoproduto;
    private int idLoja;
    private int idProduto;
    private float preco;
    private String categoria;
    private int quantidadeEstoque;

    //construtores

    public Produto() {}

    public Produto(String nomedoproduto, int idLoja, int idProduto, float preco, String categoria, int quantidadeEstoque) {
        this.nomedoproduto = nomedoproduto;
        this.idLoja = idLoja;
        this.idProduto = idProduto;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    //getters and setters

    public String getNomedoproduto() {
        return nomedoproduto;
    }

    public void setNomedoproduto(String nomedoproduto) {
        this.nomedoproduto = nomedoproduto;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
