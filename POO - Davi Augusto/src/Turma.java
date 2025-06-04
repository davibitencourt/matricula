package sistemaacademico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma {
    private int id;
    private String nome;

    private List<Matricula> matriculas;

    private static int proximoId = 1; // Para gerar IDs automaticamente

    public Turma(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma n�o pode ser nulo ou vazio.");
        }
        this.id = proximoId++;
        this.nome = nome;
        this.matriculas = new ArrayList<>();
    }


    public Turma(int id, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma n�o pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.matriculas = new ArrayList<>();
        if (id >= proximoId) {
            proximoId = id + 1;
        }
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma n�o pode ser nulo ou vazio.");
        }
        this.nome = nome;
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
        return "Turma [id=" + id + ", nome=" + nome + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return id == turma.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}