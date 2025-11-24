package service;

import model.Produto;

import java.util.ArrayList;

public class ProdutoService {
    private ArrayList<Produto> produtos= new ArrayList<>();

    //cadastrar produto
    public void cadastrarProduto(Produto produto){
        produtos.add(produto);
        System.out.println("model.Produto" + produto.getNomedoproduto() + " adicionado com sucesso!");
    }

    //listar produto
    public ArrayList<Produto> listarProdutos(){
        System.out.println("Lista de Produtos: ");
        return produtos;
    }
    // buscar produto por ID
    public Produto buscarProduto(int id){
        for(Produto produto: produtos){
            if(produto.getIdProduto() == id){
                return produto;
            }
        }
        return null;
    }

    //diminuir estoque do produto
    public void diminuirEstoque(int id, int quantidadeEstoque){
        System.out.println("Digite a ID do produto e a quantidade a ser diminuída no estoque: ");
        Produto produto = buscarProduto(id);
        if (produto != null){
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidadeEstoque);
        }
    }

}