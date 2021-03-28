package io.zup.orange.propostaspring.registroCartao.bloqueios;

public class BloqueioRequestGateway {
    private String sistemaResponsavel;

    public BloqueioRequestGateway(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    @Deprecated
    public BloqueioRequestGateway() {
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

}
