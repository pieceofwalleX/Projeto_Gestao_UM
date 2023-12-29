package FrontEnd;
import java.util.Scanner;
import BackEnd.Listas.*;

public class MenuAdministrador {
    private static int authPin = 1234;
    static Scanner in = new Scanner(System.in);
    public static void Auth(ListUC listaUC,ListProfessore listaProf,ListAluno listAluno,ListCurso listaCurso) throws InterruptedException{
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#-----Universidade-do-Minho-----#");
        System.out.println("#.Codigo de Autenticacao >");
        if  (in.nextInt() != authPin){
            System.err.println("#Error Codigo Invalido #");
            Thread.sleep(1000);
            return;
        }else{
            System.out.println(Color.GREEN + "# AVISO Acesso Autorizado #"+ Color.RESET);
            Thread.sleep(500);
            menu(listaUC,listaProf,listAluno,listaCurso);
        }
        System.out.println("#-------------------------------#");
    }

    public static void menu(ListUC listaUC,ListProfessore listaProf,ListAluno listaAluno, ListCurso listaCurso){
        int opcao = 0;
        try{
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#-----Universidade-do-Minho-----#");
            System.out.println("#---------Administracao---------#");
            System.out.println("#                               #");
            System.out.println("#1. Gerir Professor             #");
            System.out.println("#2. Gerir Curso                 #");
            System.out.println("#3. Gerir UCs                   #");
            System.out.println("#4. Listar Informacao           #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#-------------------------------#");
            opcao = in.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    //Menu Gestao de Professores;
                    MenuAdminProfessores.menu(listaProf,listaUC);
                    break;
                case 2:
                    MenuAdminCurso.menu(listaCurso, listaUC, listaProf, listaAluno);
                    break;
                case 3:
                    MenuAdminUC.gestaoUC(listaUC,listaProf);
                    //Menu Gestao de Curso/UC
                    break;
                case 4:
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