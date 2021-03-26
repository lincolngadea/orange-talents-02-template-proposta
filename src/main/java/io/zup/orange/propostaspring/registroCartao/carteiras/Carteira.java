package io.zup.orange.propostaspring.registroCartao.carteiras;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cartao cartao;

    public Carteira() {
    }

    public Carteira(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
