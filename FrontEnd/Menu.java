package FrontEnd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import BackEnd.Curso;
import BackEnd.Listas.*;
import BackEnd.Professor.Professor;
import FrontEnd.Admin.MenuAdministrador;

public class Menu {

    static final Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws InterruptedException {
        // Inicializar as Listas
        ListUC listaUC = new ListUC();
        ListProfessore listaProf = new ListProfessore();
        ListAluno listaAluno = new ListAluno();
        ListCurso listaCurso = new ListCurso();
        HashSumario listaSumarios = new HashSumario();

        String opcao;
        try {
            do {

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
                System.out.println("#                               #");
                System.out.format("#%s1.%s Administracao               #\n", Color.BLUE, Color.RESET);
                System.out.format("#%s2.%s Menu Professor              #\n", Color.BLUE, Color.RESET);
                System.out.println("#                               #");
                System.out.format("#%s0.%s Sair                        #\n", Color.BLUE, Color.RESET);
                System.out.println("#...............................#");
                opcao = in.next();

                switch (opcao) {
                    case "0":
                        break;
                    case "1":
                        /*
                         * Muda para o metodo Auth no Menu Administrador
                         * Verifica se o codigo inserido e igual ao codigo de autenticacao;
                         */
                        MenuAdministrador.Auth(listaUC, listaProf, listaAluno, listaCurso);
                        break;
                    case "2":
                        /*
                         * Muda para o Menu dos Professores
                         */
                        MenuProfessor.authProf(listaSumarios, listaUC, listaProf, listaCurso, listaAluno);
                        break;
                    default:
                        System.err.format("%s#ERROR Opcao Invalida #%s\n", Color.RED, Color.RESET);
                        Thread.sleep(400);
                        main(args);
                }
            } while (!opcao.equals("0"));
        } catch (Exception e) {
            System.err.println("#ERROR Algo deu Errado #");
        }
    }
}