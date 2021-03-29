package io.zup.orange.propostaspring.registroCartao.carteiras;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String idExterno;

    private LocalDateTime associadaEm;

    @ManyToOne
//    @JoinColumn(name = "cartao",unique = true,nullable = false)
    private Cartao cartao;

    @Email
//    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoDaCarteira emissor;

    @Deprecated
    public Carteira() {
    }

    public Carteira(
            @NotBlank String idExterno,
            LocalDateTime associadaEm,
            Cartao cartao,
            @Email String email,
            TipoDaCarteira emissor) {
        this.idExterno = idExterno;
        this.associadaEm = associadaEm;
        this.cartao = cartao;
        this.email = email;
        this.emissor = emissor;
    }

    public Long getId() {
        return id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getEmail() {
        return email;
    }

    public TipoDaCarteira getEmissor() {
        return emissor;
    }
}
