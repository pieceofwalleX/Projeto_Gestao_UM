package BackEnd.Professor;

import BackEnd.Curso;

public class Diretor extends Professor{
    private Curso curso;

    public Diretor(){
        super();
        curso = null;
    }
    public Diretor(String nome,String numMec,String data_inicio,Curso curso){
        super(numMec, nome, data_inicio);
        this.curso = curso;
    }
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    public Curso getCurso(){
        return curso;
    }
}
