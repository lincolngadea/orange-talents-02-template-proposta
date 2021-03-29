package io.zup.orange.propostaspring.registroCartao.avisos;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;
    private LocalDate validoAte;
    private String ipCliente;
    private String userAgent;
    private LocalDateTime momentoDoBloqueio = LocalDateTime.now();


    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(String destino, LocalDate validoAte, Cartao cartao) {
        this.destino = destino;
        this.validoAte = validoAte;
        this.cartao = cartao;
    }

    public Aviso(
            AvisoRequestGateway avisoRequestGateway,
            Cartao cartao,
            String requestInfo,
            String agent) {

        this.cartao = cartao;
        this.userAgent = agent;
        this.ipCliente = requestInfo;
        this.destino = avisoRequestGateway.getDestino();
        this.validoAte = avisoRequestGateway.getValidoAte();

    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getMomentoDoBloqueio() {
        return momentoDoBloqueio;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public String toString() {
        return "Aviso{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", validoAte=" + validoAte +
                ", ipCliente='" + ipCliente + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", momentoDoBloqueio=" + momentoDoBloqueio +
                ", cartao=" + cartao +
                '}';
    }
}
