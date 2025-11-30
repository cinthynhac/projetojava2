import model.Funcionario;
import model.Loja;
import model.Produto;
import service.ClienteService;
import service.FuncionarioService;
import service.LojaService;
import service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();
        LojaService lojaService = new LojaService();
        FuncionarioService funcionarioService = new FuncionarioService();

        int menu0;

        do {
            System.out.println("Bem vindo ao seu sistema integrado de Lojas. Escolha uma das opções abaixo: ");
            System.out.println("1. Criar loja.");
            System.out.println("2. Acessar Serviços.");
            System.out.println("3. Listar as suas lojas.");
            System.out.println("4. Excluir Loja.");
            System.out.println("0. Sair do Sistema.");
            menu0 = sc.nextInt();
            sc.nextLine();

            switch (menu0) {
                case 1:
                    Loja loja = new Loja() {};

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

                    lojaService.cadastrarLoja(loja);

                    System.out.println("Loja cadastrada com sucesso!");

                    break;

                case 2 :
                    System.out.println("Digite o ID da loja que deseja acessar: ");
                    int idLojaServicos = Integer.parseInt(sc.nextLine());

                    Loja lojaSelecionada = lojaService.buscarLojaPorId(idLojaServicos);

                    if (lojaSelecionada == null) {
                        System.out.println("Loja não encontrada.");
                        break;
                    }
                    int opcaoservico;

                    do {
                        System.out.println("\n===== SERVIÇOS DA LOJA " + lojaSelecionada.getNomeFantasia() + " =====");
                        System.out.println("1. Vendas.");
                        System.out.println("2. Produtos.");
                        System.out.println("3. Funcionário.");
                        System.out.println("4. Marketing.");
                        System.out.println("5. Relatórios.");
                        System.out.println("0. Voltar ao Menu Principal.");
                        opcaoservico = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoservico) {

                            case 1:

                                break;

                            case 2:
                                int opcao;

                                do{
                                    System.out.println("\n======= OPÇÃO PRODUTOS " + lojaSelecionada.getNomeFantasia()+ "=======");
                                    System.out.println("1. Cadastrar novo produto: ");
                                    System.out.println("2. Listar produtos: ");
                                    System.out.println("3. Buscar produtos: ");
                                    System.out.println("0. Voltar ao Menu de Serviços da Loja.");
                                    opcao = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcao) {
                                        case 1:
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

                                            break;

                                        case 2:

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
                                            break;

                                        case 3:

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
                                            break;

                                        case 0:
                                            System.out.println("Saindo...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;

                                    }
                                } while (opcao!= 0);

                                break;

                            case 3:

                                int opcaofuncionario;

                                do{
                                    System.out.println("\n======= OPÇÃO FUNCIONÁRIO " + lojaSelecionada.getNomeFantasia()+ "=======");
                                    System.out.println("1. Cadastrar Novo Funcionário: ");
                                    System.out.println("2. Listar Funcionários: ");
                                    System.out.println("3. Alterar Funcionário");
                                    System.out.println("4. Excluir Funcionário: ");
                                    System.out.println("0. Voltar ao Menu de Serviços da Loja.");
                                    opcaofuncionario = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcaofuncionario) {
                                        case 1:
                                            Funcionario funcionario = new Funcionario();
                                            System.out.println("Digite o nome do Funcionário: ");
                                            funcionario.setNome(sc.nextLine());

                                            funcionario.setIdLoja(lojaSelecionada.getIdLoja());

                                            System.out.println("Digite o ID do Funcionário : ");
                                            funcionario.setIdFuncionario(Integer.parseInt(sc.nextLine()));

                                            System.out.println("Digite o CPF do Funcionário: ");
                                            funcionario.setCpf(sc.nextLine());

                                            System.out.println("Digite a Data de Nascimento do Funcionário: ");
                                            funcionario.setDataNascimento(sc.nextLine());

                                            System.out.println("Digite o Telefone do Funcionário: ");
                                            funcionario.setTelefone(sc.nextLine());

                                            System.out.println("Digite o e-mail do Funcionário: ");
                                            funcionario.setEmail(sc.nextLine());

                                            System.out.println("Digite o Endereço do Funcionário: ");
                                            funcionario.setEndereco(sc.nextLine());

                                            funcionarioService.cadastrarFuncionario(funcionario);

                                            System.out.println("Funcionário Cadastrado com Sucesso!");

                                            break;

                                        case 2:

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
                                            break;

                                        case 3:

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
                                            break;

                                        case 0:
                                            System.out.println("Saindo...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;

                                    }
                                } while (opcaofuncionario!= 0);

                                break;



                            case 4:



                            case 5:



                            case 0:



                        }

                    } while (opcaoservico != 0);
                    break;

                case 3 :
                    System.out.println("\n===== LISTA DE LOJAS CADASTRADAS =====");

                    List<Loja> listaLojas = lojaService.getLojas();

                    if (listaLojas.isEmpty()) {
                        System.out.println("Nenhuma loja cadastrada ainda.");
                    } else {
                        for (Loja l : listaLojas) {
                            System.out.println(
                                    "ID Loja: " + l.getIdLoja() +
                                            " | Razão Social: " + l.getRazaoSocial() +
                                            " | Nome Fantasia: " + l.getNomeFantasia() +
                                            " | CNPJ: " + l.getCnpj() +
                                            " | Telefone: " + l.getTelefone()
                            );
                        }
                    }

                    System.out.println();
                    break;

                case 4:
                    System.out.println("\n===== EXCLUSÃO DE LOJA =====");

                    // Primeiro mostra as lojas cadastradas
                    List<Loja> lojasParaExcluir = lojaService.getLojas();

                    if (lojasParaExcluir.isEmpty()) {
                        System.out.println("Não há lojas cadastradas para excluir.");
                    } else {

                        for (Loja l : lojasParaExcluir) {
                            System.out.println(
                                    "ID Loja: " + l.getIdLoja() +
                                            " | Nome Fantasia: " + l.getNomeFantasia()
                            );
                        }

                        System.out.println("\nDigite o ID da loja que deseja excluir: ");
                        int idExcluir = Integer.parseInt(sc.nextLine());

                        boolean excluiu = lojaService.excluirLoja(idExcluir);

                        if (excluiu) {
                            System.out.println("Loja excluída com sucesso!");
                        } else {
                            System.out.println("Nenhuma loja encontrada com esse ID.");
                        }
                    }

                    System.out.println(); // apenas espaçamento
                    break;

                case 0:
                    System.out.println("Encerrando o sistema... Até logo!");
                    break;


            }
        } while (menu0 !=0);


    }

}