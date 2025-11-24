package service;
import model.Produto;

import java.util.ArrayList;

public class ProdutoService {
    private ArrayList<Produto> produtos= new ArrayList<>();

    //cadastrar produto
    public void cadastrarProduto(Produto produto){
        produtos.add(produto);
        }

    //listar produto

    public ArrayList<Produto> listarProdutosPorLoja(int idLoja) {
        ArrayList<Produto> produtosDaLoja = new ArrayList<>();

        for (Produto p : produtos) {
            if (p.getIdLoja() == idLoja) {
                produtosDaLoja.add(p);
            }
        }
        return produtosDaLoja;
    }

    // buscar produto por loja usando ID

    public Produto buscarProdutoPorLoja(int idLoja, int idProduto) {
        for (Produto p : produtos) {
            if (p.getIdLoja() == idLoja && p.getIdProduto() == idProduto) {
                return p;
            }
        }
        return null;
    }

    //diminuir estoque do produto
    public boolean diminuirEstoque(int idLoja, int idProduto, int quantidade) {
        Produto produto = buscarProdutoPorLoja(idLoja, idProduto);

        if (produto != null) {
            if (produto.getQuantidadeEstoque() >= quantidade) {
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
                return true;
            } else {
                System.out.println("Estoque insuficiente!");
                return false;
            }
        }

        System.out.println("Produto não encontrado!");
        return false;
    }

    //aumentar estoque do produto

    public boolean aumentarEstoque(int idLoja, int idProduto, int quantidade) {
        Produto produto = buscarProdutoPorLoja(idLoja, idProduto);

        if (produto != null) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
            return true;
        }

        System.out.println("Produto não encontrado!");
        return false;
    }

    //excluir produto

    public boolean excluirProduto(int idLoja, int idProduto) {
        Produto produto = buscarProdutoPorLoja(idLoja, idProduto);

        if (produto != null) {
            produtos.remove(produto);
            return true;
        }

        System.out.println("Produto não encontrado!");
        return false;
    }


}