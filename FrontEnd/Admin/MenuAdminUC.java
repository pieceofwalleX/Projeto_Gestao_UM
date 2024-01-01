package FrontEnd.Admin;

import java.util.Scanner;

import BackEnd.UC;
import BackEnd.Listas.*;
import BackEnd.Professor.Professor;
import FrontEnd.Color;
import FrontEnd.Verification;

public class MenuAdminUC {
    static final Scanner in = new Scanner(System.in);
    static final Verification check = new Verification();

    public static void addUC(ListUC listaUC, ListProfessor listaProf) throws InterruptedException {
        in.nextLine();
        String profNum,input;
        UC uc = new UC(true);
        Professor prof = new Professor();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#...........Gestao.UC...........#");
        System.out.println("# Descricao da UC: ");
        input = in.nextLine();

        if(!check.isString(input)){
            return;
        }
        uc.setDesignacao(input);

        System.out.println("# Num do Regente da UC: ");
        input = in.next();
        if(!check.isInteger(input)){
            return;
        }
        /*
         * Nos proximos 2 if`s sera verificado se existe um professor pelo num
         * mecanografico,
         * Caso esse 'Num' esteja associdado a um professor verificamos se o mesmo ja e
         * Regente;
         */
        if (!listaProf.checkNumMec(input)) {
            System.err.println("ERROR Falha ao encontrar professor");
            uc.setNextId(uc.getId());
            Thread.sleep(400);
            return;
        }
        if (listaProf.isRegente(input) || listaProf.isDiretor(input)) {
            System.err.println("ERROR Professor ja e Regente/Diretor");
            uc.setNextId(uc.getId());
            Thread.sleep(400);
            return;
        }
        /*
         * Mudar o cargo do professor para Regente
         */
        prof = listaProf.getProfByNum(input);
        prof.setCargo("Regente");
        /*
         * Adicionar a UC ao servico docente do professor e
         * Adicionar o Professor a Lista de Professores da UC
         */
        prof.addServico(uc);
        uc.adicionarProf(prof);

        uc.setRegente(listaProf.getProfByNum(input));
        System.out.format("# Registada UC: %d , %s , %s\t#\n", uc.getId(), prof.getNumMec(), uc.getDesignacao());
        System.out.println("#...............................#");
        Thread.sleep(800);
        listaUC.adicionar(uc);
    }

    public static void gestaoEquipaDocente(UC uc, ListProfessor listaProf) throws InterruptedException{
        String opcao,id;
        Professor professor = new Professor();
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#...........Gestao.UC...........#");
            System.out.println("#........Equipa..Docente........#");
            System.out.println("#                               #");
            System.out.println("#1. Adiconar Professor a Equipa #");
            System.out.println("#2. Remover Professor da Equipa #");
            System.out.println("#3. Listar Equipa Docente       #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("#.......Adicionar.Docente.......#");
                    System.out.println("# Id: ");
                    id = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if(!check.isInteger(id)){
                        break;
                    }
                    /*
                     * Verificamos se o professor existe
                     */
                    if(!listaProf.checkNumMec(id)){
                        break;
                    }
                    if(uc.inListaDocente(id)){
                        break;
                    }
                    /*
                     * Caso exista atualizamos o servico docente do professor e
                     * Atualizamos a Equipa Docente da UC
                     */
                    professor = listaProf.getProfByNum(id);
                    professor.addServico(uc);
                    uc.adicionarProf(professor);

                    Thread.sleep(500);

                    break;
                case "2":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("#........Remover.Docente........#");
                    System.out.println("# Id Professor: ");
                    id = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if(!check.isInteger(id)){
                        return;
                    }
                    /*
                     * Remover
                     */
                    professor = listaProf.getProfByNum(id);
                    professor.removeServico(uc.getId());
                    uc.removeProf(id);
                    break;
                case "3":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("#............Docentes...........#");
                    uc.listarEquipaDocente();
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
    public static void editarUC(ListUC listaUC, ListProfessor listaProf,UC uc) throws InterruptedException{
        String opcao,input;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#...........Gestao.UC...........#");
            System.out.println("#                               #");
            System.out.println("#1. Editar Designacao           #");
            System.out.println("#2. Trocar Regente              #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "1":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("# Nova Designacao: ");
                    input = in.next();

                    if(!check.isString(input)){
                        return;
                    }

                    uc.setDesignacao(input);

                    break;
                case "2":

                    break;
                default:
                    break;
            }
        }while(!opcao.equals("0"));
    }

    public static void gestaoUC(ListUC listaUC, ListProfessor listaProf) throws InterruptedException {

        String opcao,id;
        UC uc = new UC();
        Professor professor = new Professor();
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#...........Gestao.UC...........#");
            System.out.println("#                               #");
            System.out.println("#1. Registrar UC               #");
            System.out.println("#2. Editar Informaceos UC       #");
            System.out.println("#3. Equipa docente              #");
            System.out.println("#4. Listar UCs                  #");
            System.out.println("#5. Remover UC                  #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Registro de novas UCs;
                    addUC(listaUC, listaProf);
                    break;
                case "2":
                    //Editar Informacoes da UC
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("# Id da UC: ");
                    id = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if(!check.isInteger(id)){
                        return;
                    }
                    if(!listaUC.checkID(Integer.parseInt(id))){
                        return;
                    }
                    uc = listaUC.getUCById(Integer.parseInt(id));
                    editarUC(listaUC, listaProf,uc);
                    break;
                case "3":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("# Id da UC: ");
                    id = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if(!check.isInteger(id)){
                        return;
                    }
                    if(!listaUC.checkID(Integer.parseInt(id))){
                        return;
                    }
                    uc = listaUC.getUCById(Integer.parseInt(id));
                    gestaoEquipaDocente(uc, listaProf);
                    
                    break;
                case "4":
                    System.out.format("#..............Universidade.do.%sMinho%s..............#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#....................Gestao.UC....................#");
                    listaUC.listarUC(true); // O boolean server para listar caso seja true, e apenas contar caso seja
                                            // false
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case "5":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                    System.out.println("#...........Gestao.UC...........#");
                    System.out.println("#..........Eliminar.UC..........#");
                    System.out.println("# Id: ");
                    id = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if(!check.isInteger(id)){
                        return;
                    }
                    /*
                     * Remover o cargo do Professor Regente
                     */
                    uc = listaUC.getUCById(Integer.parseInt(id));
                    professor = uc.getRegente();
                    professor.setCargo("Normal");

                    listaUC.removeUC(Integer.parseInt(id));
                    Thread.sleep(500);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }
}
