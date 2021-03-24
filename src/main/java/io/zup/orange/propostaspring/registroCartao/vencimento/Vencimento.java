package io.zup.orange.propostaspring.registroCartao.vencimento;

import io.zup.orange.propostaspring.registroCartao.Cartao;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Vencimento {
    @Id
    @Type(type="uuid-char")
    private UUID id;
    @OneToOne
    private Cartao cartao;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    public Vencimento() {
    }

    public Vencimento(Cartao cartao, Integer dia, LocalDateTime dataDeCriacao) {
        this.cartao = cartao;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public UUID getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
