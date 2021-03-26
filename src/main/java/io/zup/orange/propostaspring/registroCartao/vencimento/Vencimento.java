package io.zup.orange.propostaspring.registroCartao.vencimento;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vencimento")
public class Vencimento {

    @Id
    @Type(type ="uuid-char")
    private UUID id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(UUID id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public UUID getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
