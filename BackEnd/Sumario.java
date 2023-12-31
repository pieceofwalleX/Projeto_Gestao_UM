package BackEnd;

import java.io.Serializable;

import BackEnd.Listas.ListAluno;

public class Sumario implements Serializable {
    private String[] tipoAula = {"T","TP","PL"};

    private int idDisciplina;
    private String idProfessor;
    private String aula;

    public Sumario() {
        idDisciplina = 0;
        idProfessor = "";
        aula= tipoAula[0];
    }

    public Sumario(int idDisciplina, String idProfessor,int tipo,ListAluno listaAlunos) {
        this.idDisciplina = idDisciplina;
        this.idProfessor = idProfessor;
        this.aula = tipoAula[tipo];
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public String getIdProfessor() {
        return idProfessor;
    }
    public String getTipoAula(){
        return aula;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }
    public void setTipoAula(int tipo){
        this.aula = tipoAula[tipo - 1];
    }

    public boolean verifyTipoAula(int tipo){
        switch (tipo) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }
}
