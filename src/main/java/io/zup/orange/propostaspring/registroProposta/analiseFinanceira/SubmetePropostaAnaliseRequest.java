package io.zup.orange.propostaspring.registroProposta.analiseFinanceira;

import io.zup.orange.propostaspring.registroProposta.Proposta;

import java.util.UUID;

public class SubmetePropostaAnaliseRequest {

    private String documento;
    private String nome;
    private String idProposta;

    public SubmetePropostaAnaliseRequest(Proposta proposta) {
        documento = proposta.getDocumento();
        nome = proposta.getNome();
        idProposta = proposta.getId().toString();
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
}
