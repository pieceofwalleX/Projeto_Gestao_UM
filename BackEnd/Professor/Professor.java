package BackEnd.Professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Professor {
    private String numMec;
    private String nome;
    private String data_inicio;

    public Professor(){
        numMec = "";
        nome = "";
        data_inicio = "";
    }
    public Professor(String numMec,String nome, String data_inicio){
        this.numMec = numMec;
        this.nome = nome;
        this.data_inicio = data_inicio;
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
