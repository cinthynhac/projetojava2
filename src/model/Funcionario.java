package model;
import enums.Cargo;

public class Funcionario{

    //Atributos

    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String dataNascimento;
    private int idFuncionario;
    private int idLoja;
    private Enum<Cargo> Cargo;


    //Construtores

    public Funcionario (){

    }
    public Funcionario(String nome,
                       String cpf,
                       String endereco,
                       String telefone,
                       String email,
                       String dataNascimento,
                       int idFuncionario,
                       int idLoja) {
        
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idFuncionario = idFuncionario;
        this.idLoja = idLoja;
    }


    //Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

}
