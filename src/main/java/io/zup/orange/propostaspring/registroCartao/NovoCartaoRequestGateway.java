package io.zup.orange.propostaspring.registroCartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoCartaoRequestGateway {

    private String id;
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private String idProposta;

    public NovoCartaoRequestGateway(String documento, String nome, String idProposta) {
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

    public Cartao toModel(CartaoGateway gateway) {
        this.id = gateway.cartaoById(idProposta).getId();
        System.out.println("INFORMAÇÕES ID===>"+id);
        return new Cartao(id,documento,nome,idProposta);
    }
}
