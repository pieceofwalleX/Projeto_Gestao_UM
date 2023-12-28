package BackEnd.Listas;

import java.util.HashMap;
import java.util.Map;

import BackEnd.Content;
import BackEnd.Sumario;

public class HashSumario {
    /*
     * Para os Sumarios sera usado um HashMap em que a sua KEY sera uma class chamada Sumario,
     * Essa class e composta por um (ID UC) e um (ID Professor),
     * O conteudo sera o Sumario em si
     * O uso desta KEY e explicado em baixo (linha 25)
     */
    Map<Sumario,Content> sumarios = new HashMap<>();
    public void add(Sumario s,Content content){
        sumarios.put(s,content);
    }
    public void get(Sumario s){
        sumarios.get(s);
        System.out.println("\nId Disciplina: "+ s.getIdDisciplina()+
                "\nId Professor: "+ s.getIdProfessor() +
                "\nTipo Aula: "+ s.getTipoAula() +
                "\nDescricao: " + s.getDescricao());
    }
    /*
     * No metodo listarSumarios e procurado uma Key(ID UC + ID Professor),
     * Ao usar esta Key e possivel ir diretamente para os sumarios de uma UC de um Certo Professor,
     * Em vez de porcurar em uma lista gigante varios sumarios;
     */
    public int listarSumarios(Sumario s,int tipoAula,String profNome,boolean print){
        int elementos = 0;
        for(Map.Entry<Sumario,Content> entry: sumarios.entrySet()){
            if(print){
                System.out.println("#...............................#");
                System.out.println("# Id Disciplina: "+ entry.getKey().getIdDisciplina()+
                "\n# ID-Nome Professor: "+ entry.getKey().getIdProfessor() + "\t" + profNome +
                "\n# Descricao: " + entry.getValue());
            }else{
                elementos++;
            }
        }

        return elementos;
    }
}
