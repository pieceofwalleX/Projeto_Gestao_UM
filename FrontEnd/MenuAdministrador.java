package FrontEnd;
import java.util.Scanner;
import BackEnd.Listas.*;

public class MenuAdministrador {
    private static int authPin = 1234;
    static Scanner in = new Scanner(System.in);
    public static void Auth(ListUC listaUC,ListProfessore listaProf) throws InterruptedException{
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#-----Universidade-do-Minho-----#");
        System.out.println("#.Codigo de Autenticacao >");
        if  (in.nextInt() != authPin){
            System.err.println("#Error Codigo Invalido #");
            Thread.sleep(1000);
            return;
        }else{
            System.out.println("#AVISO Acesso Autorizado #");
            Thread.sleep(500);
            menu(listaUC,listaProf);
        }
        System.out.println("#-------------------------------#");
    }

    public static void menu(ListUC listaUC,ListProfessore listaProf){
        int opcao = 0;
        try{
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#-----Universidade-do-Minho-----#");
            System.out.println("#---------Administracao---------#");
            System.out.println("#                               #");
            System.out.println("#1. Gerir Professor             #");
            System.out.println("#2. Gerir Curso\\UCs             #");
            System.out.println("#3. Listar Informacao           #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#-------------------------------#");
            opcao = in.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    //Menu Gestao de Professores;
                    MenuGestaoProfessores.menu(listaProf);
                    break;
                case 2:
                    //Verificar se o usuario quer modificar uma UC ou um Curso
                    MenuGestaoUC.gestaoUC(listaUC);
                    //Menu Gestao de Curso/UC
                    break;
                case 3:
                    //Verificar que informacao o usuario quer listar
                    //Menu Listagem
                    break;
                default:
                    System.err.println("#ERROR Opcao Invalida #");
                    break;
            }

        }while(opcao != 0);
    }catch (Exception e) {
        System.err.println("#ERROR Algo deu errado #");
    }
    }
}