package BackEnd;

import BackEnd.Listas.ListProfessore;
import BackEnd.Professor.Professor;

public class UC {
    private static int nextId = 1;
    private int id;
    private String designacao;
    private ListProfessore listaDocente = new ListProfessore();
    private Professor regente;

    public UC() {
        designacao = "";
        regente = null;
        listaDocente = null;
    }
    /*
     * Este constructor e uma maneira de resolver o bug em que ao criar uma UC auxiliar
     * o id aumentar
     */
    public UC(boolean nova){
        designacao = "";
        id = nextId;
        regente = null;
        listaDocente = null;
        nextId++;
    }

    public UC(String designacao, Professor regente, ListProfessore listaDocente) {
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

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setRegente(Professor regente) {
        this.regente = regente;
    }

    public int getId() {
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

    public void listarEquipaDocente() {
        listaDocente.listarProf(true);
    }
}
