package FrontEnd;

import java.util.Scanner;
import BackEnd.Listas.*;
import BackEnd.Professor.Professor;
import BackEnd.*;

public class MenuProfessor {
    static final Scanner in = new Scanner(System.in);
    static final Verification check = new Verification();

    public static void authProf(HashSumario listaSumarios, ListUC listaUC, ListProfessor listaProf,ListCurso listaCurso,ListAluno listaAluno)
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

    public static void newSumario(HashSumario listaSumarios, ListUC listaUC,Curso curso,ListProfessor listaProf, String id) throws InterruptedException {

        Sumario s = new Sumario();
        Content content = new Content();
        int idUC, tipoAula;
        boolean inUCList;
        String input;

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format("#.....Universidade.do.%sMinho%s.....#\n",Color.RED_BOLD,Color.RESET);
        System.out.println("#.......Professor.Sumario.......#");
        System.out.println("#                               #");
        System.out.println("#UC: ");
        idUC = in.nextInt();
        inUCList = curso.getListaUC().checkID(idUC);
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
            ListAluno presencas = new ListAluno(curso.getListaAluno());// Criar uma copia da lista principal
            System.out.println("# Selecione os Alunos em Falta | Digite \"Continuar\" para Avancar");
            presencas.listarSimples();
            do{
            System.out.println("# ---");
            input = in.next();
            
            if(input.toLowerCase().equals("continuar")){
                continue;
            }

            if(check.isInteger(input)){
                /*
                 * Verificar se o numero existe na lista
                 */
                if(!presencas.inLista(input)){
                    return;
                }
                Aluno aluno = presencas.getAlunoById(input);
                /*
                 * Adicionar falta ao Aluno
                 */
                aluno.addFalta();
                /*
                 * Remover da Lista de Presencas
                 */
                presencas.remove(input);
            }

            /*
             * Verificar se o usario digitou Continuar 
             * Transformamos o input em lowerCase para dar mais liberdade ao usario
             * De escrever "Continuar" como desejar
             */
            }while(!input.toLowerCase().equals("continuar"));
            content.setPresencas(presencas);
            in.nextLine();
            System.out.println("# Descricao: ");
            content.setDescricao(in.nextLine());
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

    public static void printSumarios(HashSumario listaSumarios,ListUC listaUC,ListProfessor listaProf,String id) throws InterruptedException {
        int idUC;
        String input,tipoAula = "";
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
        input = in.next();

        if(!check.isInteger(input)){
            return;
        }

        if (!s.verifyTipoAula(Integer.parseInt(input))) {
            System.err.println("#ERROR Tipo de aula Invalido");
            return;
        }
        
        switch (input) {
            case "1":
                tipoAula = "T";
                break;
            case "2":
                tipoAula = "TP";
                break;
            case "3":
                tipoAula = "P";
                break;
        }

        listaSumarios.listarSumarios(s, tipoAula,p.getNome(),true);
        System.out.println("#...............................#");
        System.out.println("Pressione ENTER para continuar ...");
        in.nextLine();
        in.nextLine();
    }



    public static void menu(HashSumario listaSumarios, ListUC listaUC, ListProfessor listaProf,ListCurso listaCurso,ListAluno listaAluno, String id) throws InterruptedException {

        String opcao,input;
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
            System.out.format("#......Professor..%s..[%s]......#\n",p.getNome(),p.getCargoString());
            System.out.println("#                                         #");
            System.out.println("#1. Criar Sumario                         #");
            System.out.println("#2. Lista de Sumarios                     #");
            if(p.getCargoString().equals("Regente")){
                System.out.println("#3. Menu Regente                          #");
            }else if(p.getCargoString().equals("Diretor")){
                System.out.println("#3. Menu Diretor                          #");
            }
            System.out.println("#                                         #");
            System.out.println("#0. Sair                                  #");
            System.out.println("#.........................................#");
            opcao = in.next();

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    /*
                     * Obter o Curso em que estamos a criar o Sumario
                     */
                    in.nextLine();
                    System.out.format("#.....Universidade.do.%sMinho%s.....#\n", Color.RED_BOLD, Color.RESET);
                    System.out.println("#.......Professor.Sumario.......#");
                    System.out.println("# ID: ");
                    input = in.next();

                    if(!check.isInteger(input)){
                        return;
                    }
                    Curso curso = listaCurso.getCursoById(Integer.parseInt(input));

                    newSumario(listaSumarios, listaUC,curso,listaProf, id);
                    break;
                case "2":
                    // Verificar se o usuario quer modificar uma UC ou um Curso
                    // Menu Gestao de Curso/UC
                    printSumarios(listaSumarios,listaUC,listaProf,id);
                    break;
                case "3":
                    if(p.getCargoString().equals("Regente")){                        
                        MenuRegente.menu(listaSumarios,listaCurso, listaUC,listaAluno ,listaProf, p);
                        break;
                    }else if(p.getCargoString().equals("Diretor")){
                        MenuDiretor.menu(listaCurso,listaUC,listaProf,listaAluno,p);
                        break;
                    }
                    break;
                default:
                    System.err.println("ERROR Opcao Invalida #");
                    break;
            }
            in.nextLine();
        } while (!opcao.equals("0"));
    }
}
