package BackEnd.Listas;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import BackEnd.Content;
import BackEnd.Sumario;
import BackEnd.Professor.Professor;
import BackEnd.Aluno;

public class HashSumario implements Serializable{
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
        Content content = sumarios.get(s);
        System.out.println("\nId Disciplina: "+ s.getIdDisciplina()+
                "\nId Professor: "+ s.getIdProfessor() +
                "\nTipo Aula: "+ s.getTipoAula() +
                "\nDescricao: " + content.getDescricao());
    }
    /*
     * No metodo listarSumarios e procurado uma Key(ID UC + ID Professor),
     * Ao usar esta Key e possivel ir diretamente para os sumarios de uma UC de um Certo Professor,
     * Em vez de porcurar em uma lista gigante varios sumarios;
     */
    public int listarSumarios(Sumario s,String tipoAula,String profNome,boolean print){
        int elementos = 0;
        for(Map.Entry<Sumario,Content> entry: sumarios.entrySet()){
            if(print && tipoAula.equals(entry.getKey().getTipoAula())){
                System.out.println("#...............................#");
                System.out.println("# Id Disciplina: "+ entry.getKey().getIdDisciplina()+
                "\n# ID-Nome Professor: "+ entry.getKey().getIdProfessor() + "\t" + profNome +
                "\n# Descricao: " + entry.getValue().getDescricao());
            }else{
                elementos++;
            }
        }

        return elementos;
    }
    public void removeAluno(String id){
        ListAluno tempList = new ListAluno();
        for(Map.Entry<Sumario,Content> entry: sumarios.entrySet()){
            tempList = entry.getValue().getPresencas();
            for(Aluno a: tempList.getLista()){
                if(a.getNumMec().equals(id)){
                    tempList.removeObject(a);
                }
            }
        }
    }

    /*
     * Ficheiros
     */
    public void load(InputStream input) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            Object obj = ois.readObject();
            if (obj instanceof Map<?, ?>) {
                sumarios = (Map<Sumario, Content>) obj;
            }
        }
    }

    public void save(OutputStream output) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(output)) {
            oos.writeObject(sumarios);
        }
    }
}
