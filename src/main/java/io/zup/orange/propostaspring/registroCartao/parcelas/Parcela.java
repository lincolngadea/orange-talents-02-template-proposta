package io.zup.orange.propostaspring.registroCartao.parcelas;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;

@Entity
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cartao cartao;

    public Parcela() {
    }

    public Parcela(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
