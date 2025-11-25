import model.Loja;
import model.Produto;
import org.w3c.dom.ls.LSOutput;
import service.ClienteService;
import service.ProdutoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();

        int menu0;
        int opcao;

        do {
            System.out.println("Bem vindo ao seu sistema integrado de Lojas. Escolha uma das opções abaixo: ");
            System.out.println("1.Criar loja.");
            System.out.println("2. Acessar loja.");
            System.out.println("3. Listar as suas lojas.");
            System.out.println("4. Excluir Loja.");
            System.out.println("5. Sair.");
            menu0 = sc.nextInt();
            sc.nextLine();

            switch (menu0) {
                case 1:
                    Loja loja = new Loja();

                    System.out.println("Digite a razão Social da sua loja: ");
                    loja.setRazaoSocial(sc.nextLine());

                    System.out.println("Digite Nome fantasia da sua loja: ");
                    loja.setNomeFantasia(sc.nextLine());

                    System.out.println("Digite o CNPJ da sua loja: ");
                    loja.setCnpj(sc.nextLine());

                    System.out.println("Digite o endereço da sua loja: ");
                    loja.setEndereco(sc.nextLine());

                    System.out.println("Digite o telefone da sua Loja: ");
                    loja.setTelefone(sc.nextLine());

                    System.out.println("Digite o email da sua loja: ");
                    loja.setEmail(sc.nextLine());

                    System.out.println("Digite o Id da sua Loja: ");
                    loja.setIdLoja(Integer.parseInt(sc.nextLine()));

                    lojaService.



            }
        } while (menu0 !=0);




        do{
            System.out.println("Bem-vindo ao seu sistema integrado de lojas. Escolha uma das opções abaixo: ");
            System.out.println("1. Cadastrar novo produto: ");
            System.out.println("2. Listar produtos: ");
            System.out.println("3. Buscar produtos: ");
            System.out.println("0. Sair da aplicação.");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Produto produto = new Produto();
                    System.out.println("Digite o nome do produto: ");
                    produto.setNomedoproduto(sc.nextLine());

                    System.out.println("Digite o ID da loja que você deseja atrelar esse produto: ");
                    produto.setIdLoja(Integer.parseInt(sc.nextLine()));

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

                    break;

                case 2:
                    System.out.println("Digite o ID da loja que você deseja visualizar: ");
                    int lojaListar = Integer.parseInt(sc.nextLine());

                    System.out.println("\nProdutos da loja " + lojaListar + ":");

                    var lista = produtoService.listarProdutosPorLoja(lojaListar);

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
                    break;

                case 3:

                    System.out.println("Digite o ID da loja: ");
                    int lojaBusca = Integer.parseInt(sc.nextLine());

                    System.out.println("Digite o ID do produto: ");
                    int produtoBusca = Integer.parseInt(sc.nextLine());

                    Produto encontradoLoja = produtoService.buscarProdutoPorLoja(lojaBusca, produtoBusca);

                    if (encontradoLoja != null) {
                        System.out.println("Produto encontrado: " + encontradoLoja.getNomedoproduto());
                    } else {
                        System.out.println("Produto não encontrado nessa loja.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;

            }
        } while (opcao!= 0);


    }

}