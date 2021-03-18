package io.zup.orange.propostaspring.registroProposta;

import io.zup.orange.propostaspring.compartilhado.annotations.CPFouCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "proposta")
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "nome",nullable = false)
    private String nome;

    @NotBlank @CPFouCNPJ
    @Column(name = "documento",nullable = false, unique = true)
    private String documento;

    @Email(message = "Informe um email Válido") @NotBlank
    @Column(name = "email",nullable = false)
    private String email;

    @NotNull  @Positive
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @NotBlank
    @Column(name = "endereco",nullable = false)
    private String endereco;

    @Column(name = "instantCreated", nullable = false)
    private LocalDateTime localDateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PropostaStatus propostaStatus = PropostaStatus.NAO_ELEGIVEL;

    @Deprecated
    public Proposta(){}

    public Proposta(
            @NotBlank String nome,
            @NotBlank String documento,
            @Email(message = "Informe um email Válido") @NotBlank String email,
            @NotNull @Positive BigDecimal salario,
            @NotBlank String endereco) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public UUID getId() {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public PropostaStatus getPropostaStatus() {
        return propostaStatus;
    }
}
