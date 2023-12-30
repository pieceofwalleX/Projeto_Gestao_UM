package BackEnd.Professor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import BackEnd.UC;
import BackEnd.Listas.ListUC;
import FrontEnd.Color;
import FrontEnd.Verification;

public class Professor {
    final static Verification check = new Verification();
    private enum tipoProfessor {
        Normal,
        Regente,
        Diretor
    }

    private String numMec;
    private String nome;
    private LocalDate data_inicio;
    private tipoProfessor cargo;
    private ListUC lista;

    public Professor() {
        numMec = "";
        nome = "";
        data_inicio = null;
        cargo = tipoProfessor.Normal;
        lista = new ListUC();
    }

    public Professor(String numMec, String nome, LocalDate data_inicio, tipoProfessor cargo) {
        this.numMec = numMec;
        this.nome = nome;
        this.data_inicio = data_inicio;
        this.cargo = cargo;
        lista = new ListUC();
    }

    public boolean setNumMec(String numMec) throws InterruptedException {
        // Verificar se numMec e um Inteiro
        if(!check.isInteger(numMec)){
            System.err.println(Color.RED + "#ERROR Caracter invalido" + Color.RESET);
            Thread.sleep(400);
            return false;
        }
        // Verificar se numMec > 0
        if(Integer.parseInt(numMec) <= 0) {
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

    /*
     * Metodos Lista Servico Docente
     */

    public void addServico(UC u) {
        lista.adicionar(u);
    }

    public void removeServico(int id) throws InterruptedException {
        lista.removeUC(id);
    }

    public ListUC getListaServico() {
        return lista;
    }

    public void removeProfFromUC(String id) {
        ListUC listaUC = getListaServico();
        for (UC u : listaUC.getLista()) {
            u.removeProf(id);
        }
    }

}
