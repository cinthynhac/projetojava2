package service;
import model.Loja;
import java.util.ArrayList;

public class LojaService {
    private ArrayList<Loja> lojas = new ArrayList<>();

    //Cadastrar

    public void cadastrarLoja (Loja loja){
        lojas.add(loja);
    }

    //Listar

    public ArrayList<Loja> getLojas() {
        return lojas;
    }


    //buscar loja por id
    public Loja buscarLojaPorId(int idLoja) {
        for (Loja loja : lojas) {
            if (loja.getIdLoja() == idLoja) {
                return loja;
            }
        }
        return null;
    }

    //Alterar




    //Excluir

    public boolean excluirLoja(int idLoja) {return lojas.removeIf(loja -> loja.getIdLoja() == idLoja);
    }
}




