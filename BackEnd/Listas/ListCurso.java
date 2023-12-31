package BackEnd.Listas;

import java.util.ArrayList;

import BackEnd.Curso;
import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class ListCurso {
    private ArrayList<Curso> lista;

    public ListCurso() {
        lista = new ArrayList<>();
    }

    public void adicionar(Curso c) {
        lista.add(c);
    }

    public boolean checkCursoById(String Id) throws InterruptedException {
        for (Curso c : lista) {
            if (c.getId() == Integer.parseInt(Id)) {
                return true;
            }
        }
        return false;
    }

    public Curso getCursoByDiretor(String numMec) {
        for (Curso c : lista) {
            if (c.getDiretor().getNumMec().equals(numMec)) {
                return c;
            }
        }
        return null;
    }

    public Curso getCursoById(int id) {
        for (Curso c : lista) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public int listarCursos(boolean print) {
        int elementos = 0;
        Professor diretor = null;
        for (Curso c : lista) {
            diretor = c.getDiretor();
            if (print == true) {
                System.out.format("# ID: %d Designacao: %s Regente: %s\t  #\n", c.getId(), c.getDesignacao(),
                        diretor == null ? "Sem Diretor" : diretor.getNome());
                System.out.println("#.................................................#");
            } else {
                elementos++;
            }
        }
        return elementos;
    }

    public void listarCursosSimples() {
        int i = 1;
        for (Curso c : lista) {
            System.out.print(Color.PURPLE + i + Color.RESET + ". " + c.getDesignacao() + "\t");
            if (i % 5 == 0) {
                System.out.println("\n");
            }
            i++;
        }
    }

    public void remove(int id) throws InterruptedException {
        if (!checkCursoById(Integer.toString(id))) {
            System.err.println("#Error ID Curso nao existe ");
            return;
        }
        Curso curso = getCursoById(id);
        if (lista.size() == 1) {
            curso.setNextId(1);
            System.out.println(curso.getNextId());
        }
        lista.remove(id - 1); // id - 1 , porque os ids das UCs comecam no 1 e nao no 0 ao contrario dos index
        for (int i = id - 1; i < lista.size(); i++) {
            Curso c = lista.get(i);
            c.setId(i + 1);

            // Atualiza o nextId do último curso na lista
            if (i == lista.size()) {
                curso = c;
            }
        }
        curso.setNextId(lista.size() + 1);
    }
}
