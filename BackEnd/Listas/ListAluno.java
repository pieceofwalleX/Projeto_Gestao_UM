package BackEnd.Listas;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import BackEnd.Aluno;
import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class ListAluno implements Serializable{
    private ArrayList<Aluno> lista;

    public ListAluno(){
        lista = new ArrayList<>();
    }
    public ListAluno(ListAluno lista){
        this.lista = new ArrayList<>(lista.getLista());
    }
    public void adicionar(Aluno a){
        lista.add(a);
    }
    public Aluno getAlunoById(String id){
        return lista.get(Integer.parseInt(id) - 1);
    }
    public void remove(String id){
        int i = 0;
        for(Aluno a: lista){
            if(a.getNumMec().equals(id)){
                lista.remove(i);
                return;
            }
            i++;
        }
    }
    public void removeObject(Aluno a){
        lista.remove(a);
    }
    public boolean inLista(String id){
        for(Aluno a:lista){
            if(a.getNumMec().equals(id)){
                return true;
            }
        }
        return false;
    }
    public void listarSimples(){
        for(Aluno a: lista){
            System.out.format("# Numero: %s%s%s \n# Nome: %s \n",Color.PURPLE, a.getNumMec(),Color.RESET, a.getNome());
            System.out.println("#.........................................#");
        }
    }
    public ArrayList<Aluno> getLista(){
        return lista;
    }

    public void load(InputStream input) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            while (input.available() > 0) {
                Aluno a = (Aluno) ois.readObject();
                adicionar(a);
            }
        }
    }

    public void save(OutputStream output) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(output)) {
            for (Aluno a : lista) {
                oos.writeObject(a);
            }
        }
    }
}
