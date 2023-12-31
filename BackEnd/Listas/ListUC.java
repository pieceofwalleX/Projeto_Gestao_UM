package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.UC;
import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class ListUC{
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
                System.out.format("# ID: %d Designacao: %s Regente: %s\t\t  #\n", uc.getId(), uc.getDesignacao(), regente == null ? "Sem Regente" : regente.getNome());
                System.out.println("#.................................................#");
            }else{                     
                elementos++;
            }
        }
        return elementos;
    }
    public int listarUCInCurso(ListUC listaCurso,boolean print){
        int elementos = 0;
        Professor regente = null;
        for(UC uc:lista){
            if(listaCurso.checkID(uc.getId())){
                regente = uc.getRegente();
                if(print == true) {
                    System.out.format("# ID: %d Designacao: %s Regente: %s\t\t  #\n", uc.getId(), uc.getDesignacao(), regente == null ? "Sem Regente" : regente.getNome());
                    System.out.println("#.................................................#");
                }else{                     
                    elementos++;
                }
            }
        }
        return elementos;
    }
    public void listarUCSimples(ListUC listaCurso,boolean check){
        int i = 1;
        ListUC tempList = listaCurso;
            /*
             * Caso seja para verificar se existem UCs na lista de Curso (boolean check)
             * E caso a UC(u) tenha o id de uma UC na ListaCurso entao nao mostrar
             *                  Adicionar                                    Remover
             */
        if(!check){
            for(UC u:lista){
                if(tempList.checkID(u.getId())){
                    continue;
                }
                System.out.print(Color.PURPLE + u.getId() + Color.RESET + ". "+ u.getDesignacao() + "\t");
                if(i % 5 == 0){
                    System.out.println("\n");
                }
                i++;
            }
        }else{
            for(UC u: tempList.getLista()){
                System.out.print(Color.PURPLE + i + Color.RESET + ". "+ u.getDesignacao() + "\t");
                if(i % 5 == 0){
                    System.out.println("\n");
                }
                i++;
            }
        }
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
    public UC getUCById(int id){
        for(UC u:lista){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
    public void removeUC(int id) throws InterruptedException{
        if(!checkID(id)){
            System.err.println("#Error ID Curso nao existe ");
            return;
        }
        UC uc = getUCById(id);
        if(lista.size() == 1){
            uc.setNextId(1);
        }
        lista.remove(id - 1); //id - 1 , porque os ids das UCs comecam no 1 e nao no 0 ao contrario dos index
        for (int i = id - 1; i < lista.size(); i++) {
            UC u = lista.get(i);
            u.setId(i + 1);
    
            // Atualiza o nextId do último curso na lista
            if (i == lista.size()) {
                uc = u;
            }

        }
        uc.setNextId(lista.size() + 1);
    }
    public void removeUCFromCurso(int id) throws InterruptedException{
        lista.remove(id - 1);
    }


    public ArrayList<UC> getLista() {
        return lista;
    }

}
