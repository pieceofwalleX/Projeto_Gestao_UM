package FrontEnd;

import java.util.Scanner;

import BackEnd.UC;
import BackEnd.Listas.ListUC;

public class MenuGestaoUC {
    static final Scanner in = new Scanner(System.in);
        public static void addUC(ListUC listaUC) throws InterruptedException{
            in.nextLine();
            String prof;
            UC uc = new UC(); 
            System.out.println("#-----Universidade-do-Minho-----#");
            System.out.println("#-----------Gestao-UC-----------#");
            System.out.println("# Descricao da UC: ");
            uc.setDesignacao(in.nextLine());
            System.out.println("# Num do Regente da UC: ");
            prof = in.nextLine();
            
            uc.setDesignacao(in.nextLine());
            System.out.format("# Registada UC: %d , %s , %s\t#\n",uc.getId(),prof,uc.getDesignacao());
            System.out.println("#-------------------------------#");
            Thread.sleep(800);
            listaUC.adicionar(uc);
    }
    
    public static void gestaoUC(ListUC listaUC) throws InterruptedException{
        
        int opcao = 0;
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#-----Universidade-do-Minho-----#");
            System.out.println("#-----------Gestao-UC-----------#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar UC               #");
            System.out.println("#2. Editar Informaceos UC       #");
            System.out.println("#3. Listar UCs                  #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#-------------------------------#");
            opcao = in.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    //Registro de novas UCs;
                    addUC(listaUC);
                    break;
                case 3:
                    System.out.println("#-----Universidade-do-Minho-----#");
                    System.out.println("#-----------Gestao-UC-----------#");
                    listaUC.listarUC(true); // O boolean server para listar caso seja true, e apenas contar caso seja false
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        }while(opcao != 0);
    }
}
