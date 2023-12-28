package BackEnd.Listas;

import java.util.ArrayList;
import BackEnd.Aluno;

public class ListAluno {
    private ArrayList<Aluno> lista;

    public ListAluno(){
        lista = new ArrayList<>();
    }
    public void adicionar(Aluno a){
        lista.add(a);
    }
}
