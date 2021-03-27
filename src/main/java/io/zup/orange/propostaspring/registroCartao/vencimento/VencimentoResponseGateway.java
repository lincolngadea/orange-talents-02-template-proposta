package io.zup.orange.propostaspring.registroCartao.vencimento;

import java.time.LocalDateTime;

public class VencimentoResponseGateway {

    private String id;
    private String dia;
    private String dataDeCriacao;

    public String getId() {
        return id;
    }

    public String getDia() {
        return dia;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Vencimento toModel() {
        return new Vencimento(id,dia,LocalDateTime.parse(dataDeCriacao));
    }
}
