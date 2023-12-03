package BackEnd.Professor;

import BackEnd.UC;

public class Regente extends Professor{
    private UC uc;

    public Regente(){
        super();
        uc = null;
    }
    public Regente(String nome,String numMec,String data_inicio,UC uc){
        super(numMec,nome,data_inicio);
        this.uc = uc;
    }
    public void setUC(UC uc){
        this.uc = uc;
    }
    public UC getUC(){
        return uc;
    }

}
