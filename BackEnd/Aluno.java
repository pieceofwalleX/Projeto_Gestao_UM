package BackEnd;

public class Aluno {
    private static String nextNum = "1";
    private String nome;
    private String numMec; //Sera usado o sistema de IDs ou seja os alunos comecao no 1 e sempre que se registra um aluno sera aumentado +1 ao numMec;
    private Curso curso;
    private int falta = 0;

    public Aluno(){
        nome = "";
        numMec = nextNum;
        curso = null;
        nextNum = Integer.toString(Integer.parseUnsignedInt(nextNum) + 1);// Transforma uma String em um Inteiro(positivo) e soma 1. Depois transforma em String outra vez
    }
    public Aluno(String nome,String numMec,Curso curso){
        this.nome = nome;
        this.numMec = numMec;
        this.curso = curso;
        numMec = nextNum;
        nextNum = Integer.toString(Integer.parseUnsignedInt(nextNum) + 1);
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNumMec(String numMec){
        this.numMec = numMec;
    }
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    public void addFalta(){
        falta++;
    }
    public String getNome(){
        return nome;
    }
    public String getNumMec(){
        return numMec;
    }
    public int getFaltas(){
        return falta;
    }
    public Curso getCurso(){
        return curso;
    }

}
