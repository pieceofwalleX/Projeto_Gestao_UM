package FrontEnd.Admin;

import java.util.Scanner;

import BackEnd.Curso;
import BackEnd.UC;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessor;
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
    public static void addCurso(ListUC listaUC, ListProfessor listaProf, ListAluno listaAluno, ListCurso listaCurso)
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
            curso.setNextId(curso.getId());
            Thread.sleep(400);
            return;
        }
        /*
         * Verificar se o professor ja e Diretor ou Regente
         */
        if (listaProf.isDiretor(profNum) || listaProf.isRegente(profNum)) {
            System.err.println("ERROR Professor ja e Diretor de Curso");
            curso.setNextId(curso.getId());
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
            curso.setNextId(curso.getId());
            Thread.sleep(400);
        }
        /*
         * Colocar o professor como Diretor do Curso
         */
        try {
            curso.setDiretor(prof);
        } catch (Exception e) {
            System.err.println("#ERROR Falha ao trocar o cargo");
            curso.setNextId(curso.getId());
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
    public static void editCurso(ListCurso listaCurso, ListUC listaUC, ListProfessor listaProf, ListAluno listaAluno, int id)
            throws InterruptedException {
        String opcao,input = null;
        Curso c = new Curso();
        UC u = new UC();
        Professor p = new Professor();
        c = listaCurso.getCursoById(id);
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
            System.out.println("#.........Gestao..Curso.........#");
            System.out.println("#                               #");
            System.out.format("# Curso [%s%s%s]                 #\n",Color.BLUE,c.getDesignacao(),Color.RESET);
            System.out.println("#                               #");
            System.out.println("#1. Editar Nome                 #");
            System.out.println("#2. Trocar Diretoria            #");
            System.out.println("#3. Adicionar UC                #");
            System.out.println("#4. Remover UC                  #");
            System.out.println("#5. Listar UCs                  #");
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
                    input = in.nextLine();
                    if(!check.isString(input)){
                        return;
                    }
                    c.setDesignacao(input);
                    System.out.println(Color.GREEN_BOLD + "# Nome do Curso trocado com Sucesso" + Color.RESET);
                    break;
                case "2":
                /*
                 * Trocar Diretor
                 */
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    System.out.println("# ID Novo Diretor: ");
                    input = in.next();
                    if(!check.isInteger(input)){
                        return;
                    }
                    /*
                     * Verificar se existe um professor com este ID
                     */
                    if(!listaProf.checkNumMec(input)){
                        return;
                    }
                    /*
                     * Verificar se o professor nao tem nenhum cargo associado
                     */
                    if(listaProf.isDiretor(input) || listaProf.isRegente(input)){
                        break;
                    }
                    /*
                     * Remover o cargo do Diretor atual caso exista
                     */
                    if(c.getDiretor() != null){
                        p = c.getDiretor();
                        p.setCargo("Normal");
                    }
                    /*
                     * Atribuir o cargo ao novo professor
                     */
                    p = listaProf.getProfByNum(input);
                    p.setCargo("Diretor");

                    c.setDiretor(p);

                    break;
                case "3":
                /*
                 * Adicionar UC
                 */
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    /*
                     * Verificar se ha UCs
                     */
                    if(listaUC.getLista().size() == 0){
                        System.out.println("# Nao existem UCs Registadas no Sistema");
                        System.out.println("Pressione ENTER para continuar ...");
                        in.nextLine();
                        in.nextLine();
                        break;
                    }
                    /*
                     * Listar as UCs que ainda nao estao na Lista de UCs do Curso
                     */
                    listaUC.listarUCSimples(c.getListaUC(),false);
                    System.out.println("\n# ID: ");
                    input = in.next();
                    if(!check.isInteger(input)){
                        return;
                    }
                    u = listaUC.getUCById(Integer.parseInt(input));
                    if(c.ucInList(Integer.parseInt(input))){
                        System.err.println(Color.RED + "ERROR Opcao Invalida #" + Color.RESET);
                        Thread.sleep(450);
                        break;
                    }
                    c.addUC(u);
                    break;
                case "4":
                /*
                 * Remover UC
                 */
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    /*
                     * Verificar se ha UCs
                     */
                    if(listaUC.getLista().size() == 0){
                        System.out.println("# Nao existem UCs Registadas no Sistema");
                        System.out.println("Pressione ENTER para continuar ...");
                        in.nextLine();
                        in.nextLine();
                        break;
                    }
                    listaUC.listarUCSimples(c.getListaUC(),true);
                    System.out.println("\n# ID: ");
                    input = in.next();
                    if(!check.isInteger(input)){
                        return;
                    }
                    c.removeUC(Integer.parseInt(input));
                    break;
                case "5":
                /*
                 * Listar UCs no curso
                 */
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.........Gestao..Curso.........#");
                    c.listarUC();
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

    public static void menu(ListCurso listaCurso, ListUC listaUC, ListProfessor listaProf, ListAluno listaAluno)
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
            System.out.println("#1. Registrar Curso            #");
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
