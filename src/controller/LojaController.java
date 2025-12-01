package controller;
import model.Loja;
import model.LojaContext;
import service.LojaService;

import java.util.List;
import java.util.Scanner;

public class LojaController {

    private LojaContext lojaContext;
    private LojaService lojaService;
    private Scanner sc;
    private AcessarServicosController acessarServicosController;

    //construtores

    public LojaController(LojaContext lojaContext, LojaService lojaService, Scanner sc, AcessarServicosController acessarServicosController) {
        this.lojaContext = lojaContext;
        this.lojaService = lojaService;
        this.sc = sc;
        this.acessarServicosController = acessarServicosController;
    }


    //métodos

    public void menu0(){

        int menu0;

        do{
            System.out.println("Bem vindo ao seu sistema integrado de Lojas. Escolha uma das opções abaixo: ");
            System.out.println("1. Criar loja.");
            System.out.println("2. Acessar Serviços.");
            System.out.println("3. Listar as suas lojas.");
            System.out.println("4. Excluir Loja.");
            System.out.println("0. Sair do Sistema.");
            menu0 = sc.nextInt();
            sc.nextLine();

            switch (menu0){
                case 1:
                    criarloja();
                    break;
                case 2:
                    acessarServicosController.acessarServicosDaLoja();
                    break;
                case 3:
                    listarlojas();
                    break;
                case 4:
                    excluirLoja();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema... Até logo!");
                    break;
            }

        }while (menu0 != 0);

    }

    //metodo criar loja;

    public void criarloja(){
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

    }
    //metodo listar loja
    public void listarlojas(){
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
    }

    //metodo excluir loja

    public void excluirLoja(){
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
    }












}
