package FrontEnd;

import java.util.Scanner;

import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessore;
import BackEnd.Listas.ListUC;

public class MenuAdminCurso {
    static final Scanner in = new Scanner(System.in);
    
     public static void menu(ListCurso listaCurso, ListUC listaUC,ListProfessore listaProf, ListAluno listaAluno) throws InterruptedException{
        int opcao = 0;
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#-----Universidade-do-Minho-----#");
            System.out.println("#---------Gestao--Curso---------#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar Curso            #");
            System.out.println("#2. Editar Informaceos Curso    #");
            System.out.println("#4. Listar Cursos               #");
            System.out.println("#5. Remover Curso               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#-------------------------------#");
            opcao = in.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    //Registro de novas UCs;
                    
                    break;
                case 4:
                    System.out.println("#-----Universidade-do-Minho-----#");
                    System.out.println("#---------Gestao--Curso---------#");
                    
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("#-----Universidade-do-Minho-----#");
                    System.out.println("#---------Gestao--Curso---------#");
                    System.out.println("#--------Eliminar--Curso--------#");
                    System.out.println("# Id: ");
                    
                    Thread.sleep(800);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        }while(opcao != 0);
    }
    
}
