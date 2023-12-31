package BackEnd.Listas;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class ListProfessor implements Serializable{
     private ArrayList<Professor> lista;

    public ListProfessor(){
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
               System.out.println("#.........................................#");
            }else{
                elementos++;
            }
        }
        return elementos;
    }
    public int listarProfSimples(boolean print){
        int elementos = 0;
        for(Professor prof:lista){
            if(print == true) {
               System.out.format("# Numero: %s%s%s \n# Nome: %s \n",Color.PURPLE, prof.getNumMec(),Color.RESET, prof.getNome());
               System.out.println("#.........................................#");
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
    public boolean isDiretor(String numMec){
        for(Professor prof:lista){
            if( numMec.equals(prof.getNumMec()) && prof.getCargoString().equals("Diretor") ){
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

    /*
     * Ficheiros
     */

    public void load(InputStream input) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            while (input.available() > 0) {
                Professor professor = (Professor) ois.readObject();
                adicionar(professor);
            }
        }
    }

    public void save(OutputStream output) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(output)) {
            for (Professor professor : lista) {
                oos.writeObject(professor);
            }
        }
    }
}
