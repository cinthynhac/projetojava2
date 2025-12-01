package service;
import model.Produto;
import model.Vendas;
import java.util.ArrayList;

public class RelatoriosService {

        private VendasService vendasService;
        private ProdutoService produtoService;
        private ClienteService clienteService;

    public RelatoriosService(VendasService vendasService, ProdutoService produtoService, ClienteService clienteService) {
        this.vendasService = vendasService;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    // RELATÓRIO 1: TOTAL DE VENDAS + FATURAMENTO DA LOJA

        public void relatorioVendasPorLoja(int idLoja) {
            ArrayList<Vendas> vendas = vendasService.listarVendasPorLoja(idLoja);

            System.out.println("===== RELATÓRIO DE VENDAS =====");
            System.out.println("Total de vendas: " + vendas.size());

            double total = 0;
            for (Vendas v : vendas) {
                total += v.getValorTotal();
            }

            System.out.println("Faturamento total: R$" + total);
        }

        // RELATÓRIO 2: LISTAR VENDAS DE UM CLIENTE

        public void relatorioVendasPorCliente(int idLoja, int idCliente) {
            ArrayList<Vendas> vendas = vendasService.listarVendasPorCliente(idLoja, idCliente);

            System.out.println("===== RELATÓRIO DE CLIENTE =====");
            System.out.println("Total de compras: " + vendas.size());

            double soma = 0;
            for (Vendas v : vendas) soma += v.getValorTotal();

            System.out.println("Total gasto: R$" + soma);
        }

        // RELATÓRIO 3: PRODUTOS COM ESTOQUE BAIXO
        public void relatorioEstoqueBaixo(int idLoja, int limite) {
            ArrayList<Produto> produtos = produtoService.listarProdutosPorLoja(idLoja);

            System.out.println("===== PRODUTOS COM ESTOQUE BAIXO =====");

            for (Produto p : produtos) {
                if (p.getQuantidadeEstoque() <= limite) {
                    System.out.println(
                            "Produto: " + p.getNomedoproduto() +
                                    " | Estoque: " + p.getQuantidadeEstoque()
                    );
                }
            }
        }
    }
