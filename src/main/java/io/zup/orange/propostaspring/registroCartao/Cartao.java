package io.zup.orange.propostaspring.registroCartao;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.zup.orange.propostaspring.compartilhado.annotations.ValorUnico;
import io.zup.orange.propostaspring.registroCartao.avisos.Aviso;
import io.zup.orange.propostaspring.registroCartao.bloqueios.Bloqueio;
import io.zup.orange.propostaspring.registroCartao.carteiras.Carteira;
import io.zup.orange.propostaspring.registroCartao.parcelas.Parcela;
import io.zup.orange.propostaspring.registroCartao.vencimento.Vencimento;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCartao", nullable = false)
    private Long idCartao;

    @Column(name = "numeroDoCartao",unique = true,nullable = false)
    private String numeroDoCartao;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
    private LocalDateTime emitidoEm = LocalDateTime.now();

    @NotBlank
    @Column(name = "nome",updatable = true, nullable = false)
    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Aviso> avisos;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Carteira> carteiras;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Parcela> parcelas;

    private BigDecimal limite;
    private BigDecimal renegociacao;

    @OneToOne(cascade = CascadeType.MERGE)
    private Vencimento vencimento;

    @NotBlank
    @Column(name = "documento", updatable = false, nullable = false)
    private String documento;

    @NotBlank
    @Column(name = "idProposta", updatable = false, nullable = false)
    private String idProposta;


    @Deprecated
    public Cartao() {
    }

    public Cartao(
            @NotBlank String documento,
            @NotBlank String titular,
            @NotNull String idProposta) {
        this.documento = documento;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "idCartao=" + idCartao +
                ", numeroDoCartao='" + numeroDoCartao + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                ", documento='" + documento + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return numeroDoCartao.equals(cartao.numeroDoCartao) && titular.equals(cartao.titular) && documento.equals(cartao.documento) && idProposta.equals(cartao.idProposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDoCartao, titular, documento, idProposta);
    }
}
