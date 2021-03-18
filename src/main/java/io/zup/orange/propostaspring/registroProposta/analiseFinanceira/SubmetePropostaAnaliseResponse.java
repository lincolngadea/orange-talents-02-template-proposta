package io.zup.orange.propostaspring.registroProposta.analiseFinanceira;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.zup.orange.propostaspring.registroProposta.PropostaStatus;
import org.springframework.data.web.JsonPath;

public class SubmetePropostaAnaliseResponse {

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
