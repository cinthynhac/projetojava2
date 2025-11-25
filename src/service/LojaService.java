package service;

import model.Loja;

import java.util.ArrayList;

public class LojaService {

    private ArrayList<Loja> lojas = new ArrayList<>();

    //Listar

    public ArrayList<Loja> getLojas() {
        return lojas;
    }

    //Cadastrar

    public void cadastrarLoja (Loja loja){
        lojas.add(loja);
    }

    //ALterar




    //Excluir

    public boolean excluirLoja(int idLoja) {
        return lojas.removeIf(loja -> loja.getIdLoja() == idLoja);
    }
}




