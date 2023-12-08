package BackEnd.Listas;

import java.util.HashMap;
import java.util.Map;

import BackEnd.Sumario;

public class HashSumario {
      /*
        Usar HashMap em que receba uma classe que contenha o Id da Uc e do Professor em questao.
    */
    Map<Sumario,String> sumarios = new HashMap<>();

    public void add(Sumario s,String descricao){
        sumarios.put(s,descricao);
    }
    public void get(Sumario s){
        sumarios.get(s);
        System.out.println("\nId Disciplina: "+ s.getIdDisciplina()+
                "\nId Professor: "+ s.getIdProfessor() +
                "\nDescricao: " + s.getDescricao());
    }
    public int listarSumarios(Sumario s,boolean print){
        int elementos = 0;
        for(Map.Entry<Sumario,String> entry: sumarios.entrySet()){
            if(print){
                System.out.println("Id Disciplina: "+ entry.getKey().getIdDisciplina()+
                "Id Professor: "+ entry.getKey().getIdProfessor() +
                "Descricao: " + entry.getValue());
            }else{
                elementos++;
            }
        }

        return elementos;
    }
}
