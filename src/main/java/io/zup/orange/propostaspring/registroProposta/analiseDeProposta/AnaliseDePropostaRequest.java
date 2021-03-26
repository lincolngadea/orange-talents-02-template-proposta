package io.zup.orange.propostaspring.registroProposta.analiseDeProposta;

import io.zup.orange.propostaspring.registroProposta.Proposta;

public class AnaliseDePropostaRequest {

    private String documento;
    private String nome;
    private String idProposta;

    public AnaliseDePropostaRequest(Proposta proposta) {
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
