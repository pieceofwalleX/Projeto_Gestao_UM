package FrontEnd.Admin;
import java.util.Scanner;
import BackEnd.Listas.*;
import FrontEnd.Color;

public class MenuAdministrador {
    private static String authPin = "1234";
    static Scanner in = new Scanner(System.in);
    public static void Auth(ListUC listaUC,ListProfessor listaProf,ListAluno listAluno,ListCurso listaCurso) throws InterruptedException{
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#.Codigo de Autenticacao >");
        if(!in.next().equals(authPin)){
            System.out.println(Color.RED + "#Error Codigo Invalido #" + Color.RESET);
            Thread.sleep(1000);
            return;
        }else{
            System.out.println(Color.GREEN + "# AVISO Acesso Autorizado #"+ Color.RESET);
            Thread.sleep(500);
            menu(listaUC,listaProf,listAluno,listaCurso);
        }
        System.out.println("#...............................#");
    }

    public static void menu(ListUC listaUC,ListProfessor listaProf,ListAluno listaAluno, ListCurso listaCurso){
        String opcao;
        try{
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#.........Administracao.........#");
            System.out.println("#                               #");
            System.out.println("#1. Gerir Professor             #");
            System.out.println("#2. Gerir Curso                 #");
            System.out.println("#3. Gerir UCs                   #");
            System.out.println("#4. Listar Alunos               #");
            System.out.println("#                               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao){
                case "0":
                    break;
                case "1":
                    //Menu Gestao de Professores;
                    MenuAdminProfessores.menu(listaProf,listaUC,listaCurso);
                    break;
                case "2":
                    MenuAdminCurso.menu(listaCurso, listaUC, listaProf, listaAluno);
                    break;
                case "3":
                    MenuAdminUC.gestaoUC(listaUC,listaProf);
                    //Menu Gestao de Curso/UC
                    break;
                case "4":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#.........Administracao.........#");
                    listaAluno.listarSimples();
                default:
                    System.err.println("#ERROR Opcao Invalida #");
                    break;
            }

        }while(!opcao.equals("0"));
    }catch (Exception e) {
        System.err.println("#ERROR Algo deu errado #");
    }
    }
}