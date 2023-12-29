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
        in.nextLine();
        System.out.println("#.....Universidade-do-Minho.....#");
        System.out.println("#......Gestao..Professores.....#");
        System.out.println("# Numero do Professor: ");
        u.setNumMec(in.nextLine());
        //Verificar se o Numero de Professor ja Existe
        if (listaProfessore.checkNumMec(u.getNumMec())) {
            return;
        }
        System.out.println("# Nome do Professor: ");
        u.setNome(in.nextLine());
        System.out.println("# Digite a data de Inicio(ddmmyyyy)");
        data = in.nextLine();
        try{
            LocalDate.parse(data, DateTimeFormatter.ofPattern("d/M/yyyy"));
        }catch(DateTimeParseException e){
            System.out.println(Color.RED_BOLD +"#ERROR "+ data + " nao e uma data valida." + Color.RESET);
        }

        u.setDataInicio(data);
        System.out.println("#...............................#");
        System.out.format("# Resgistrado Professor \n#NUM: %s \n#Nome: %s \n#Cargo: %s \n#Data de Inicio: %s\n",
                u.getNumMec(), u.getNome(), u.getCargoString(), u.transformData());
        System.out.println("#...............................#");
        Thread.sleep(2250);
        listaProfessore.adicionar(u);
    }

    public static void removeProf(ListProfessore listaProf, ListUC listaUC) throws InterruptedException {
        String id;
        StringBuilder animationRegente = new StringBuilder("Removendo regencia");
        StringBuilder animationNormal = new StringBuilder("Removendo");
        UC uc = new UC();
        int maxDots = 3;
        int currentDot = 0;

        System.out.println("#-----Universidade-do-Minho-----#");
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
        if(listaProf.isRegente(id)) {
            uc = listaUC.getUCByRegente(id);
            uc.setRegente(null);
            //Animaca
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
        //Animacao
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

        int opcao = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("#.....Universidade-do-Minho.....#");
            System.out.println("#......Gestao..Professores......#");
            System.out.println("#                               #");
            System.out.println("#1. Resgistrar Professor        #");
            System.out.println("#2. Editar Dados Professor      #");
            System.out.println("#3. Listar Professores          #");
            System.out.println("#4. Eliminar Professor          #");
            System.out.println("#                               #");
            System.out.println("#0. Sair                        #");
            System.out.println("#...............................#");
            opcao = in.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    // Registro de novas UCs;
                    addProf(listaProf);
                    break;
                case 3:
                    System.out.println("#.....Universidade.do.Minho.....#");
                    System.out.println("#......Gestao..Professores......#");
                    listaProf.listarProf(true); // O boolean server para listar caso seja true, e apenas contar caso
                                                // seja false
                    System.out.println("Pressione ENTER para continuar ...");
                    in.nextLine();
                    in.nextLine();
                    break;
                case 4:
                    removeProf(listaProf, listaUC);
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
        } while (opcao != 0);
    }
}