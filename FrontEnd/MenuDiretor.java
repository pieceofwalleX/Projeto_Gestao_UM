package FrontEnd;

import java.util.Scanner;

import BackEnd.Curso;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessor;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;

public class MenuDiretor {
    static final Scanner in = new Scanner(System.in);
    
     public static void menu(ListCurso listaCurso, ListUC listaUC, ListProfessor listaProf, ListAluno listaAluno,Professor p)
            throws InterruptedException {
        String opcao;
        Curso c = new Curso();
        c = listaCurso.getCursoByDiretor(p.getNumMec());
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Diretor: %s    %s[%s]%s\t#",p.getNome(),Color.RED,c.getDesignacao(),Color.RESET);
            System.out.println("\n#                               #");
            System.out.println("#1. Alterar Designacao do Curso #");
            System.out.println("#2. Listar Professores/Alunos   #");
            System.out.println("#                               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Registro de novas UCs;
                    
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }
}
