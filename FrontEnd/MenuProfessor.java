package FrontEnd;

import java.util.Scanner;
import BackEnd.Listas.*;
import BackEnd.Professor.Professor;
import BackEnd.*;

public class MenuProfessor {
    static final Scanner in = new Scanner(System.in);

    public static void authProf(HashSumario listaSumarios, ListUC listaUC, ListProfessore listaProf,ListCurso listaCurso,ListAluno listaAluno)
            throws InterruptedException {

        String id;

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#.Codigo de Professor >");

        id = in.nextLine();

        if (listaProf.checkNumMec(id)) {
            System.err.println("#AVISO Acesso Autorizado #");
            Thread.sleep(500);
            menu(listaSumarios, listaUC, listaProf,listaCurso,listaAluno, id);
        } else {
            System.out.println(Color.RED + "#Error Codigo Invalido #" + Color.RESET);
            Thread.sleep(800);
            return;
        }
        System.out.println("#...............................#");
    }

    public static void newSumario(HashSumario listaSumarios, ListUC listaUC, ListProfessore listaProf, String id)
            throws InterruptedException {

        Sumario s = new Sumario();
        Content content = new Content(); // Por Fazer
        int idUC, tipoAula;
        boolean inUCList;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#.......Professor.Sumario.......#");
        System.out.println("#                               #");
        System.out.println("#UC: ");
        idUC = in.nextInt();
        inUCList = listaUC.checkID(idUC);
        in.nextLine();
        if (inUCList) {
            s.setIdDisciplina(idUC);
            s.setIdProfessor(id);
            System.out.println("#..............Tipo..Aula..............");
            System.out.println("# 1.Teorica 2.Teorico-Pratica 3.Pratica");
            tipoAula = in.nextInt();
            if (!s.verifyTipoAula(tipoAula)) {
                System.err.println("#ERROR Tipo de aula Invalido");
                return;
            }
            s.setTipoAula(tipoAula);
            in.nextLine(); //Limpar o buffer
            /*
             * Adicionar Conteudo
             * Aqui sera adicionado uma Lista de Alunos, sera usada a lista de Cursos
             * Ao selecionar o Curso a lista de alunos desse curso sera usada.
             * Verificacao:
             *  Ver se o Curso contem a UC e Se o Professor pertence a equipa Docente
             */
            System.out.println("# Descricao: ");
            s.setDescricao(in.nextLine());
            listaSumarios.add(s, content);
            System.out.println("#...............................#");
            System.out.println("# Resgistrado Sumario           #");
            listaSumarios.get(s);
            System.out.println("#...............................#");

            Thread.sleep(2200);
        } else {
            System.err.println("#ERROR UC nao existe");
            Thread.sleep(500);
            return;
        }
    }

    public static void printSumarios(HashSumario listaSumarios,ListUC listaUC,ListProfessore listaProf,String id) {
        int idUC, tipoAula;
        Sumario s = new Sumario();
        Professor p = new Professor();
        p = listaProf.getProfByNum(id);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#.......Professor.Sumario.......#");
        System.out.println("#                               #");
        System.out.println("#UC: ");
        idUC = in.nextInt();
        if(!listaUC.checkID(idUC)){
            System.err.println("#ERROR Id da UC nao existe");
            return;
        }
        System.out.println("#..............Tipo..Aula..............");
        System.out.println("# 1.Teorica 2.Teorico-Pratica 3.Pratica");
        tipoAula = in.nextInt();
        if (!s.verifyTipoAula(tipoAula)) {
            System.err.println("#ERROR Tipo de aula Invalido");
            return;
        }
        listaSumarios.listarSumarios(s, tipoAula,p.getNome(),true);
        System.out.println("#...............................#");
        System.out.println("Pressione ENTER para continuar ...");
        in.nextLine();
        in.nextLine();
    }



    public static void menu(HashSumario listaSumarios, ListUC listaUC, ListProfessore listaProf,ListCurso listaCurso,ListAluno listaAluno, String id)
            throws InterruptedException {

        String opcao;
        Professor p = new Professor();

        try{
            p = listaProf.getProfByNum(id);
        }catch(Exception e){
            System.err.println("#ERROR Falha ao obter informacao do professor");
        }

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.format("#..........Universidade.do.%sMinho%s..........#\n",Color.RED_BOLD,Color.RESET);
            System.out.format("#......Professor.%s..[%s].......#\n",p.getNome(),p.getCargoString());
            System.out.println("#                                         #");
            System.out.println("#1. Criar Sumario                         #");
            System.out.println("#2. Lista de Sumarios                     #");
            if(p.getCargoString() == "Regente"){
                System.out.println("#3. Editar UC                             #");
            }else if(p.getCargoString() == "Diretor"){
                System.out.println("#3. Editar Curso                          #");
            }
            System.out.println("#                                         #");
            System.out.println("#0. Sair                                  #");
            System.out.println("#.........................................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    // Menu Gestao de Projefores;
                    newSumario(listaSumarios, listaUC, listaProf, id);
                    break;
                case "2":
                    // Verificar se o usuario quer modificar uma UC ou um Curso
                    // Menu Gestao de Curso/UC
                    printSumarios(listaSumarios,listaUC,listaProf,id);
                    break;
                case "3":
                    if(p.getCargoString() == "Regente"){
                        //Menu Regente
                    }else if(p.getCargoString() == "Diretor"){
                        MenuDiretor.menu(listaCurso,listaUC,listaProf,listaAluno,p);
                    }
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }

        } while (!opcao.equals("0"));
    }
}
