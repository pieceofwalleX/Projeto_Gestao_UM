package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.Professor.Professor;
import BackEnd.Professor.Regente;

public class ListProfessore {
     private ArrayList<Professor> lista;

    public ListProfessore(){
        lista = new ArrayList<>();
    }
    public void adicionar(Professor prof){
        lista.add(prof);
    }
    public int listarProf(boolean print){
        int elementos = 0;
        for(Professor prof:lista){
            if(print == true) {
               System.out.format("# Numero: %s \n# Nome: %s \n# Inicio das funcoes: %s\n", prof.getNumMec(), prof.getNome(),prof.transformData());
               System.out.println("#-------------------------------#");
            }else{
                elementos++;
            }
        }
        return elementos;
    }
    public boolean checkNumMec(String numMec){
        for(Professor prof:lista){
            if(numMec.equals(prof.getNumMec())){
                return true;
            }
        }
        return false;
    }
    public boolean isRegente(String numMec){
        for(Professor prof:lista){
            if(numMec.equals(prof.getNumMec()) && prof instanceof Regente){
                return false;
            }
        }
        return true;
    }
}
