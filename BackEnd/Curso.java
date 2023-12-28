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
    /*
     * Metodos para as listas
     */
    public void addUC(UC u){
        listaUC.adicionar(u);
    }
    public void addAluno(Aluno a){
        listaAluno.adicionar(a);
    }
}
