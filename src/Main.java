import model.*;
import service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();
        LojaService lojaService = new LojaService();
        FuncionarioService funcionarioService = new FuncionarioService();
        VendasService vendasService = new VendasService();
        RelatoriosService relatorioService = new RelatoriosService(vendasService, produtoService, clienteService);


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
                case 1: //case1 menu0;
                    Loja loja = new Loja() {
                    };

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

                case 2: //case2 do menu0
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
                        System.out.println("4. Clientes");
                        System.out.println("5. Relatórios.");
                        System.out.println("0. Voltar ao Menu Principal.");
                        opcaoservico = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoservico) {

                            case 1: //case1 do menuSERVIÇOS;
                                int vendasopcao;

                                do {
                                    System.out.println("\n===== OPÇÃO VENDAS " + lojaSelecionada.getNomeFantasia() + "=====");
                                    System.out.println("1. Registrar Venda.");
                                    System.out.println("2. Listar Vendas da Loja " + lojaSelecionada.getNomeFantasia() + ".");
                                    System.out.println("3. Listar Vendas do Cliente.");
                                    System.out.println("4. Buscar vendas por ID da Venda.");
                                    System.out.println("5. Buscar vendas por Cliente.");
                                    System.out.println("6. Exlcuir Venda.");
                                    System.out.println("0. Voltar ao Menu de Serviços da Loja");
                                    vendasopcao = sc.nextInt();
                                    sc.nextLine();

                                    switch (vendasopcao) {
                                        case 1://case1 do menu VENDAS
                                            Vendas vendas = new Vendas();

                                            System.out.println("Parabéns pela sua venda!");
                                            vendas.setIdLoja(lojaSelecionada.getIdLoja());

                                            System.out.println("Digite o CPF do Cliente: ");
                                            String cpf = sc.nextLine();
                                            Cliente clienteSelecionado = clienteService.buscarClientePorCpf(lojaSelecionada.getIdLoja(), cpf);

                                            if (clienteSelecionado == null) {
                                                System.out.println("Cliente não encontrado! Cadastre o cliente antes de registrar a venda.");
                                                break; // volta ao menu de vendas
                                            }

                                            vendas.setCliente(clienteSelecionado);

                                            System.out.println("Digite o CPF do Funcionário responsável: ");
                                            String cpfFunc = sc.nextLine();
                                            Funcionario funcionarioSelecionado = funcionarioService.buscarFuncionarioPorCPF(lojaSelecionada.getIdLoja(), cpfFunc);

                                            if (funcionarioSelecionado == null) {
                                                System.out.println("Funcionário não encontrado!");
                                                break;
                                            }

                                            vendas.setFuncionario(funcionarioSelecionado);

                                            // Criar lista de itens da venda
                                            List<ItemVenda> itens = new ArrayList<>();
                                            float valorTotal = 0;

                                            String continuar;
                                            do {
                                                System.out.println("Digite o ID do Produto:");
                                                int idProduto = sc.nextInt();
                                                sc.nextLine();

                                                Produto produtoSelecionado = produtoService.buscarProdutoPorLoja(lojaSelecionada.getIdLoja(), idProduto);

                                                if (produtoSelecionado == null) {
                                                    System.out.println("Produto não encontrado!");
                                                    break;
                                                }

                                                System.out.println("Digite a quantidade:");
                                                int quantidade = sc.nextInt();
                                                sc.nextLine();

                                                float subtotal = produtoSelecionado.getPreco() * quantidade;

                                                // Criando item de venda
                                                ItemVenda item = new ItemVenda(produtoSelecionado, quantidade, subtotal);
                                                itens.add(item);

                                                valorTotal += subtotal;

                                                System.out.println("Adicionar mais um produto? (S/N)");
                                                continuar = sc.nextLine();

                                            } while (continuar.equalsIgnoreCase("S"));

                                            vendas.setItens(itens);
                                            vendas.setValorTotal(valorTotal);
                                            vendas.setDataVenda(LocalDate.now());

                                            // Registrar venda
                                            vendasService.cadastrarVenda(vendas);

                                            System.out.println("Venda registrada com sucesso!");
                                            System.out.println("Valor total da venda: R$ " + valorTotal);

                                            break;

                                        case 2:

                                            System.out.println("\n===== LISTA DE VENDAS DA LOJA =====");

                                            ArrayList<Vendas> vendasDaLoja = vendasService.listarVendasPorLoja(lojaSelecionada.getIdLoja());

                                            if (vendasDaLoja.isEmpty()) {
                                                System.out.println("Nenhuma venda registrada para esta loja.");
                                            } else {
                                                for (Vendas v : vendasDaLoja) {
                                                    System.out.println("ID Venda: " + v.getIdVenda());
                                                    System.out.println("Cliente: " + v.getCliente().getNome());
                                                    System.out.println("Funcionário: " + v.getFuncionario().getNome());
                                                    System.out.println("Valor Total: R$ " + v.getValorTotal());
                                                    System.out.println("Data: " + v.getDataVenda());
                                                    System.out.println("--------------------------------------");
                                                }
                                            }
                                            break;

                                        case 3:
                                            System.out.println("\nDigite o ID do cliente: ");
                                            int idClienteListar = Integer.parseInt(sc.nextLine());

                                            ArrayList<Vendas> vendasDoCliente = vendasService.listarVendasPorCliente(
                                                    lojaSelecionada.getIdLoja(), idClienteListar);

                                            if (vendasDoCliente.isEmpty()) {
                                                System.out.println("Nenhuma venda encontrada para este cliente.");
                                            } else {
                                                System.out.println("\n===== VENDAS DO CLIENTE =====");
                                                for (Vendas v : vendasDoCliente) {
                                                    System.out.println("ID Venda: " + v.getIdVenda());
                                                    System.out.println("Valor Total: R$ " + v.getValorTotal());
                                                    System.out.println("Data: " + v.getDataVenda());
                                                    System.out.println("--------------------------------------");
                                                }
                                            }
                                            break;

                                        case 4:
                                            System.out.println("Digite o ID da venda para buscar: ");
                                            int idVendaBusca = Integer.parseInt(sc.nextLine());

                                            Vendas vendaEncontrada = vendasService.buscarVendaPorLoja(
                                                    lojaSelecionada.getIdLoja(), idVendaBusca);

                                            if (vendaEncontrada == null) {
                                                System.out.println("Venda não encontrada!");
                                            } else {
                                                System.out.println("\n===== DETALHES DA VENDA =====");
                                                System.out.println("ID Venda: " + vendaEncontrada.getIdVenda());
                                                System.out.println("Cliente: " + vendaEncontrada.getCliente().getNome());
                                                System.out.println("Funcionário: " + vendaEncontrada.getFuncionario().getNome());
                                                System.out.println("Valor Total: R$ " + vendaEncontrada.getValorTotal());
                                                System.out.println("Data: " + vendaEncontrada.getDataVenda());

                                                System.out.println("--- Itens da Venda ---");
                                                for (ItemVenda item : vendaEncontrada.getItens()) {
                                                    System.out.println("Produto: " + item.getProduto().getNomedoproduto());
                                                    System.out.println("Quantidade: " + item.getQuantidade());
                                                    System.out.println("Subtotal: R$ " + item.getSubtotal());
                                                    System.out.println("-----------------------");
                                                }
                                            }
                                            break;

                                        case 5:
                                            System.out.println("Digite o ID do Cliente:");
                                            int idCliBusca = Integer.parseInt(sc.nextLine());

                                            System.out.println("Digite o ID da Venda:");
                                            int idVendaCli = Integer.parseInt(sc.nextLine());

                                            Vendas vendaCliente = vendasService.buscarVendaPorCliente(
                                                    lojaSelecionada.getIdLoja(), idCliBusca, idVendaCli);

                                            if (vendaCliente == null) {
                                                System.out.println("Venda não encontrada para este cliente!");
                                            } else {
                                                System.out.println("\n===== DETALHES DA VENDA (Cliente) =====");
                                                System.out.println("ID Venda: " + vendaCliente.getIdVenda());
                                                System.out.println("Cliente: " + vendaCliente.getCliente().getNome());
                                                System.out.println("Valor Total: R$ " + vendaCliente.getValorTotal());
                                                System.out.println("Data: " + vendaCliente.getDataVenda());

                                                System.out.println("--- Itens da Venda ---");
                                                for (ItemVenda item : vendaCliente.getItens()) {
                                                    System.out.println("Produto: " + item.getProduto().getNomedoproduto());
                                                    System.out.println("Quantidade: " + item.getQuantidade());
                                                    System.out.println("Subtotal: R$ " + item.getSubtotal());
                                                    System.out.println("-----------------------");
                                                }
                                            }
                                            break;

                                        case 6:
                                            System.out.println("Digite o ID da venda para excluir:");
                                            int idVendaExcluir = Integer.parseInt(sc.nextLine());

                                            boolean removida = vendasService.excluirVenda(lojaSelecionada.getIdLoja(), idVendaExcluir);

                                            if (removida) {
                                                System.out.println("Venda excluída com sucesso!");
                                            }
                                            break;

                                        case 0:

                                            System.out.println("Saindo...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;

                                    }

                                } while (vendasopcao != 0);
                                break;

                            case 2:
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
                                        case 1://cadastrar novo produto
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

                                        case 2: // listar produto

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

                                        case 3: //buscar produtos

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

                                        case 4: //excluir produto
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

                                            System.out.println(); // apenas espaçamento
                                            break;

                                        case 0: //voltar para ao menu serviço
                                            System.out.println("Saindo...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;

                                    }
                                } while (opcaoprodutos != 0);

                                break;

                            case 3:
                                int opcaofuncionario;
                                do {
                                    System.out.println("\n======= OPÇÃO FUNCIONÁRIO " + lojaSelecionada.getNomeFantasia() + "=======");
                                    System.out.println("1. Cadastrar Novo Funcionário: ");
                                    System.out.println("2. Excluir Funcionário: ");
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

                                            System.out.println("Digite o ID do funcionário que deseja excluir:");
                                            int idExcluir = Integer.parseInt(sc.nextLine());

                                            boolean excluiu = funcionarioService.excluirfuncionarioPorLoja(
                                                    lojaSelecionada.getIdLoja(),
                                                    idExcluir
                                            );

                                            if (excluiu) {
                                                System.out.println("Funcionário excluído com sucesso!");
                                            }
                                            break;

                                        case 0:
                                            System.out.println("Saindo...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;

                                    }
                                } while (opcaofuncionario != 0);

                                break;


                            case 4: //cadastrar novo cliente
                                int opcaocliente;
                                do {
                                    System.out.println("\n======= OPÇÃO CLIENTES " + lojaSelecionada.getNomeFantasia() + "=======");
                                    System.out.println("1. Cadastrar Cliente");
                                    System.out.println("2. Listar Clientes");
                                    System.out.println("3. Buscar Cliente por CPF");
                                    System.out.println("4. Excluir Cliente");
                                    System.out.println("0. Voltar");
                                    opcaocliente = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcaocliente) {

                                        case 1: // cadastrar cliente
                                            Cliente cliente = new Cliente();

                                            System.out.println("Digite o nome do cliente:");
                                            cliente.setNome(sc.nextLine());

                                            cliente.setIdLoja(lojaSelecionada.getIdLoja());

                                            System.out.println("Digite o CPF:");
                                            cliente.setCpf(sc.nextLine());

                                            System.out.println("Digite o telefone:");
                                            cliente.setTelefone(sc.nextLine());

                                            clienteService.cadastrarCliente(cliente);

                                            System.out.println("Cliente cadastrado com sucesso!");
                                            break;

                                        case 2: // listar clientes
                                            System.out.println("\n===== LISTA DE CLIENTES =====");
                                            ArrayList<Cliente> clientesLoja =
                                                    clienteService.listarClientesPorLoja(lojaSelecionada.getIdLoja());

                                            if (clientesLoja.isEmpty()) {
                                                System.out.println("Nenhum cliente cadastrado.");
                                            } else {
                                                for (Cliente c : clientesLoja) {
                                                    System.out.println(
                                                            "Nome: " + c.getNome() +
                                                                    " | CPF: " + c.getCpf() +
                                                                    " | Telefone: " + c.getTelefone()
                                                    );
                                                }
                                            }
                                            break;

                                        case 3: // buscar cliente
                                            System.out.println("Digite o CPF do cliente:");
                                            String cpfBusca = sc.nextLine();

                                            Cliente encontrado = clienteService.buscarClientePorCpf(
                                                    lojaSelecionada.getIdLoja(), cpfBusca
                                            );

                                            if (encontrado == null) {
                                                System.out.println("Cliente não encontrado!");
                                            } else {
                                                System.out.println("Cliente encontrado: " + encontrado.getNome());
                                            }
                                            break;

                                        case 4: // excluir cliente
                                            System.out.println("Digite o ID do cliente que deseja excluir:");
                                            int idc = Integer.parseInt(sc.nextLine());

                                            boolean clienteRemovido = clienteService.removerClientePorLoja(lojaSelecionada.getIdLoja(), idc);

                                            if (clienteRemovido) {
                                                System.out.println("Cliente excluído com sucesso!");
                                            } else {
                                                System.out.println("Nenhum cliente encontrado com esse CPF.");
                                            }
                                            break;
                                        case 0:
                                            System.out.println("Voltando...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                            break;

                                    }
                                } while (opcaocliente != 0);


                                break;


                            case 5://emitir relatórios

                                int opcRel;

                                do {
                                    System.out.println("\n===== RELATÓRIOS =====");
                                    System.out.println("1. Vendas da loja");
                                    System.out.println("2. Vendas de um cliente");
                                    System.out.println("3. Produtos com estoque baixo");
                                    System.out.println("0. Voltar");
                                    opcRel = sc.nextInt(); sc.nextLine();

                                    switch(opcRel) {
                                        case 1:
                                            relatorioService.relatorioVendasPorLoja(lojaSelecionada.getIdLoja());
                                            break;

                                        case 2:
                                            System.out.println("Digite ID do cliente:");
                                            int idC = sc.nextInt();
                                            relatorioService.relatorioVendasPorCliente(lojaSelecionada.getIdLoja(), idC);
                                            break;

                                        case 3:
                                            System.out.println("Mostrar produtos com estoque <= que qual quantidade?");
                                            int limite = sc.nextInt();
                                            relatorioService.relatorioEstoqueBaixo(lojaSelecionada.getIdLoja(), limite);
                                            break;

                                        case 0:
                                            break;

                                        default:
                                            System.out.println("Opção inválida.");
                                    }
                                } while(opcRel != 0);

                                break;

                            case 0:

                                System.out.println("Saindo...");
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;

                        }
                    } while (opcaoservico != 0);

                    break;

                case 3:
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
        } while (menu0 != 0);
    }
}

