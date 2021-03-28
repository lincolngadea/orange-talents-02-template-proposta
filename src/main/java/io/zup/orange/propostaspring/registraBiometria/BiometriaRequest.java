package io.zup.orange.propostaspring.registraBiometria;

import io.zup.orange.propostaspring.compartilhado.annotations.IsBase64;
import io.zup.orange.propostaspring.compartilhado.annotations.ValorUnico;
import io.zup.orange.propostaspring.registroCartao.Cartao;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class BiometriaRequest {

    @NotBlank
    @IsBase64
    @ValorUnico(nomeDoCampo = "fingerPrint",classeDoDominio = Biometria.class, message = "A Biometria não pode ser duplicada!")
    private String fingerPrint;

    /**
     * @Deprecated para uso do Spring
     */
    @Deprecated
    public BiometriaRequest(){ }

    public BiometriaRequest(@NotBlank String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Biometria toModel(Cartao cartao) {
        Assert.hasLength(fingerPrint,"Deve ser informada a biometria!");
        return new Biometria(fingerPrint,cartao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiometriaRequest that = (BiometriaRequest) o;
        return fingerPrint.equals(that.fingerPrint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fingerPrint);
    }
}
