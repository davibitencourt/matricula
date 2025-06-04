package sistemaacademico;


public enum Status {
    MATRICULADO("Matriculado"),
    CURSANDO("Cursando"),
    CONCLUIDO("Concluido"),
    TRANCADO("Trancado"),
    REPROVADO("Reprovado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}