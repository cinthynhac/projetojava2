package service;
import model.Cliente;
import java.util.ArrayList;

public class ClienteService {
    private ArrayList<Cliente> clientes= new ArrayList<>();

    //cadastrar cliente

    public void cadastrarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    //listar clientes

    public ArrayList<Cliente> listarClientesPorLoja(int idLoja){
        ArrayList<Cliente> clientesdaloja = new ArrayList<>();

        for (Cliente cliente : clientes){
            if (cliente.getIdLoja() == idLoja){
                clientesdaloja.add(cliente);
            }
        }
        return clientesdaloja;
    }

    //buscar cliente

    public Cliente buscarClientePorLoja(int idLoja, int idCliente){
        for (Cliente cliente : clientes){
            if (cliente.getIdLoja() == idLoja&&cliente.getIdCliente() == idCliente){
                return cliente;
            }
        }
        return null;
    }
    //buscar cliente por CPF
    public Cliente buscarClientePorCpf(int idLoja,String cpf){
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    //excluir cliente
    public boolean removerClientePorLoja(int idLoja,int idCliente){
        Cliente cliente = buscarClientePorLoja(idLoja, idCliente);

            if (cliente != null){
                clientes.remove(cliente);
                return true;
            }
        System.out.println("Cliente não encontrado.");
            return false;
        }


    }




