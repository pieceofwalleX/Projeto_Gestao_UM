package FrontEnd.Admin;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import BackEnd.UC;
import BackEnd.Listas.ListProfessore;
import BackEnd.Listas.ListUC;
import BackEnd.Professor.Professor;
import FrontEnd.Color;

public class MenuAdminProfessores {
    static final Scanner in = new Scanner(System.in);

    public static void addProf(ListProfessore listaProfessore) throws InterruptedException {
        String data;
        Professor u = new Professor();

        in.nextLine(); // Limpar o buffer

        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#......Gestao..Professores.....#");

        System.out.println("# Numero do Professor: ");
        if (!u.setNumMec(in.next())) {
            return;
        }

        // Verificar se o Numero de Professor já Existe
        if (listaProfessore.checkNumMec(u.getNumMec())) {
            return;
        }

        // Limpar o buffer
        in.nextLine();

        System.out.println("# Nome do Professor: ");
        u.setNome(in.nextLine());

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

    public static void removeProf(ListProfessore listaProf, ListUC listaUC) throws InterruptedException {
        String id;
        StringBuilder animationRegente = new StringBuilder("Removendo regencia");
        StringBuilder animationNormal = new StringBuilder("Removendo");
        UC uc = new UC();
        int maxDots = 3;
        int currentDot = 0;

        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        in.nextLine();
        System.out.println("#......Gestao..Professores......#");
        System.out.println("#......Eliminar.Professore......#");
        System.out.println("# Id: ");
        id = in.next();
        if (!listaProf.checkNumMec(id)) {
            System.err.println("#ERROR Nao foi possivel encontrar o professor");
            Thread.sleep(400);
            return;
        }
        if (listaProf.isRegente(id)) {
            uc = listaUC.getUCByRegente(id);
            uc.setRegente(null);
            // Animaca
            for (int r = 0; r < 4; r++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.err.println("#ERROR Algo deu errado");
                }

                currentDot = (currentDot + 1) % (maxDots + 1);
                for (int i = 0; i < 3; i++) {
                    animationRegente.append(".");
                }
                System.out.print("\r" + animationRegente);
            }
        }

        listaProf.removePorf(id);
        // Animacao
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

    public static void menu(ListProfessore listaProf, ListUC listaUC) throws InterruptedException {

        String opcao;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
            System.out.println("#......Gestao..Professores......#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar Professor        #");
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
                case "3":
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#......Gestao..Professores......#");
                    listaProf.listarProf(true); // O boolean server para listar caso seja true, e apenas contar caso
                                                // seja false
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case "4":
                    removeProf(listaProf, listaUC);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (!opcao.equals("0"));
    }
}
