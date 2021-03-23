package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registraBiometria.Biometria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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
}
