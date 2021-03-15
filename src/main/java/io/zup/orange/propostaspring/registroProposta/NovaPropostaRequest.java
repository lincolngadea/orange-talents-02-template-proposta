package io.zup.orange.propostaspring.registroProposta;

import io.zup.orange.propostaspring.compartilhado.CPFouCNPJ;
import io.zup.orange.propostaspring.compartilhado.ValorUnico;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @CPFouCNPJ
    @ValorUnico(nomeDoCampo = "documento",classeDoDominio = Proposta.class)
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;


}
