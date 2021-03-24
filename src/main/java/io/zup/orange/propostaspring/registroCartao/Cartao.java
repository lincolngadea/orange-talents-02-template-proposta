package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registraBiometria.Biometria;
import io.zup.orange.propostaspring.registroCartao.avisos.Avisos;
import io.zup.orange.propostaspring.registroCartao.bloqueio.BloqueioStatus;
import io.zup.orange.propostaspring.registroCartao.bloqueio.Bloqueios;
import io.zup.orange.propostaspring.registroCartao.carteiras.Carteiras;
import io.zup.orange.propostaspring.registroCartao.parcelas.Parcelas;
import io.zup.orange.propostaspring.registroCartao.vencimento.Vencimento;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {

    @NotNull
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @NotBlank
    @Column(name = "documento", updatable = false, nullable = false)
    private String documento;

    @NotBlank
    @Column(name = "nome",updatable = true, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "idProposta", updatable = false, nullable = false)
    private String idProposta;

    @OneToMany(mappedBy = "cartao")
    private Set<Biometria> biometria = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private BloqueioStatus bloqueioStatus = BloqueioStatus.BLOQUEADO;

    @OneToMany
    private List<Bloqueios> bloqueios;
    @OneToMany
    private List<Avisos> avisos;
    @OneToMany
    private List<Carteiras> carteiras;
    @OneToMany
    private List<Parcelas> parcelas;
    @OneToOne
    private Vencimento vencimento;

    private BigDecimal limite;
    private BigDecimal renegociacao;

    private LocalDateTime emitidoEm = LocalDateTime.now();

    @Deprecated
    public Cartao() {
    }

    public Cartao(
            @NotBlank String id,
            @NotBlank String documento,
            @NotBlank String nome,
            @NotNull String idProposta) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public Set<Biometria> getBiometria() {
        return biometria;
    }

    public BloqueioStatus getBloqueioStatus() {
        return bloqueioStatus;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public List<Bloqueios> getBloqueios() {
        return bloqueios;
    }

    public String getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public List<Avisos> getAvisos() {
        return avisos;
    }

    public List<Carteiras> getCarteiras() {
        return carteiras;
    }

    public List<Parcelas> getParcelas() {
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
}
