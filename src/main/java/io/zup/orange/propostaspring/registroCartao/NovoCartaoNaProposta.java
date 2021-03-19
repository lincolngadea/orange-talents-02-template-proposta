package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registroCartao.avisos.Avisos;
import io.zup.orange.propostaspring.registroCartao.bloqueios.Bloqueios;
import io.zup.orange.propostaspring.registroCartao.carteiras.Carteiras;
import io.zup.orange.propostaspring.registroCartao.parcelas.Parcelas;
import io.zup.orange.propostaspring.registroCartao.vencimento.Vencimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class NovoCartaoNaProposta {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueios> bloqueios;
    private List<Avisos> avisos;
    private List<Carteiras> carteiras;
    private List<Parcelas> parcelas;
    private BigDecimal limite;
    private BigDecimal renegociacao;
    private Vencimento vencimento;

    public Vencimento getVencimento() {
        return vencimento;
    }

    public List<Avisos> getAvisos() {
        return avisos;
    }

    public List<Carteiras> getCarteiras() {
        return carteiras;
    }

    public List<Parcelas> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getRenegociacao() {
        return renegociacao;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueios> getBloqueios() {
        return bloqueios;
    }

    @Override
    public String toString() {
        return "NovaPropostaComCartao{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                '}';
    }
}
