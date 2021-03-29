package io.zup.orange.propostaspring.registroCartao.avisos;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import java.time.LocalDate;

public class AvisoResponseGateway {

    private String validoAte;

    private String destino;

    private Cartao cartao;

    public Aviso toModel(){
        return new Aviso(destino, LocalDate.parse(validoAte), cartao);
    }

    public String getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Cartao getCartao() {
        return cartao;
    }

}
