package io.zup.orange.propostaspring.registraBiometria;

import io.zup.orange.propostaspring.registroCartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "biometria")
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "fingerprint", updatable = true, nullable = false, unique = true)
    private String fingerPrint;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idCartao", nullable = false)
    private Cartao cartao;

    /**
     * @Deprecated para uso do framework
     */
    @Deprecated
    public Biometria(){}

    public Biometria(@NotBlank String fingerPrint, @NotNull Cartao cartao) {
        Assert.hasLength(fingerPrint,"Biometria é necessária");
        Assert.notNull(cartao,"Cartão é necessário");

        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return fingerPrint.equals(biometria.fingerPrint) && cartao.equals(biometria.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fingerPrint, cartao);
    }
}
