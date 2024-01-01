package FrontEnd;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import BackEnd.Curso;
import BackEnd.UC;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessor;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;
import FrontEnd.Verification;

public class MenuDiretor {
    static final Scanner in = new Scanner(System.in);
    static final Verification check = new Verification();

    public static void menu(ListCurso listaCurso, ListUC listaUC, ListProfessor listaProf, ListAluno listaAluno,Professor p) throws InterruptedException {
        String opcao,input;
        /*
         * Sera usado um HashSet para caso que um professor seja docente de 2+ UCs
         * Ele so sera contado uma vez
         */
        Set<Professor> professores = new HashSet<>(); 
        
        int counter = 0;
        Curso c = new Curso();
        c = listaCurso.getCursoByDiretor(p.getNumMec());
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Diretor: %s    %s[%s]%s\t#", p.getNome(), Color.RED, c.getDesignacao(), Color.RESET);
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
                    in.nextLine(); // Limpar o Buffer
                    // Alterar o nome do Curso
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# Novo nome: ");
                    input = in.nextLine();
                    if (!check.isString(input)) {
                        return;
                    }
                    c.setDesignacao(input);
                    System.out.println(Color.GREEN_BOLD + "# Nome do Curso trocado com Sucesso" + Color.RESET);
                    break;
                case "2":
                    in.nextLine(); // Limpar o Buffer
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# 1 - Listar Professores");
                    System.out.println("# 2 - Listar Alunos");
                    input = in.nextLine();

                    if(!check.isInteger(input)){
                        return;
                    }
                    if(input.equals("1")){
                        for (UC u : c.getListaUC().getLista()) {
                            professores.addAll(u.getListDocente().getLista());
                        }
                        
                        System.out.format("# O Curso %s tem %d Professores\n",c.getDesignacao(),professores.size());
                        System.out.println("Pressione ENTER para continuar ...");
                        in.nextLine();
                        break;
                    }else if(input.equals("2")){
                        counter = c.getListaAluno().listarSimples(false);
                        System.out.format("# O Curso %s tem %d Alunos\n",c.getDesignacao(),counter);
                        System.out.println("Pressione ENTER para continuar ...");
                        in.nextLine();
                        break;
                    }else{
                        System.out.println("#ERROR Opcao invalida");
                        Thread.sleep(500);
                        break;
                    }
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }
}
