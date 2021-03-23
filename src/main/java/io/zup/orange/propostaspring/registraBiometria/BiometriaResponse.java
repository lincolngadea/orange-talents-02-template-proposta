package io.zup.orange.propostaspring.registraBiometria;

public class BiometriaResponse {

    private String fingerPrint;
    private String idCartao;

    public BiometriaResponse(Biometria biometria) {
        fingerPrint = biometria.getFingerPrint();
        idCartao = biometria.getCartao().getId();
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public String getIdCartao() {
        return idCartao;
    }
}
