package FrontEnd.Admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import BackEnd.Curso;
import BackEnd.UC;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListProfessor;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;
import FrontEnd.Color;
import FrontEnd.Verification;

public class MenuAdminProfessores {
    static final Scanner in = new Scanner(System.in);
    static final Verification check = new Verification();

    public static void addProf(ListProfessor listaProfessore) throws InterruptedException {
        String data,input;
        Professor u = new Professor();

        in.nextLine(); // Limpar o buffer

        System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
        System.out.println("#......Gestao..Professores.....#");

        System.out.println("# Numero do Professor: ");
        input = in.next();
        if (!u.setNumMec(input)) {
            return;
        }

        // Verificar se o Numero de Professor já Existe
        if (listaProfessore.checkNumMec(input)) {
            return;
        }

        // Limpar o buffer
        in.nextLine();


        System.out.println("# Nome do Professor: ");
        input = in.nextLine();
        if(!check.isString(input)){
            return;
        }
        u.setNome(input);

        System.out.println("# Digite a data de Inicio (ddMMyyyy): ");
        data = in.nextLine();
        try {
            LocalDate dataInicio = LocalDate.parse(data, DateTimeFormatter.ofPattern("ddMMyyyy"));
            if (!u.setDataInicio(dataInicio)) {
                System.err.println(Color.RED + "#ERROR Data Invalida" + Color.RESET);
                Thread.sleep(450);
                return;
            }

            System.out.println("#...............................#");
            System.out.format("# Resgistrado Professor \n#NUM: %s \n#Nome: %s \n#Cargo: %s \n#Data de Inicio: %s\n",
                    u.getNumMec(), u.getNome(), u.getCargoString(), u.transformData());
            System.out.println("#...............................#");

            listaProfessore.adicionar(u);
        } catch (DateTimeParseException e) {
            System.out.println(Color.RED_BOLD + "#ERROR " + data + " não é uma data válida." + Color.RESET);
        }

        Thread.sleep(450);
    }

    public static void removeProf(ListProfessor listaProf, ListUC listaUC, ListCurso listaCurso)
            throws InterruptedException {
        String id;
        Curso c = new Curso();
        UC uc = new UC();
        Professor prof = new Professor();
        /*
         * Vars para animacao de remover
         */
        int maxDots = 3;
        int currentDot = 0;
        StringBuilder animationCargo = new StringBuilder("Removendo cargo");
        StringBuilder animationNormal = new StringBuilder("Removendo");

        System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
        in.nextLine();
        System.out.println("#......Gestao..Professores......#");
        System.out.println("#......Eliminar.Professore......#");
        System.out.println("# Id: ");
        id = in.next();
            
        if(!check.isInteger(id)){
            return;
        }

        if (!listaProf.checkNumMec(id)) {
            System.err.println("#ERROR Nao foi possivel encontrar o professor");
            Thread.sleep(400);
            return;
        }

        prof = listaProf.getProfByNum(id);

        if (listaProf.isRegente(id)) {
            uc = listaUC.getUCByRegente(id);
            uc.setRegente(null);
            // Animacao
            for (int r = 0; r < 4; r++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.err.println("#ERROR Algo deu errado");
                }

                currentDot = (currentDot + 1) % (maxDots + 1);
                for (int i = 0; i < 3; i++) {
                    animationCargo.append(".");
                }
                System.out.print("\r" + animationCargo);
            }
        }
        /*
         * Caso seja diretor remover o Cargo
         */
        if (listaProf.isDiretor(id)) {
            c = listaCurso.getCursoByDiretor(id);
            c.setDiretor(null);
            // Animacao
            for (int r = 0; r < 4; r++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.err.println("#ERROR Algo deu errado");
                }

                currentDot = (currentDot + 1) % (maxDots + 1);
                for (int i = 0; i < 3; i++) {
                    animationCargo.append(".");
                }
                System.out.print("\r" + animationCargo);
            }
        }
        /*
         * Remover o professor das UCs em que ele pertence a Equipa Docente
         */
        prof.removeProfFromUC(id);
        /*
         * Apagar o Professor
         */
        listaProf.removePorf(id);
        // Animacao
        System.out.println("\n");
        for (int r = 0; r < 4; r++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.err.println("#ERROR Algo deu errado");
            }

            currentDot = (currentDot + 1) % (maxDots + 1);
            for (int i = 0; i < 3; i++) {
                animationNormal.append(".");
            }
            System.out.print("\r" + animationNormal);
        }

    }

    public static void editarDados(Professor p, ListProfessor listaProf, ListUC listaUC) throws InterruptedException {
        String opcao, input;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
            System.out.println("#......Gestao..Professores......#");
            System.out.format("#........Editar...%s........#\n", p.getNome());
            System.out.println("#                               #");
            System.out.println("#                               #");
            System.out.println("#1. Editar Num                  #");
            System.out.println("#2. Editar Nome                 #");
            System.out.println("#3. Editar Data Incio de Funcoes#");
            System.out.println("#                               #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#......Gestao..Professores......#");
                    System.out.println("# Novo Id: ");
                    input = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if (!check.isInteger(input)) {
                        break;
                    }
                    /*
                     * Verificar se o ID ja esta associado
                     */
                    if (listaProf.checkNumMec(input)) {
                        break;
                    }
                    /*
                     * Se for valido , alterar o ID ao professor e
                     * Alterar o ID das UCs/Curso associadas(o)
                     */
                    if (p.setNumMec(input)) {
                        System.out.println("# Numero alterado com sucesso");
                        Thread.sleep(400);
                    }
                    break;
                case "2":
                    in.nextLine();
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#......Gestao..Professores......#");
                    System.out.println("# Novo Nome: ");
                    input = in.nextLine();

                    if(!check.isString(input)){
                        break;
                    }
                    
                    p.setNome(input);
                    break;
                case "3":
                    in.nextLine();
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#......Gestao..Professores......#");
                    System.out.println("# Nova Data: ");
                    input = in.nextLine();
                    /*
                     * Transformar input em LocalDate
                     */
                    LocalDate dataInicio = LocalDate.parse(input, DateTimeFormatter.ofPattern("ddMMyyyy"));
                    /*
                     * Verificar se a data e valida
                     */
                    if (!p.setDataInicio(dataInicio)) {
                        System.err.println(Color.RED + "#ERROR Data Invalida" + Color.RESET);
                        Thread.sleep(450);
                        return;
                    }

                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    public static void menu(ListProfessor listaProf, ListUC listaUC, ListCurso listaCurso)
            throws InterruptedException {

        String opcao, id;
        Professor professor = new Professor();
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
            System.out.println("#......Gestao..Professores......#");
            System.out.println("#                               #");
            System.out.println("#1. Registrar Professor        #");
            System.out.println("#2. Editar Dados Professor      #");
            System.out.println("#3. Listar Professores          #");
            System.out.println("#4. Eliminar Professor          #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Registro de novas UCs;
                    addProf(listaProf);
                    break;
                case "2":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#......Gestao..Professores......#");
                    System.out.println("# Id Professor: ");
                    id = in.next();
                    /*
                     * Verificamos se o input e um inteiro
                     */
                    if (!check.isInteger(id)) {
                        return;
                    }
                    if (!listaProf.checkNumMec(id)) {
                        return;
                    }
                    professor = listaProf.getProfByNum(id);
                    editarDados(professor, listaProf, listaUC);

                    break;
                case "3":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#......Gestao..Professores......#");
                    listaProf.listarProf(true); // O boolean server para listar caso seja true, e apenas contar caso seja false
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case "4":
                    removeProf(listaProf, listaUC, listaCurso);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }
}
