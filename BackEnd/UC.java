package BackEnd;

import BackEnd.Listas.ListProfessore;
import BackEnd.Professor.Regente;

public class UC {
    private static int nextId = 1;
    private int id;
    private String designacao;
    private ListProfessore listaProfessores;
    private Regente regente;

    public UC(){
        designacao = "";
        id = nextId;
        nextId++;
    }
    public UC(String designacao){
        this.designacao = designacao;
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
    public int getId(){
        return id;
    }
    public String getDesignacao(){
        return designacao;
    }
}
