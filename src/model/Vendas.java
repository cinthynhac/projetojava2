package model;

import java.time.LocalDate;
import java.util.List;

public class Vendas {
    //atributos

    private int idVenda;
    private int idLoja;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<ItemVenda> itens;
    private float valorTotal;
    private LocalDate dataVenda;

    //construtores

    public Vendas() {

    }

    public Vendas(int idVenda, int idLoja, Cliente cliente, Funcionario funcionario, List<ItemVenda> itens, float valorTotal, LocalDate dataVenda) {
        this.idVenda = idVenda;
        this.idLoja = idLoja;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    //getters and setter

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
