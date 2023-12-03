package BackEnd;

public class Sumario {
    private int idDisciplina;
    private String idProfessor;
    private String descricao;

    public Sumario(){
        idDisciplina = 0;
        idProfessor = "";
    }
    public Sumario(int idDisciplina,String idProfessor){
        this.idDisciplina = idDisciplina;
        this.idProfessor = idProfessor;
    }
    public int getIdDisciplina(){
        return idDisciplina;
    }
    public String getIdProfessor(){
        return idProfessor;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setIdDisciplina(int idDisciplina){
        this.idDisciplina = idDisciplina;
    }
    public void setIdProfessor(String idProfessor){
        this.idProfessor = idProfessor;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
