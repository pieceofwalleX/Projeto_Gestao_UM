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

    public static void addUC(ListUC listaUC, ListProfessore listaProf) throws InterruptedException {
        in.nextLine();
        String profNum;
        UC uc = new UC(true);
        Professor prof = new Professor();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#...........Gestao.UC...........#");
        System.out.println("# Descricao da UC: ");
        uc.setDesignacao(in.nextLine());
        System.out.println("# Num do Regente da UC: ");
        profNum = in.nextLine();
        /*
         * Nos proximos 2 if`s sera verificado se existe um professor pelo num
         * mecanografico,
         * Caso esse 'Num' esteja associdado a um professor verificamos se o mesmo ja e
         * Regente;
         */
        if (!listaProf.checkNumMec(profNum)) {
            System.err.println("ERROR Falha ao encontrar professor");
            Thread.sleep(400);
            return;
        }
        if (listaProf.isRegente(profNum) || listaProf.isDiretor(profNum)) {
            System.err.println("ERROR Professor ja e Regente/Diretor");
            Thread.sleep(400);
            return;
        }
        prof = listaProf.getProfByNum(profNum);
        prof.setCargo("Regente");
        uc.setRegente(listaProf.getProfByNum(profNum));
        System.out.format("# Registada UC: %d , %s , %s\t#\n", uc.getId(), profNum, uc.getDesignacao());
        System.out.println("#...............................#");
        Thread.sleep(800);
        listaUC.adicionar(uc);
    }

    public static void gestaoEquipaDocente(UC uc, ListProfessore listaProf) throws InterruptedException{
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
                        return;
                    }
                    /*
                     * Verificamos se o professor existe
                     */
                    if(!listaProf.checkNumMec(id)){
                        return;
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

    public static void gestaoUC(ListUC listaUC, ListProfessore listaProf) throws InterruptedException {

        String opcao,id;
        UC uc = new UC();
        Professor professor = new Professor();
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#...........Gestao.UC...........#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar UC               #");
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
