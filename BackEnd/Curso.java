package BackEnd;

import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;

public class Curso {
    private static int nextId = 1;
    private int Id;
    private String designacao;
    private ListUC listaUC;
    private ListAluno listaAluno;
    private Professor diretor;

    public Curso(){
        designacao = "";
        listaUC = new ListUC();
        listaAluno = new ListAluno();
        diretor = new Professor();
    }
        /*
     * Este constructor e uma maneira de resolver o bug em que ao criar um Curso auxiliar
     * o id aumentar
     */
    public Curso(boolean novo){
        Id = nextId;
        designacao = "";
        listaUC = new ListUC();
        listaAluno = new ListAluno();
        diretor = new Professor();
        nextId++;
    }

    public void setDesignacao(String designacao){
        this.designacao = designacao;
    }
    public void setDiretor(Professor diretor){
        this.diretor = diretor;
    }
    public int getId(){
        return Id;
    }
    public String getDesignacao(){
        return designacao;
    }
    public Professor getDiretor(){
        return diretor;
    }
    /*
     * Metodos para as listas
     */
    public void addUC(UC u){
        listaUC.adicionar(u);
    }
    public void addAluno(Aluno a){
        listaAluno.adicionar(a);
    }
    public void removeUC(int id){
        listaUC.removeUC(id);;
    }
    public void removeAluno(String id){
        listaAluno.remove(id);
    }
    public void listarUC(){
        listaUC.listarUC(true);
    }

}
