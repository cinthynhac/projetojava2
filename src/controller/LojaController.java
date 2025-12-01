package controller;

import model.Loja;
import service.LojaService;

import java.util.Scanner;

public class LojaController {

    private LojaService lojaService;
    private Scanner sc;

    public LojaController(LojaService lojaService, Scanner sc) {
        this.lojaService = lojaService;
        this.sc = sc;
    }

    public void menuLoja() {
        int opcao;

        do {
            System.out.println("\n===== MENU LOJA =====");
            System.out.println("1. Criar loja");
            System.out.println("2. Listar lojas");
            System.out.println("3. Excluir loja");
            System.out.println("0. Voltar");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Loja loja = new Loja();
                    System.out.println("Razão Social: ");
                    loja.setRazaoSocial(sc.nextLine());
                    System.out.println("Nome Fantasia: ");
                    loja.setNomeFantasia(sc.nextLine());
                    System.out.println("CNPJ: ");
                    loja.setCnpj(sc.nextLine());
                    System.out.println("Endereço: ");
                    loja.setEndereco(sc.nextLine());
                    System.out.println("Telefone: ");
                    loja.setTelefone(sc.nextLine());
                    System.out.println("Email: ");
                    loja.setEmail(sc.nextLine());
                    System.out.println("ID da Loja: ");
                    loja.setIdLoja(Integer.parseInt(sc.nextLine()));

                    lojaService.cadastrarLoja(loja);
                    System.out.println("Loja cadastrada!");
                    break;

                case 2:
                    System.out.println("\n===== LISTA DE LOJAS =====");

                    lojaService.buscarLojaPorId().forEach(l ->
                            System.out.println("ID: " + loja.getIdLoja() + " | Nome: " + loja.getNomeFantasia())
                    );
                    break;

                case 3:
                    System.out.println("Digite o ID da loja para excluir:");
                    int idExcluir = Integer.parseInt(sc.nextLine());
                    boolean excluiu = lojaService.excluirLoja(idExcluir);

                    if (excluiu) System.out.println("Loja excluída!");
                    else System.out.println("Loja não encontrada.");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
