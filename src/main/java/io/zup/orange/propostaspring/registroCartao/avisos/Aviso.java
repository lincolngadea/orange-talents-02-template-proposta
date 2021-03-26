package io.zup.orange.propostaspring.registroCartao.avisos;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    public Aviso() {
    }

    public Aviso(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
