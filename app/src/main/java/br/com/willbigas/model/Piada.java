package br.com.willbigas.model;

public class Piada {

    private Integer id;
    private String pergunta;
    private String resposta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Piada{" + "id=" + id + ", pergunta=" + pergunta + ", resposta=" + resposta + '}';
    }
}
