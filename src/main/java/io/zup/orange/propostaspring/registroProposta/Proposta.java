package io.zup.orange.propostaspring.registroProposta;

import io.zup.orange.propostaspring.compartilhado.CPFouCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "proposta")
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank @CPFouCNPJ
    private String documento;

    @Email(message = "Informe um email Válido") @NotBlank
    private String email;

    @NotNull  @Positive
    private BigDecimal salario;
    @NotBlank
    private String endereco;

    @Deprecated
    public Proposta(){}

    public Proposta(
            @NotBlank String nome,
            @NotBlank String documento,
            @Email @NotBlank String email,
            @NotNull @Positive BigDecimal salario,
            @NotBlank String endereco
    ) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
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
