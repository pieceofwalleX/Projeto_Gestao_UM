package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.Curso;
import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class ListCurso {
    private ArrayList<Curso> lista;
    public ListCurso(){
        lista = new ArrayList<>();
    }
    public void adicionar(Curso c){
        lista.add(c);
    }
    public boolean checkCursoById(String Id) throws InterruptedException{
        for(Curso c: lista){
            if(c.getId() == Integer.parseInt(Id)){
                return true;
            }
        }
        return false;
    }
    public Curso getCursoByDiretor(String numMec){
        for(Curso c:lista){
            if(c.getDiretor().getNumMec().equals(numMec)){
                return c;
            }
        }
        return null;
    }

    public Curso getCursoById(int id){
        for(Curso c:lista){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    public int listarCursos(boolean print){
        int elementos = 0;
        Professor diretor = null;
        for(Curso c:lista){
            diretor = c.getDiretor();
            if(print == true) {
                System.out.format("# ID: %d Designacao: %s Regente: %s\t  #\n", c.getId(), c.getDesignacao(), diretor == null ? "Sem Diretor" : diretor.getNome());
                System.out.println("#.................................................#");
            }else{                     
                elementos++;
            }
        }
        return elementos;
    }
    public void remove(int id){
        lista.remove(id - 1);
    }
}
