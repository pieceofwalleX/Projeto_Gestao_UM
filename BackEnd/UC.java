package BackEnd;

import BackEnd.Listas.ListProfessore;
import BackEnd.Professor.Professor;

public class UC {
    private static int nextId = 1;
    private int id;
    private String designacao;
    private ListProfessore listaProfessores;
    private Professor regente;

    public UC(){
        designacao = "";
        id = nextId;
        regente = null;
        nextId++;
    }
    public UC(String designacao,Professor regente){
        this.designacao = designacao;
        this.regente = regente;
        id = nextId;
        nextId++;
    }
    public void setId(int id){
        /*
            Esta funcao sera usado caso uma UC seja removida,
            Sera usado para trocar os ids das proximas UCs
        */
        this.id = id;
    }
    public void setDesignacao(String designacao){
        this.designacao = designacao;
    }
    public void setRegente(Professor regente){
        this.regente = regente;
    }
    public int getId(){
        return id;
    }
    public String getDesignacao(){
        return designacao;
    }
    public Professor getRegente(){
        return regente;
    }
}
