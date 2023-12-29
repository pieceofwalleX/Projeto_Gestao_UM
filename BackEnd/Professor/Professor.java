package BackEnd.Professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import FrontEnd.Color;

public class Professor {
    private enum tipoProfessor {
        Normal,
        Regente,
        Diretor
    }

    private String numMec;
    private String nome;
    private LocalDate data_inicio;
    private tipoProfessor cargo;

    public Professor() {
        numMec = "";
        nome = "";
        data_inicio = null;
        cargo = tipoProfessor.Normal;
    }

    public Professor(String numMec, String nome, LocalDate data_inicio, tipoProfessor cargo) {
        this.numMec = numMec;
        this.nome = nome;
        this.data_inicio = data_inicio;
        this.cargo = cargo;
    }

    public boolean setNumMec(String numMec) throws InterruptedException {
        // Verificar se numMec e um Inteiro
        int number;
        try {
            number = Integer.valueOf(numMec);
        } catch (NumberFormatException e) {
            System.err.println(Color.RED + "#ERROR Caracter invalido" + Color.RESET);
            Thread.sleep(400);
            return false;
        }
        // Verificar se numMec > 0
        if (number <= 0) {
            System.err.println(Color.RED + "#ERROR Numero invalido" + Color.RESET);
            Thread.sleep(400);
            return false;
        }

        this.numMec = numMec;
        return true;
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
                break;
            default:
                System.err.println("\n#ERROR Erro ao mudar o Cargo do professor " + nome);
                break;
        }
    }

    public boolean setDataInicio(LocalDate data_inicio) {
        LocalDate dataAtual = LocalDate.now();
        // Verificar se a Data inserida e maior que a data atual
        if (dataAtual.isBefore(data_inicio)) {
            return false;
        }
        this.data_inicio = data_inicio;
        return true;
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

    public LocalDate getDataInicio() {
        return data_inicio;
    }

    public String transformData() {
        try {
            // Usando DateTimeFormatter para formatar a data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return data_inicio.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return "Data inválida";
        }
    }
}
