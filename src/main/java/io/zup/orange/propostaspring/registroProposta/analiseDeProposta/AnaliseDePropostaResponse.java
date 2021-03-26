package io.zup.orange.propostaspring.registroProposta.analiseDeProposta;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.zup.orange.propostaspring.registroProposta.PropostaStatus;

public class AnaliseDePropostaResponse {

    private String documento;
    private String nome;
    @JsonProperty("resultadoSolicitacao")
    private String status;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public PropostaStatus toModel() {
        return getStatus().equals("COM_RESTRICAO") ? PropostaStatus.NAO_ELEGIVEL : PropostaStatus.ELEGIVEL;
    }
}
