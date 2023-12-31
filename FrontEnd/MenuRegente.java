package FrontEnd;

import java.util.Scanner;

import BackEnd.Aluno;
import BackEnd.Curso;
import BackEnd.Listas.HashSumario;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessor;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;

public class MenuRegente {
        static final Scanner in = new Scanner(System.in);
        static final Verification check = new Verification();

    public static void adicionarAluno(ListCurso listaCurso,ListAluno listaAluno,Professor p) throws InterruptedException{
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        String input;
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
        System.out.println("#.........Gestao..Curso.........#");
        System.out.println("# ID: ");
        input = in.next();

        if(!check.isInteger(input)){
            System.err.println("#ERROR Caracter Invalido");
            aluno.setNumMec(input);
            Thread.sleep(400);
            return;
        }
        if(listaAluno.inLista(input)){
            System.err.println("#ERROR Este Numero ja pertence a um Aluno");
            Thread.sleep(400);
            return;
        }
        aluno.setNumMec(input);

        in.nextLine();

        System.out.println("# Nome:");
        aluno.setNome(in.nextLine());

        System.out.println("# Curso");
        listaCurso.listarCursosSimples();
        System.out.println("\n# ID:  ");
        input = in.next();

        if(!check.isInteger(input)){
            System.err.println("#ERROR Caracter Invalido");
            Thread.sleep(400);
            return;
        }

        curso = listaCurso.getCursoById(Integer.parseInt(input));
        aluno.setCurso(listaCurso.getCursoById(Integer.parseInt(input)));
        
        ListAluno listaAlunoCurso = curso.getListaAluno();
        listaAlunoCurso.adicionar(aluno);
        listaAluno.adicionar(aluno);

        System.out.println("#...............................#");
        System.out.format("# Resgistrado Aluno \n#NUM: %s \n#Nome: %s \n#Curso: %s\n",
                            aluno.getNumMec(),aluno.getNome(),aluno.getCurso().getDesignacao());
        System.out.println("#...............................#");
        Thread.sleep(550);
    }

     public static void gerirAluno(HashSumario listaSumarios, ListCurso listaCurso,ListAluno listaAluno,Professor p) throws InterruptedException{
        String opcao,input;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Regente: %s                       #",p.getNome());
            System.out.println("\n#                               #");
            System.out.println("#1. Adiconar Aluno              #");
            System.out.println("#2. Remover Aluno               #");
            System.out.println("#                               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();
            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Registra novo aluno
                    adicionarAluno(listaCurso,listaAluno, p);
                    break;
                case "2":
                    in.nextLine();
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# ID: ");
                    input = in.next();
                    if(!check.isInteger(input)){
                        System.err.println("#ERROR Caracter Invalido");
                        Thread.sleep(400);
                        return;
                    }
                    if(!listaAluno.inLista(input)){
                        System.err.println("#ERROR Aluno nao Existe");
                        Thread.sleep(400);
                        return;
                    }
                    listaSumarios.removeAluno(input);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
     }
    
     public static void menu(HashSumario listaSumarios,ListCurso listaCurso, ListUC listaUC,ListAluno listaAluno ,ListProfessor listaProf,Professor p) throws InterruptedException {
        String opcao,input;
        Curso c = new Curso();
        do {

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Regente: %s                    #",p.getNome());
            System.out.println("\n#                               #");
            System.out.println("#1. Gerir Alunos                #");
            System.out.println("#2. Consultar Assiduidade       #");
            System.out.println("#                               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    gerirAluno(listaSumarios,listaCurso,listaAluno, p);
                    break;
                case "2":
                    //Ver assiduidade
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# ID do Curso: ");
                    input = in.next();

                    if(!check.isInteger(input)){
                        return;
                    }

                    if(!listaCurso.checkCursoById(input)){
                        return;
                    }

                    c = listaCurso.getCursoById(Integer.parseInt(input));
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# ID do Aluno: ");
                    input = in.next();

                    if(!check.isInteger(input)){
                        return;
                    }

                    if(!c.getListaAluno().inLista(input)){
                        System.err.println("#ERROR Aluno nao existe #");
                        Thread.sleep(450);
                        return;
                    }

                    Aluno aluno = c.getListaAluno().getAlunoById(input);

                    System.out.println("# Faltas: " + aluno.getFaltas());
                    System.out.println("#...............................#");
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                break;
                    
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }
}
