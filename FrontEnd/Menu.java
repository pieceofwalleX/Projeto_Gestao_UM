package FrontEnd;

import java.util.Scanner;
import BackEnd.Listas.*;

public class Menu {

    static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        //Inicializar as Listas
        ListUC listaUC = new ListUC();
        ListProfessore listaProf = new ListProfessore();
        HashSumario listaSumarios = new HashSumario();
       
        int opcao = 0;
    try{
        do{
            
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#.....Universidade-do-Minho.....#");
            System.out.println("#                               #");
            System.out.println("#1. Administracao               #");
            System.out.println("#2. Gestao das UCs              #");
            System.out.println("#3. Gestao do Curso             #");
            System.out.println("#4. Menu Professor              #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.nextInt();
            
                switch (opcao){
                    case 0:
                        break;
                    case 1:
                        /*
                            Muda para o metodo Auth no Menu Administrador
                            Verifica se o codigo inserido e igual ao codigo de autenticacao;
                        */
                        MenuAdministrador.Auth(listaUC,listaProf);
                        break;
                    case 4:
                        /*
                            Muda para o Menu dos Professores
                        */
                        MenuProfessor.authProf(listaSumarios,listaUC,listaProf);
                        break;
                    default:
                        System.err.println("#ERROR Opcao Invalida #");
                }
        }while(opcao != 0);
    }catch(Exception e){
         System.err.println("#ERROR Algo deu Errado #");
    }
    in.close();
    }
}