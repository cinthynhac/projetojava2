import controller.*;
import model.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LojaContext lojaContext = new LojaContext();

        // SERVICES
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();
        LojaService lojaService = new LojaService();
        FuncionarioService funcionarioService = new FuncionarioService();
        VendasService vendasService = new VendasService();
        RelatoriosService relatorioService = new RelatoriosService(
                vendasService,
                produtoService,
                clienteService
        );

        // CONTROLLERS SECUNDÁRIOS (cada um recebe seus SERVICES necessários)
        ProdutoController produtoController =
                new ProdutoController(produtoService, sc, lojaContext);

        ClienteController clienteController =
                new ClienteController(clienteService, sc, lojaContext);

        FuncionarioController funcionarioController =
                new FuncionarioController(funcionarioService, sc, lojaContext);

        VendasController vendasController =
                new VendasController(vendasService, clienteService, funcionarioService, produtoService, lojaService,sc, lojaContext);

        RelatoriosController relatoriosController =
                new RelatoriosController(relatorioService, sc, lojaContext);

        // CONTROLLER PRINCIPAL DOS SERVIÇOS
        AcessarServicosController acessarServicosController =
                new AcessarServicosController(
                        relatorioService,
                        sc,
                        lojaService,
                        vendasController,
                        produtoController,
                        funcionarioController,
                        clienteController,
                        lojaContext
                );

        // CONTROLLER PRINCIPAL DO SISTEMA
        LojaController lojaController =
                new LojaController(
                        lojaContext,
                        lojaService,
                        sc,
                        acessarServicosController
                );

        // EXECUTAR SISTEMA
        lojaController.menu0();

    }
}

