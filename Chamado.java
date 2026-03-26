public class Chamado {
    private int id; // Conforme enunciado: inteiro
    private String bairro;
    private String descricao;
    private char nivelUrgencia; // '1' a '5'
    private Status status;

    public enum Status {
        ABERTO, EM_ATENDIMENTO, FINALIZADO
    }

    // Construtor padrão
    public Chamado() {
        this(0, "Nenhum", "Nenhum", '2', Status.ABERTO);
    }

    // Construtor completo
    public Chamado(int id, String bairro, String descricao, char nivelUrgencia, Status status) {
        this.id = id;
        this.bairro = bairro;
        this.descricao = descricao;
        this.nivelUrgencia = nivelUrgencia;
        this.status = status;
    }

    // Getters e Setters corrigidos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public char getNivelUrgencia() { return nivelUrgencia; }
    public void setNivelUrgencia(char nivelUrgencia) { this.nivelUrgencia = nivelUrgencia; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String dados() {
        return "ID: " + id +
               ", Bairro: " + bairro +
               ", Descrição: " + descricao +
               ", Nível de Urgência: " + nivelUrgencia +
               ", Status: " + status;
    }
}