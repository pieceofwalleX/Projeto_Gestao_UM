package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.UC;
import BackEnd.Professor.Professor;

public class ListUC {
      private ArrayList<UC> lista;

    public ListUC(){
        lista = new ArrayList<>();
    }
    public void adicionar(UC uc){
        lista.add(uc);
    }
    public int listarUC(boolean print){
        int elementos = 0;
        Professor regente = null;
        for(UC uc:lista){
            regente = uc.getRegente();
            if(print == true) {
                System.out.format("# ID: %d Designacao: %s Regente: %s\t#\n", uc.getId(), uc.getDesignacao(), regente.getNome() == null ? "Sem Regente" : regente.getNome());
                System.out.println("#.........................................#");
            }else{                     
                elementos++;
            }
        }
        return elementos;
    }
    public boolean checkID(int id){
        for(UC uc:lista){
            if(id == uc.getId()){
                return true;
            }
        }
        return false;
    }
    public UC getUCByRegente(String id){
        Professor regente;
        for(UC u:lista){
            regente = u.getRegente();
            if(regente.getNumMec().equals(id)){
                return u;
            }
        }
        return null;
    }
    public void removeUC(int id){
        if(!checkID(id)){
            System.err.println("#Error ID UC nao existe ");
            return;
        }
        lista.remove(id-1); //id - 1 , porque os ids das UCs comecam no 1 e nao no 0 ao contrario dos index
        for(UC uc:lista.subList(id - 1, lista.size())){
            uc.setId(id+1);
        }
    }
}
