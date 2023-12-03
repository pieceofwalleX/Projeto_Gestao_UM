package FrontEnd;

import java.util.Scanner;

import BackEnd.Listas.ListProfessore;
import BackEnd.Professor.Professor;

public class MenuGestaoProfessores {
    static Scanner in = new Scanner(System.in);
    public static void addProf(ListProfessore listaProfessore) throws InterruptedException{
            String data;
            Professor u = new Professor();
            System.out.println("#.....Universidade-do-Minho.....#");
            System.out.println("#......Gestao..Professores.....#");
            System.out.println("# Numero do Professor: ");
            u.setNumMec(in.nextLine());
            System.out.println("# Nome do Professor: ");
            u.setNome(in.nextLine());
            System.out.println("# Digite a data de Inicio(ddmmyyyy)");
            data = in.nextLine();
            if(data.length() < 8 || data.length() > 8){
                System.err.println("#ERROR Data Invalida");
                Thread.sleep(700);
                return;
            }
            u.setDataInicio(data);
            System.out.println("#...............................#");
            System.out.format("# Resgistrado Professor \n#Id: %s \n#Nome: %s \n#Data de Inicio: %s\n",u.getId(),u.getNome(),u.transformData());
            System.out.println("#...............................#");
            Thread.sleep(2250);
            listaProfessore.adicionar(u);
    }
    public static void removeProf(){

    }
    public static void menu(ListProfessore listaProf) throws InterruptedException {
        int opcao = 0;
        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#.....Universidade-do-Minho.....#");
            System.out.println("#......Gestao..Professores......#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar Professor        #");
            System.out.println("#2. Editar Dados Professor      #");
            System.out.println("#3. Listar Professores          #");
            System.out.println("#4. Eliminar Professor          #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.nextInt();

            switch (opcao){
                case 0:
                    break;
                case 1:
                    //Registro de novas UCs;
                    addProf(listaProf);
                    break;
                case 3:
                    System.out.println("#.....Universidade.do.Minho.....#");
                    System.out.println("#......Gestao..Professores......#");
                    listaProf.listarProf(true); // O boolean server para listar caso seja true, e apenas contar caso seja false
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
