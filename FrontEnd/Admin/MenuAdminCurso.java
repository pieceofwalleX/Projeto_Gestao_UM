package FrontEnd.Admin;

import java.util.Scanner;

import BackEnd.Curso;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessore;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;
import FrontEnd.Color;
import FrontEnd.Verification;

public class MenuAdminCurso {
    static final Scanner in = new Scanner(System.in);
    static final Verification check = new Verification();
    /*
     * Adicionar um Curso
     */
    public static void addCurso(ListUC listaUC, ListProfessore listaProf, ListAluno listaAluno, ListCurso listaCurso)
            throws InterruptedException {
        in.nextLine();
        String profNum;
        Professor prof = new Professor();
        Curso curso = new Curso(true);
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
        System.out.println("#...........Gestao.UC...........#");
        System.out.println("# Descricao do Curso: ");
        curso.setDesignacao(in.next());
        System.out.println("# Num do Diretor do Curso: ");
        profNum = in.next();
        /*
         * Verificar se existe o numero de professor na lista de professores
         */
        if (!listaProf.checkNumMec(profNum)) {
            System.err.println("ERROR Falha ao encontrar professor");
            Thread.sleep(400);
            return;
        }
        /*
         * Verificar se o professor ja e Diretor ou Regente
         */
        if (listaProf.isDiretor(profNum) || listaProf.isRegente(profNum)) {
            System.err.println("ERROR Professor ja e Diretor de Curso");
            Thread.sleep(400);
            return;
        }

        prof = listaProf.getProfByNum(profNum);
        /*
         * Mudar o Cargo do Professor
         */
        try {
            prof.setCargo("Diretor");
        } catch (Exception e) {
            System.err.println("#ERROR Falha ao trocar o cargo");
            Thread.sleep(400);
        }
        /*
         * Colocar o professor como Diretor do Curso
         */
        try {
            curso.setDiretor(prof);
        } catch (Exception e) {
            System.err.println("#ERROR Falha ao trocar o cargo");
            Thread.sleep(400);
        }

        System.out.format("# Registado Curso: %d , %s , %s\t#\n", curso.getId(), profNum, curso.getDesignacao());
        System.out.println("#...............................#");
        Thread.sleep(600);
        listaCurso.adicionar(curso);
    }

    /*
     * Editar informacoes do curso
     */
    public static void editCurso(ListCurso listaCurso, ListUC listaUC, ListProfessore listaProf, ListAluno listaAluno, int id)
            throws InterruptedException {
        String opcao,input = null;
        Curso c = new Curso();
        c = listaCurso.getCursoById(id);
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Curso [%s%s%s]                    #\n",Color.BLUE,c.getDesignacao(),Color.RESET);
            System.out.println("#                               #");
            System.out.println("#1. Editar Nome                 #");
            System.out.println("#2. Adicionar UC                #");
            System.out.println("#3. Remover UC                  #");
            System.out.println("#4. Listar UCs                  #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Alterar o nome do Curso
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# Novo nome: ");
                    c.setDesignacao(in.next());
                    System.out.println(Color.GREEN_BOLD + "# Nome do Curso trocado com Sucesso" + Color.RESET);
                    break;
                case "2":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    listaUC.listarUCSimples();
                    System.out.println("# ID: ");
                    input = in.next();
                    if(!check.isInteger(input)){
                        return;
                    }
                    c.addUC(listaUC.getUCById(id));
                    break;
                case "3":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    listaUC.listarUCSimples();
                    System.out.println("# ID: ");
                    input = in.next();
                    if(!check.isInteger(input)){
                        return;
                    }
                    c.removeUC(Integer.parseInt(input));
                    break;
                case "4":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    c.listarUC();
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    public static void menu(ListCurso listaCurso, ListUC listaUC, ListProfessore listaProf, ListAluno listaAluno)
            throws InterruptedException {
        String opcao,id = "";
        Curso c = new Curso();
        Professor p = new Professor();
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Cursos: %d                     #\n", listaCurso.listarCursos(false));// Apresenta o numero de cursos
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar Curso            #");
            System.out.println("#2. Editar Informaceos Curso    #");
            System.out.println("#3. Listar Cursos               #");
            System.out.println("#4. Remover Curso               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Registro de novo Curso;
                    addCurso(listaUC, listaProf, listaAluno, listaCurso);
                    break;
                case "2":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# ID:");
                    id = in.next();

                    if(!check.isInteger(id)){
                        return;
                    }
                    editCurso(listaCurso, listaUC, listaProf, listaAluno,Integer.valueOf(id));
                    break;
                case "3":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    listaCurso.listarCursos(true);
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case "4":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("#........Eliminar..Curso........#");
                    System.out.println("# ID:");
                    id = in.next();
                    /*
                     * Verificar se o input e valido
                     */
                    if(!check.isInteger(id)){
                        return;
                    }
                    if(!listaCurso.checkCursoById(id)){
                        return;
                    }
                    /*
                     * Remover o cargo de Diretor
                     */
                    c = listaCurso.getCursoById(Integer.parseInt(id));
                    p = c.getDiretor();
                    p.setCargo("Normal");
                    /*
                     * Remover o Curso da lista
                     */
                    listaCurso.remove(Integer.parseInt(id));
                    

                    Thread.sleep(1000);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }

}
