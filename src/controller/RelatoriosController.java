package controller;

import model.Loja;
import model.LojaContext;
import service.RelatoriosService;

import java.util.Scanner;

public class RelatoriosController {

    private RelatoriosService relatoriosService;
    private Scanner sc;
    private LojaContext lojaContext;

    //construtores

    public RelatoriosController(RelatoriosService relatoriosService, Scanner sc, LojaContext lojaContext) {
        this.relatoriosService = relatoriosService;
        this.sc = sc;
        this.lojaContext = lojaContext;
    }


    //metodos

    public void menuRelatorio(){
        int opcaorelatorio = 0;

        do{
            System.out.println("\n===== RELATÓRIOS =====");
            System.out.println("1. Vendas da loja");
            System.out.println("2. Vendas de um cliente");
            System.out.println("3. Produtos com estoque baixo");
            System.out.println("0. Voltar");
            opcaorelatorio = sc.nextInt();
            sc.nextLine();

            switch(opcaorelatorio){
                case 1:
                    vendasdaLoja();
                    break;
                case 2:
                    vendasPorCliente();
                    break;
                case 3:
                    relatorioEstoqueBaixo();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;

            }

        }while (opcaorelatorio!=0);

    }

    public void vendasdaLoja(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        relatoriosService.relatorioVendasPorLoja(lojaSelecionada.getIdLoja());
    }

    public void vendasPorCliente(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("Digite ID do cliente:");
        int idC = sc.nextInt();
        relatoriosService.relatorioVendasPorCliente(lojaSelecionada.getIdLoja(), idC);
    }

    public void relatorioEstoqueBaixo (){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("Mostrar produtos com estoque <= que qual quantidade?");
        int limite = sc.nextInt();
        relatoriosService.relatorioEstoqueBaixo(lojaSelecionada.getIdLoja(), limite);
    }













}
