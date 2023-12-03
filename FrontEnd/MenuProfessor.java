package FrontEnd;

import java.util.Scanner;
import BackEnd.Listas.*;
import BackEnd.*;

public class MenuProfessor{

    static Scanner in = new Scanner(System.in);
    public static void authProf(ListSumario listaSumarios,ListUC listaUC,ListProfessore listaProf) throws InterruptedException { 
        String id;
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#.....Universidade-do-Minho.....#");
        System.out.println("#.Codigo de Professor >");

        id = in.nextLine();

        if (listaProf.checkNumMec(id)){
            System.err.println("#AVISO Acesso Autorizado #");
            Thread.sleep(500);
            menu(listaSumarios,listaUC,listaProf,id);
        }else{
            System.out.println("#Error Codigo Invalido #");
            Thread.sleep(800);
            return;
        }
        System.out.println("#...............................#");
    }

    public static void newSumario(ListSumario listaSumarios,ListUC listaUC,ListProfessore listaProf) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        Sumario s = new Sumario();
        int idUC;
        String idProf;
        boolean inUCList,inProfList;

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#.....Universidade.do.Minho.....#");
        System.out.println("#.......Professor.Sumario.......#");
        System.out.println("#                               #");
        System.out.println("#UC: ");
        idUC = in.nextInt();
        inUCList = listaUC.checkID(idUC);
        in.nextLine();
        if(inUCList){
            System.out.println("#Professor: ");
            idProf = in.nextLine();
            inProfList = listaProf.checkNumMec(idProf);
            if(inProfList){
                s.setIdDisciplina(idUC);
                s.setIdProfessor(idProf);
                System.out.println("# Descricao: ");
                s.setDescricao(in.nextLine());
                listaSumarios.add(s, s.getDescricao());;
                
                System.out.println("#...............................#");
                System.out.println("# Resgistrado Sumario           #");
                listaSumarios.get(s);
                System.out.println("#...............................#");

                Thread.sleep(2200);
            }else{
                System.err.println("#ERROR Prof nao existe");
                Thread.sleep(500);
                return;
            }
        }else{
            System.err.println("#ERROR UC nao existe");
            Thread.sleep(500);
            return;
        }
    }
    public static void menu(ListSumario listaSumarios,ListUC listaUC,ListProfessore listaProf,String id) throws InterruptedException{
        Scanner in = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#.....Universidade.do.Minho.....#");
            System.out.format("#...........Professor.%s.........#\n",id);
            System.out.println("#                               #");
            System.out.println("#1. Criar Sumario               #");
            System.out.println("#2. Lista de Sumarios           #");
            System.out.println("#3. Unidades Curriculares\\UCs   #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    //Menu Gestao de Projefores;
                    newSumario(listaSumarios,listaUC,listaProf);
                    break;
                case 2:
                    //Verificar se o usuario quer modificar uma UC ou um Curso
                    //Menu Gestao de Curso/UC
                    break;
                case 3:
                    //Verificar que informacao o usuario quer listar
                    //Menu Listagem
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }

        } while (opcao != 0);
    }
}
