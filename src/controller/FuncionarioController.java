package controller;

import model.Funcionario;
import model.Loja;
import model.LojaContext;
import service.FuncionarioService;
import service.LojaService;

import java.util.Scanner;

public class FuncionarioController {

    private FuncionarioService funcionarioService;
    private Scanner sc;
    private LojaContext lojaContext;

    //construtores

    public FuncionarioController(FuncionarioService funcionarioService, Scanner sc, LojaContext lojaContext) {
        this.funcionarioService = funcionarioService;
        this.sc = sc;
        this.lojaContext = lojaContext;
    }


    //métodos

    public void menuFuncionario(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        int opcaofuncionario;

        do {
            System.out.println("\n======= OPÇÃO FUNCIONÁRIO " + lojaSelecionada.getNomeFantasia() + "=======");
            System.out.println("1. Cadastrar Novo Funcionário: ");
            System.out.println("2. Excluir Funcionário: ");
            System.out.println("0. Voltar ao Menu de Serviços da Loja.");
            opcaofuncionario = sc.nextInt();
            sc.nextLine();

            switch (opcaofuncionario){
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    excluirFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }while (opcaofuncionario!=0);
    }

    public void cadastrarFuncionario(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();

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
    }

    public void excluirFuncionario(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();

        System.out.println("Digite o ID do funcionário que deseja excluir:");
        int idExcluir = Integer.parseInt(sc.nextLine());

        boolean excluiu = funcionarioService.excluirfuncionarioPorLoja(
                lojaSelecionada.getIdLoja(),
                idExcluir
        );

        if (excluiu) {
            System.out.println("Funcionário excluído com sucesso!");
        }
    }












}
