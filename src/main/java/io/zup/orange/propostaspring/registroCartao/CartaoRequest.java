package io.zup.orange.propostaspring.registroCartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CartaoRequest {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private String idProposta;

    public CartaoRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "CartaoRequest{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
