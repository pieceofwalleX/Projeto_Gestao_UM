package BackEnd;

import BackEnd.Listas.ListAluno;

public class Sumario {
    private String[] tipoAula = {"T","TP","PL"};

    private int idDisciplina;
    private String idProfessor;
    private String descricao;
    private String aula;
    private ListAluno listaAlunos;

    public Sumario() {
        idDisciplina = 0;
        idProfessor = "";
        aula= tipoAula[0];
        listaAlunos = null;
    }

    public Sumario(int idDisciplina, String idProfessor,int tipo,ListAluno listaAlunos) {
        this.idDisciplina = idDisciplina;
        this.idProfessor = idProfessor;
        this.aula = tipoAula[tipo];
        this.listaAlunos = listaAlunos;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public String getDescricao() {
        return descricao;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setTipoAula(int tipo){
        this.aula = tipoAula[tipo];
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
