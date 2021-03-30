package io.zup.orange.propostaspring.registroCartao.carteiras;

import io.zup.orange.propostaspring.compartilhado.annotations.ValorUnico;
import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.validation.constraints.Email;

public class CarteiraRequestGateway {

    @Email
    private String email;

    private TipoDaCarteira carteira;

    @Deprecated
    public CarteiraRequestGateway() {
    }

    public CarteiraRequestGateway(
            @Email String email,
            TipoDaCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoDaCarteira getCarteira() {
        return carteira;
    }

}
