package sistema;

import java.time.LocalDate;

public class Sistema {

    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("111.222.333-44", "Jo�o Silva");
        Aluno aluno2 = new Aluno("555.666.777-88", "Maria Oliveira");

        Disciplina dscAlgoritmos = new Disciplina("Algoritmos e Programa��o", 80);
        Disciplina dscEstrutura = new Disciplina("Estrutura de Dados", 80);
        Disciplina dscBD = new Disciplina("Banco de Dados", 60);

        Turma turmaA2023 = new Turma("Turma A - 2023/2");
        Turma turmaB2023 = new Turma(202302, "Turma B - 2023/2 - Noturno");


        System.out.println("--- Cen�rio de Matr�culas ---");

        Matricula mat1 = null;
        try {
            mat1 = new Matricula(aluno1, dscAlgoritmos, turmaA2023, LocalDate.now());
            System.out.println("Criada: " + mat1);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar matr�cula: " + e.getMessage());
        }

        Matricula mat2 = new Matricula(aluno2, dscAlgoritmos, turmaA2023, LocalDate.now().minusDays(1));
        System.out.println("Criada: " + mat2);

        Matricula mat3 = new Matricula(aluno1, dscEstrutura, turmaB2023, LocalDate.now());
        System.out.println("Criada: " + mat3);

        System.out.println("\n--- Estados das Matr�culas Iniciais ---");
        if (mat1 != null) System.out.println(mat1);
        System.out.println(mat2);
        System.out.println(mat3);

        System.out.println("\n--- A��es nas Matr�culas ---");
        if (mat1 != null) {
            mat1.confirmar(); 
            mat1.aprovar(); 
        }

        mat2.confirmar(); 
        mat2.trancar();

        mat3.confirmar(); 
        mat3.reprovar(); 

        System.out.println("\n--- Estados Finais das Matr�culas ---");
        if (mat1 != null) System.out.println(mat1);
        System.out.println(mat2);
        System.out.println(mat3);

        System.out.println("\n--- Informa��es dos Alunos ---");
        System.out.println(aluno1 + " tem " + aluno1.getMatriculas().size() + " matr�culas:");
        for (Matricula m : aluno1.getMatriculas()) {
            System.out.println("  - " + m.getDisciplina().getNome() + " (" + m.getStatus().getDescricao() + ")");
        }

        System.out.println(aluno2 + " tem " + aluno2.getMatriculas().size() + " matr�culas:");
        for (Matricula m : aluno2.getMatriculas()) {
            System.out.println("  - " + m.getDisciplina().getNome() + " (" + m.getStatus().getDescricao() + ")");
        }

        System.out.println("\n--- Informa��es das Disciplinas ---");
        System.out.println(dscAlgoritmos + " tem " + dscAlgoritmos.getMatriculas().size() + " matr�culas.");
        for(Matricula m : dscAlgoritmos.getMatriculas()){
            System.out.println("  - Aluno: " + m.getAluno().getNome() + ", Status: " + m.getStatus());
        }

        System.out.println("\n--- Informa��es das Turmas ---");
        System.out.println(turmaA2023 + " tem " + turmaA2023.getMatriculas().size() + " matr�culas.");
        for(Matricula m : turmaA2023.getMatriculas()){
            System.out.println("  - Aluno: " + m.getAluno().getNome() + " na disciplina " + m.getDisciplina().getNome());
        }
    }
}