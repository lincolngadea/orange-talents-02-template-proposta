package io.zup.orange.propostaspring.registroCartao.vencimento;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vencimento")
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idExterno;

    private String dia;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String idExterno, String dia, LocalDateTime dataDeCriacao) {
        this.idExterno = idExterno;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public String getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public String toString() {
        return "Vencimento{" +
                "id=" + id +
                ", idExterno='" + idExterno + '\'' +
                ", dia='" + dia + '\'' +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
