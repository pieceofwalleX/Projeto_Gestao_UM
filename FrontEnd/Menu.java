package FrontEnd;

import java.util.Scanner;
import BackEnd.Listas.*;

public class Menu {

    static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        //Inicializar as Listas
        ListUC listaUC = new ListUC();
        ListProfessore listaProf = new ListProfessore();
        ListAluno listaAluno = new ListAluno();
        ListCurso listaCurso = new ListCurso();
        HashSumario listaSumarios = new HashSumario();
       
        int opcao = 0;
    try{
        do{
            
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("%s#.....Universidade-do-Minho.....#%s\n",Color.WHITE_BOLD,Color.RESET);
            System.out.println("#                               #");
            System.out.format("#%s1.%s Administracao               #\n",Color.BLUE_BOLD,Color.RESET);
            System.out.format("#%s2.%s Menu Professor              #\n",Color.BLUE_BOLD,Color.RESET);
            System.out.println("#                               #");
            System.out.format("#%s0.%s Sair                        #\n",Color.BLUE_BOLD,Color.RESET);
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
                        MenuAdministrador.Auth(listaUC,listaProf,listaAluno,listaCurso);
                        break;
                    case 2:
                        /*
                            Muda para o Menu dos Professores
                        */
                        MenuProfessor.authProf(listaSumarios,listaUC,listaProf,listaCurso,listaAluno);
                        break;
                    default:
                        System.err.println("#ERROR Opcao Invalida #");
                        Thread.sleep(400);
                        main(args);
                }
        }while(opcao != 0);
    }catch(Exception e){
         System.err.println("#ERROR Algo deu Errado #");
    }
    in.close();
    }
}