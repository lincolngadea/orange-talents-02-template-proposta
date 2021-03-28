package io.zup.orange.propostaspring.registroCartao.bloqueios;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bloqueios")
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cartao cartao;

    private String idExterno;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private boolean ativo;

    private String ipCliente;

    private String userAgent;

    public Long getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Bloqueio(String idExterno, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo, Cartao cartao) {
        this.idExterno = idExterno;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.cartao = cartao;
    }

    @Deprecated
    public Bloqueio() {
    }

    public void setInformacoesDeRequest(String remoteAddr, String agent, Cartao cartao) {

        this.ipCliente = remoteAddr;
        this.userAgent = agent;
        this.cartao = cartao;

    }

    @Override
    public String toString() {
        return "Bloqueio{" +
                "id=" + id +
                ", cartao=" + cartao +
                ", idExterno='" + idExterno + '\'' +
                ", bloqueadoEm=" + bloqueadoEm +
                ", sistemaResponsavel='" + sistemaResponsavel + '\'' +
                ", ativo=" + ativo +
                ", ipCliente='" + ipCliente + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
