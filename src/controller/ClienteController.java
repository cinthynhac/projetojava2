package controller;
import model.Cliente;
import model.Loja;
import model.LojaContext;
import service.ClienteService;
import service.LojaService;

import java.util.ArrayList;
import java.util.Scanner;

public class ClienteController {

        private ClienteService clienteService;
        private Scanner sc;
        private LojaContext  lojaContext;


      //construtores

    public ClienteController(ClienteService clienteService, Scanner sc, LojaContext lojaContext) {
        this.clienteService = clienteService;
        this.sc = sc;
        this.lojaContext = lojaContext;
    }


    //metodos

    public void menuClientes(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        int opcaocliente;

        do{
            System.out.println("\n======= OPÇÃO CLIENTES " + lojaSelecionada.getNomeFantasia() + "=======");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por CPF");
            System.out.println("4. Excluir Cliente");
            System.out.println("0. Voltar");
            opcaocliente = sc.nextInt();
            sc.nextLine();

            switch (opcaocliente){
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarClienteporCPF();
                    break;
                case 4:
                    excluirCliente();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcaocliente!=0);

    }

    public void cadastrarCliente(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
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
    }

    public void listarClientes(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
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
    }

    public void buscarClienteporCPF(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();

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

    }

    public void excluirCliente(){
        Loja lojaSelecionada = lojaContext.getLojaAtual();
        System.out.println("Digite o ID do cliente que deseja excluir:");
        int idc = Integer.parseInt(sc.nextLine());

        boolean clienteRemovido = clienteService.removerClientePorLoja(lojaSelecionada.getIdLoja(), idc);

        if (clienteRemovido) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Nenhum cliente encontrado com esse CPF.");
        }

    }














}
