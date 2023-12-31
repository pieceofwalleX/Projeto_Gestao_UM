package BackEnd;

import java.io.Serializable;

import BackEnd.Listas.ListProfessor;
import BackEnd.Professor.Professor;

public class UC implements Serializable {
    private static int nextId = 1;
    private int id;
    private String designacao;
    private ListProfessor listaDocente;
    private Professor regente;

    public UC() {
        designacao = "";
        regente = null;
        listaDocente = new ListProfessor();
    }
    /*
     * Este constructor e uma maneira de resolver o bug em que ao criar uma UC auxiliar
     * o id aumentar
     */
    public UC(boolean nova){
        designacao = "";
        id = nextId;
        regente = null;
        listaDocente = new ListProfessor();
        nextId++;
    }

    public UC(String designacao, Professor regente, ListProfessor listaDocente) {
        this.designacao = designacao;
        this.regente = regente;
        this.listaDocente = listaDocente;
        id = nextId;
        nextId++;
    }

    public void setId(int id) {
        /*
         * Esta funcao sera usado caso uma UC seja removida,
         * Sera usado para trocar os ids das proximas UCs
         */
        this.id = id;
    }
    public void setNextId(int id) {
        /*
         * Esta funcao sera usado caso uma UC seja removida,
         * Sera usado para trocar os ids das proximas UCs
         */
        nextId = id;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setRegente(Professor regente) {
        this.regente = regente;
    }

    public int getId() {
        return id;
    }
    public int getNextId() {
        return id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public Professor getRegente() {
        return regente;
    }

    public void adicionarProf(Professor p) {
        
        listaDocente.adicionar(p);
    }
    public void removeProf(String id) {
        listaDocente.removePorf(id);
    }

    public void listarEquipaDocente() {
        listaDocente.listarProfSimples(true);
    }
    public boolean inListaDocente(String id){
        if(listaDocente.checkNumMec(id)){
            return true;
        }
        return false;
    }

    public ListProfessor getListDocente(){
        return listaDocente;
    }
}
