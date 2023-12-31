package BackEnd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

import BackEnd.Listas.HashSumario;
import BackEnd.Listas.ListAluno;
import BackEnd.Listas.ListCurso;
import BackEnd.Listas.ListUC;
import BackEnd.Listas.ListProfessor;

public class File {

    public static void saveData(HashSumario sumarios, ListAluno alunos, ListCurso cursos, ListProfessor professores,ListUC ucs) throws IOException {

        try {
            FileOutputStream fileProfessores = new FileOutputStream("Professores.txt");
            if(fileProfessores != null){
                professores.save(fileProfessores);
            }
            FileOutputStream fileSumarios = new FileOutputStream("Sumarios.txt");
            if(fileSumarios != null){
                sumarios.save(fileSumarios);
            }
            FileOutputStream fileAlunos = new FileOutputStream("Alunos.txt");
            if(fileAlunos != null){
                alunos.save(fileAlunos);
            }
            FileOutputStream fileCursos = new FileOutputStream("Cursos.txt");
            if(fileCursos != null){
                cursos.save(fileCursos);
            }
            FileOutputStream fileUCs = new FileOutputStream("UCs.txt");
            if(fileUCs != null){
                ucs.save(fileUCs);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(HashSumario sumarios, ListAluno alunos, ListCurso cursos, ListProfessor professores,ListUC ucs) throws IOException, ClassNotFoundException {

        try {
            FileInputStream fileProfessores = new FileInputStream("Professores.txt");
            if(fileProfessores != null){
                professores.load(fileProfessores);
            }
            FileInputStream fileSumarios = new FileInputStream("Sumarios.txt");
            if(fileSumarios != null){
                sumarios.load(fileSumarios);
            }
            FileInputStream fileAlunos = new FileInputStream("Alunos.txt");
            if(fileAlunos != null){
                alunos.load(fileAlunos);
            }
            FileInputStream fileCursos = new FileInputStream("Cursos.txt");
            if(fileCursos != null){
                cursos.load(fileCursos);
            }
            FileInputStream fileUCs = new FileInputStream("UCs.txt");
            if(fileUCs != null){
                ucs.load(fileUCs);
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }
}
