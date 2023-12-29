package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.Curso;

public class ListCurso {
    private ArrayList<Curso> lista;
    public ListCurso(){
        lista = new ArrayList<>();
    }
    public void adicionar(Curso c){
        lista.add(c);
    }
    public Curso getCursoByDiretor(String numMec){
        for(Curso c:lista){
            if(c.getProf().getNumMec().equals(numMec)){
                return c;
            }
        }
        return null;
    }
}
