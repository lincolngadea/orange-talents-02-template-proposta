package io.zup.orange.propostaspring.registraBiometria;

import java.time.LocalDateTime;

public class BiometriaResponse {

    private LocalDateTime momentoDaCriacao;
    private String fingerPrint;
    private String idCartao;

    public BiometriaResponse(Biometria biometria) {
        momentoDaCriacao = biometria.getMomentoDaCriacao();
        fingerPrint = biometria.getFingerPrint();
        idCartao = biometria.getCartao().getId();
    }

    public LocalDateTime getMomentoDaCriacao() {
        return momentoDaCriacao;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public String getIdCartao() {
        return idCartao;
    }
}
