package io.zup.orange.propostaspring.registroCartao.vencimento;

import java.time.LocalDateTime;

public class Vencimento {
    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public String toString() {
        return "Vencimento{" +
                "id='" + id + '\'' +
                ", dia=" + dia +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
