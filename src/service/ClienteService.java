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

    //alterar dados de cliente

    public boolean alterarCliente(int idLoja, int idCliente, String novoNome, String novoCpf,
                                  String novoEndereco, String novoTelefone, String novoEmail,
                                  String novaDataNascimento) {

        Cliente cliente = buscarClientePorLoja(idLoja, idCliente);

        if (cliente != null) {
            // Atualiza os dados
            cliente.setNome(novoNome);
            cliente.setCpf(novoCpf);
            cliente.setEndereco(novoEndereco);
            cliente.setTelefone(novoTelefone);
            cliente.setEmail(novoEmail);
            cliente.setDataNascimento(novaDataNascimento);

            return true; // sucesso
        }

        return false; // cliente não encontrado
    }

    }




