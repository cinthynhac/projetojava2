package controller;
import model.Loja;
import model.LojaContext;
import service.LojaService;
import service.RelatoriosService;

import java.util.Scanner;

public class AcessarServicosController {

    private RelatoriosService relatorioService;
    private Scanner sc;
    private LojaService lojaService;
    private VendasController vendasController;
    private ProdutoController produtoController;
    private FuncionarioController funcionarioController;
    private ClienteController clienteController;
    private LojaContext lojaContext;

    public AcessarServicosController(RelatoriosService relatorioService, Scanner sc, LojaService lojaService, VendasController vendasController, ProdutoController produtoController, FuncionarioController funcionarioController, ClienteController clienteController, LojaContext lojaContext) {
        this.relatorioService = relatorioService;
        this.sc = sc;
        this.lojaService = lojaService;
        this.vendasController = vendasController;
        this.produtoController = produtoController;
        this.funcionarioController = funcionarioController;
        this.clienteController = clienteController;
        this.lojaContext = lojaContext;
    }

    //métodos

    public void acessarServicosDaLoja() {
        System.out.println("Digite o ID da loja que deseja acessar: ");
        int idLojaServicos = Integer.parseInt(sc.nextLine());

        Loja lojaSelecionada = lojaService.buscarLojaPorId(idLojaServicos);

        if (lojaSelecionada == null) {
            System.out.println("Loja não encontrada.");

        }

        lojaContext.setLojaAtual(lojaSelecionada);

        int opcaoservico;

        do{
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
                case 1:
                    vendasController.menuvendas();
                    break;
                case 2:
                    produtoController.menuProdutos();
                    break;
                case 3:
                    funcionarioController.menuFuncionario();
                    break;
                case 4:
                    clienteController.menuClientes();
                    break;
                case 5:
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;

            }

        } while (opcaoservico!=0);

    }













}