package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.Professor.Professor;

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
               System.out.format("# Numero: %s \n# Nome: %s \n# Cargo: %s\n# Inicio das funcoes: %s\n", prof.getNumMec(), prof.getNome(),prof.getCargoString(),prof.transformData());
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

    public Professor getProfByNum(String numMec){
        for(Professor prof:lista){
            if( numMec.equals(prof.getNumMec()) ){
                return prof;
            }
        }
        return null;
    }

    public boolean isRegente(String numMec){
        for(Professor prof:lista){
            if( numMec.equals(prof.getNumMec()) && prof.getCargoString().equals("Regente") ){
                return true;
            }
        }
        return false;
    }

    public void removePorf(String numMec){
        int i = 0;
        for(Professor p: lista){
            if(p.getNumMec().equals(numMec)){
                lista.remove(i);
                return;
            }
            i++;
        }
    }
}
