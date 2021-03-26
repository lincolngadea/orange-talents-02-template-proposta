package io.zup.orange.propostaspring.registroCartao.bloqueios;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cartao cartao;

    public Bloqueio() {
    }

    public Bloqueio(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
