package BackEnd.Professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Professor {
    private enum tipoProfessor {
        Normal,
        Regente,
        Diretor
    }

    private String numMec;
    private String nome;
    private String data_inicio;
    private tipoProfessor cargo;

    public Professor() {
        numMec = "";
        nome = "";
        data_inicio = "";
        cargo = tipoProfessor.Normal;
    }

    public Professor(String numMec, String nome, String data_inicio, tipoProfessor cargo) {
        this.numMec = numMec;
        this.nome = nome;
        this.data_inicio = data_inicio;
        this.cargo = cargo;
    }

    public void setNumMec(String numMec) {
        this.numMec = numMec;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        switch (cargo) {
            case "Normal":
                this.cargo = tipoProfessor.Normal;
                break;
            case "Regente":
                this.cargo = tipoProfessor.Regente;
                break;
            case "Diretor":
                this.cargo = tipoProfessor.Diretor;
            default:
                System.err.println("\n#ERROR Erro ao mudar o Cargo do professor " + nome);
                break;
        }
    }

    public void setDataInicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getNumMec() {
        return numMec;
    }

    public String getNome() {
        return nome;
    }

    public String getCargoString() {
        if (cargo == tipoProfessor.Normal) {
            return "Normal";
        } else if (cargo == tipoProfessor.Regente) {
            return "Regente";
        } else if (cargo == tipoProfessor.Diretor) {
            return "Diretor";
        } else {
            return "NULL";
        }
    }

    public tipoProfessor getCargo() {
        return cargo;
    }

    public String getDataInicio() {
        return data_inicio;
    }

    public String transformData() {
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
