package service;
import model.Vendas;
import java.util.ArrayList;

public class VendasService {
    private ArrayList<Vendas> venda = new ArrayList<>();
    private int proximoId = 1;

    //gerar ir automática

    private int gerarIdVenda() {
        return proximoId++;
    }

    //cadastrar venda

    public void cadastrarVenda (Vendas vendas){
        vendas.setIdVenda(gerarIdVenda());
        venda.add(vendas);
    }

    //listar vendas por loja;

    public ArrayList<Vendas> listarVendasPorLoja(int idLoja){
        ArrayList<Vendas> vendasDaLoja = new ArrayList<>();
        for (Vendas venda : venda){
            if (venda.getIdLoja() == idLoja){
                vendasDaLoja.add(venda);
            }
        }
        return vendasDaLoja;
    }
    //listar vendas por cliente
    public ArrayList<Vendas> listarVendasPorCliente(int idLoja, int idCliente) {
        ArrayList<Vendas> vendasDaLoja = listarVendasPorLoja(idLoja);
        ArrayList<Vendas> vendasDoCliente = new ArrayList<>();

        for (Vendas venda : vendasDaLoja) {
            if (venda.getCliente().getIdCliente() == idCliente) {
                vendasDoCliente.add(venda);
            }
        }

        return vendasDoCliente;
    }
    //buscar venda por Loja;
    public Vendas buscarVendaPorLoja(int idLoja, int idVenda){
        for (Vendas venda : venda){
            if (venda.getIdLoja() == idLoja && venda.getIdVenda() == idVenda){
                return venda;
            }
        }
        return null;
    }
    //buscar venda por Cliente;
    public Vendas buscarVendaPorCliente(int idLoja, int idCliente, int idVenda) {
        ArrayList<Vendas> vendasCliente = listarVendasPorCliente(idLoja, idCliente);

        for (Vendas venda : vendasCliente) {
            if (venda.getIdVenda() == idVenda) {
                return venda;
            }
        }
        return null;
    }

    //excluir venda

    public boolean excluirVenda(int idLoja, int idVenda){
        Vendas vendaencontrada = buscarVendaPorLoja(idLoja, idVenda);

        if (vendaencontrada != null){
            venda.remove(vendaencontrada);
            return true;
        }
        System.out.println("Venda não encontrada!");
        return false;
    }


}
