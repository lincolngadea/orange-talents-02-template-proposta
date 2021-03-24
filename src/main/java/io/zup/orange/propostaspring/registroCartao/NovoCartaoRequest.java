package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registroProposta.Proposta;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class NovoCartaoRequest {

    private String id;
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private String idProposta;

    public NovoCartaoRequest(String documento, String nome, String idProposta) {
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

    public Cartao toModel(String idCartao){
        this.id = idCartao;
        return new Cartao(id,documento,nome,idProposta);
    }
}
