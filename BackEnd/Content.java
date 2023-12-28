package BackEnd;

import BackEnd.Listas.ListAluno;

public class Content {
    private ListAluno presencas;
    private String descricao;

    public Content(){
        presencas = new ListAluno();
        descricao = "";
    }
    public Content(ListAluno presencas,String descricao){
        this.presencas = presencas;
        this.descricao = descricao;
    }
    public void setPresencas(ListAluno presencas){
        this.presencas = presencas;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public ListAluno getPresencas(){
        return presencas;
    }
    public String Descricao(){
        return descricao;
    }

}
