package BackEnd;

import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class Curso {
    private static int nextId = 1;
    private int id;
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
        id = nextId;
        designacao = "";
        listaUC = new ListUC();
        listaAluno = new ListAluno();
        diretor = new Professor();
        nextId++;
    }

    public void setId(int id) {
        /*
         * Esta funcao sera usado caso um Curso seja removido,
         * Sera usado para trocar os ids dos proximos Cursos
         */
        this.id = id;
    }
    public void setNextId(int id) {
        /*
         * Esta funcao sera usado caso um Curso seja removido,
         * Sera usado para trocar os ids dos proximos Cursos
         */
        nextId = id;
    }

    public void setDesignacao(String designacao){
        this.designacao = designacao;
    }
    public void setDiretor(Professor diretor){
        this.diretor = diretor;
    }
    public int getId(){
        return id;
    }
    public int getNextId(){
        return nextId;
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
    public void removeUC(int id) throws InterruptedException{
        listaUC.removeUCFromCurso(id);
    }
    public void removeAluno(String id){
        listaAluno.remove(id);
    }
    public void listarUC(){
        listaUC.listarUCInCurso(listaUC,true);
    }
    public ListUC getListaUC(){
        return listaUC;
    }
    public ListAluno getListaAluno(){
        return listaAluno;
    }
    public void listarSimplesUC(){
        int i = 0;
        ListUC tempLista = listaUC;
        for(UC u: tempLista.getLista()){
            System.out.println("\t");
            System.out.println(Color.PURPLE + u.getId() + Color.RESET + ". "+ u.getDesignacao() + "\t");
            if(i % 5 == 0){
                System.out.println("\n\t");
            }
            i++;
        }
    }
    public boolean ucInList(int id){
        ListUC tempLista = listaUC;
        for(UC u : tempLista.getLista()){
            if(u.getId() == id){
                return true;
            }
        }
        return false;
    }

}
