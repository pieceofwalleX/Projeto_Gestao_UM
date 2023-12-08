package BackEnd.Professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum tipoProfessor{
    Normal,
    Regente,
    Diretor
}

public class Professor {
    private String numMec;
    private String nome;
    private String data_inicio;
    private tipoProfessor cargo;

    public Professor(){
        numMec = "";
        nome = "";
        data_inicio = "";
        cargo = tipoProfessor.Normal;
    }
    public Professor(String numMec,String nome, String data_inicio,tipoProfessor cargo){
        this.numMec = numMec;
        this.nome = nome;
        this.data_inicio = data_inicio;
        this.cargo = cargo;
    }
    public void setNumMec(String numMec){
        this.numMec = numMec;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDataInicio(String data_inicio){
        this.data_inicio = data_inicio;
    }
    public String getNumMec(){
        return numMec;
    }
    public String getNome(){
        return nome;
    }
    public String getDataInicio(){
        return data_inicio;
    }
    public String transformData(){
        try {
            SimpleDateFormat original = new SimpleDateFormat("ddMMyyyy");
            Date data = original.parse(data_inicio);

            SimpleDateFormat transformedDate = new SimpleDateFormat("dd/MM/yyyy");
            return transformedDate.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Data inválida";
        }
    }
}
