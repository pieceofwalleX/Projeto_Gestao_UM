package FrontEnd;

import java.util.Scanner;

import BackEnd.Curso;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessore;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;

public class MenuAdminCurso {
    static final Scanner in = new Scanner(System.in);

    public static void addCurso(ListUC listaUC, ListProfessore listaProf, ListAluno listaAluno, ListCurso listaCurso)
            throws InterruptedException {
        in.nextLine();
        String profNum;
        Professor prof = new Professor();
        Curso curso = new Curso();
        System.out.println("#.....Universidade.do.Minho.....#");
        System.out.println("#...........Gestao.UC...........#");
        System.out.println("# Descricao do Curso: ");
        curso.setDesignacao(in.next());
        System.out.println("# Num do Diretor do Curso: ");
        profNum = in.next();
        if (!listaProf.checkNumMec(profNum)) {
            System.err.println("ERROR Falha ao encontrar professor");
            Thread.sleep(400);
            return;
        }
        if (listaProf.isDiretor(profNum) || listaProf.isRegente(profNum)) {
            System.err.println("ERROR Professor ja e Diretor de Curso");
            Thread.sleep(400);
            return;
        }
        prof = listaProf.getProfByNum(profNum);
        try{
            prof.setCargo("Diretor");
        }catch(Exception e){
            System.err.println("#ERROR Falha ao trocar o cargo");
            Thread.sleep(400);
        }
        try{
            curso.setDiretor(prof);
        }catch(Exception e){
            System.err.println("#ERROR Falha ao trocar o cargo");
            Thread.sleep(400);
        }
        
        System.out.format("# Registado Curso: %d , %s , %s\t#\n", curso.getId(), profNum, curso.getDesignacao());
        System.out.println("#...............................#");
        Thread.sleep(600);
        listaCurso.adicionar(curso);
    }

    public static void menu(ListCurso listaCurso, ListUC listaUC, ListProfessore listaProf, ListAluno listaAluno)
            throws InterruptedException {
        int opcao = 0;
        do {
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

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    // Registro de novas UCs;
                    addCurso(listaUC, listaProf, listaAluno, listaCurso);
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
        } while (opcao != 0);
    }

}
