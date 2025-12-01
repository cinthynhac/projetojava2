package controller;
import model.Loja;
import model.LojaContext;
import model.Produto;
import service.LojaService;
import service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class ProdutoController {

    private ProdutoService produtoService;
    private Scanner sc;
    private LojaContext lojaContext;

    //construtores

    public ProdutoController(ProdutoService produtoService, Scanner sc, LojaContext lojaContext) {
        this.produtoService = produtoService;
        this.sc = sc;
        this.lojaContext = lojaContext;
    }


    //metodos

    public void menuProdutos (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        int opcaoprodutos;

        do {
            System.out.println("\n======= OPÇÃO PRODUTOS " + lojaSelecionada.getNomeFantasia() + "=======");
            System.out.println("1. Cadastrar novo produto: ");
            System.out.println("2. Listar produtos. ");
            System.out.println("3. Buscar produtos. ");
            System.out.println("4. Excluir produto.");
            System.out.println("0. Voltar ao Menu de Serviços da Loja.");
            opcaoprodutos = sc.nextInt();
            sc.nextLine();

            switch (opcaoprodutos) {
                case 1:
                    cadastrarNovoProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProdutos();
                    break;
                case 4:
                    excluirProdutos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        }while (opcaoprodutos!= 0);
    }

    public void cadastrarNovoProduto (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        Produto produto = new Produto();
        System.out.println("Digite o nome do produto: ");
        produto.setNomedoproduto(sc.nextLine());

        produto.setIdLoja(lojaSelecionada.getIdLoja());

        System.out.println("Digite o ID do produto: ");
        produto.setIdProduto(Integer.parseInt(sc.nextLine()));

        System.out.println("Digite o valor do seu produto: ");
        produto.setPreco(Float.parseFloat(sc.nextLine()));

        System.out.println("Digite a categoria do seu produto: ");
        produto.setCategoria(sc.nextLine());

        System.out.println("Digite a quantidade em estoque do seu produto: ");
        produto.setQuantidadeEstoque(Integer.parseInt(sc.nextLine()));

        produtoService.cadastrarProduto(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public void listarProdutos (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("\nProdutos da loja " + lojaSelecionada.getNomeFantasia() + ":");

        List<Produto> lista = produtoService.listarProdutosPorLoja(lojaSelecionada.getIdLoja());

        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado para essa loja.");
        } else {
            for (Produto produtos : lista) {
                System.out.println(
                        "Nome: " + produtos.getNomedoproduto() +
                                " | ID Produto: " + produtos.getIdProduto() +
                                " | Preço: " + produtos.getPreco() +
                                " | Categoria: " + produtos.getCategoria() +
                                " | Estoque: " + produtos.getQuantidadeEstoque()
                );
            }
        }

    }

    public void buscarProdutos (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("Digite o ID do produto: ");
        int idProdutoBusca = Integer.parseInt(sc.nextLine());

        Produto encontradoLoja = produtoService.buscarProdutoPorLoja(
                lojaSelecionada.getIdLoja(),
                idProdutoBusca
        );

        if (encontradoLoja != null) {
            System.out.println("Produto encontrado: " + encontradoLoja.getNomedoproduto());
        } else {
            System.out.println("Produto não encontrado nessa loja.");
        }
    }

    public void excluirProdutos (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("\n===== EXCLUSÃO DE PRODUTO =====");

        // Primeiro mostrar os produtos cadastrados na loja
        List<Produto> produtosParaExcluir = produtoService.listarProdutosPorLoja(lojaSelecionada.getIdLoja());

        if (produtosParaExcluir.isEmpty()) {
            System.out.println("Não há Produtos cadastrados na loja " + lojaSelecionada.getNomeFantasia());
        } else {
            System.out.println("Produtos disponíveis para excluir: ");

            for (Produto produtos : produtosParaExcluir) {

                System.out.println(
                        "ID Produto: " + produtos.getIdProduto() +
                                " | Nome: " + produtos.getNomedoproduto() +
                                " | Preço: " + produtos.getPreco() +
                                " | Estoque: " + produtos.getQuantidadeEstoque()
                );
            }

            System.out.println("\nDigite o ID do Produto que deseja excluir: ");
            int idExcluir = Integer.parseInt(sc.nextLine());

            boolean excluiu = produtoService.excluirProduto(lojaSelecionada.getIdLoja(), idExcluir);

            if (excluiu) {
                System.out.println("Produto excluída com sucesso!");
            } else {
                System.out.println("Nenhuma produto encontrado com esse ID.");
            }
        }

        System.out.println();
    }























}
