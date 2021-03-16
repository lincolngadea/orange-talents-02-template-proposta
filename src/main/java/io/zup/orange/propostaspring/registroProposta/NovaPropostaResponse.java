package io.zup.orange.propostaspring.registroProposta;

import java.math.BigDecimal;

public class NovaPropostaResponse {

    private String nome;
    private String documento;
    private String email;
    private BigDecimal salario;
    private String endereco;

    public NovaPropostaResponse(Proposta proposta){
        nome = proposta.getNome();
        documento = proposta.getDocumento();
        email = proposta.getEmail();
        salario = proposta.getSalario();
        endereco = proposta.getEndereco();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getEndereco() {
        return endereco;
    }
}
