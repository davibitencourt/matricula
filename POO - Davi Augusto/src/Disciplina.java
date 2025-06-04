package sistemaacademico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Disciplina {
    private String nome;
    private int cargaHoraria;

    private List<Matricula> matriculas;

    public Disciplina(String nome, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina n�o pode ser nulo ou vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga hor�ria deve ser positiva.");
        }
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.matriculas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina n�o pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga hor�ria deve ser positiva.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public List<Matricula> getMatriculas() {
        return new ArrayList<>(matriculas);
    }

    void adicionarMatricula(Matricula matricula) {
        if (matricula != null && !this.matriculas.contains(matricula)) {
            this.matriculas.add(matricula);
        }
    }

    void removerMatricula(Matricula matricula) {
        this.matriculas.remove(matricula);
    }

    @Override
    public String toString() {
        return "Disciplina [nome=" + nome + ", cargaHoraria=" + cargaHoraria + "h]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}