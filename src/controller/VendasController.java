package controller;
import model.*;
import service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendasController {

    //atributos

    private VendasService vendasService;
    private ClienteService clienteService;
    private FuncionarioService funcionarioService;
    private ProdutoService produtoService;
    private LojaService lojaService;
    private Scanner sc;
    private LojaContext lojaContext;

    //construtores

    public VendasController(VendasService vendasService, ClienteService clienteService, FuncionarioService funcionarioService, ProdutoService produtoService, LojaService lojaService, Scanner sc, LojaContext lojaContext) {
        this.vendasService = vendasService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.produtoService = produtoService;
        this.lojaService = lojaService;
        this.sc = sc;
        this.lojaContext = lojaContext;
    }


    //métodos

    public void menuvendas(){

        Loja lojaSelecionada = lojaContext.getLojaAtual();

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
                case 1:
                    registrarVendas();
                    break;
                case 2:
                    listarVendas();
                    break;
                case 3:
                    listarVendasPorCliente();
                    break;
                case 4:
                    buscarVendasPorIdVenda();
                    break;
                case 5:
                    buscarVendasPorCliente();
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;

                 default:
                     System.out.println("Opção inválida.");
                     break;
            }

        } while (vendasopcao !=0);
    }

    //registrar venda:
    public void registrarVendas(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        Vendas vendas = new Vendas();

        System.out.println("Parabéns pela sua venda!");
        vendas.setIdLoja(lojaSelecionada.getIdLoja());

        System.out.println("Digite o CPF do Cliente: ");
        String cpf = sc.nextLine();
        Cliente clienteSelecionado = clienteService.buscarClientePorCpf(lojaSelecionada.getIdLoja(), cpf);

        if (clienteSelecionado == null) {
            System.out.println("Cliente não encontrado! Cadastre o cliente antes de registrar a venda.");
            // volta ao menu de vendas
        }

        vendas.setCliente(clienteSelecionado);

        System.out.println("Digite o CPF do Funcionário responsável: ");
        String cpfFunc = sc.nextLine();
        Funcionario funcionarioSelecionado = funcionarioService.buscarFuncionarioPorCPF(lojaSelecionada.getIdLoja(), cpfFunc);

        if (funcionarioSelecionado == null) {
            System.out.println("Funcionário não encontrado!");
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
    }

    public void listarVendas(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
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
    }

    public void listarVendasPorCliente(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
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
    }

    public void buscarVendasPorIdVenda(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
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
    }

    public void buscarVendasPorCliente (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
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
    }

    public void excluirVenda(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("Digite o ID da venda para excluir:");
        int idVendaExcluir = Integer.parseInt(sc.nextLine());

        boolean removida = vendasService.excluirVenda(lojaSelecionada.getIdLoja(), idVendaExcluir);

        if (removida) {
            System.out.println("Venda excluída com sucesso!");
        }
    }



















}
