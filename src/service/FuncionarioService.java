package service;
import model.Funcionario;
import java.util.ArrayList;

public class FuncionarioService {

    private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

    //cadastrar funcionario
    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    //buscar funcionario
    public Funcionario buscarFuncionarioPorLoja (int idLoja, int idFuncionario) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getIdLoja() == idLoja&&funcionario.getIdFuncionario() == idFuncionario) {
                return funcionario;
            }
        }
        return null;
    }
    //buscar funcionario por cpf

    public Funcionario buscarFuncionarioPorCPF(int idLoja,String cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }

    //excluir funcionario
    public boolean excluirfuncionarioPorLoja(int idLoja, int idFuncionario) {
        Funcionario funcionario =  buscarFuncionarioPorLoja(idLoja, idFuncionario);
        if  (funcionario != null) {
            funcionarios.remove(funcionario);
            return true;
        }
        System.out.println("Funcionário não encontrado.");
        return false;
    }

    //alterar funcionario
    //listar funcionarios
}
