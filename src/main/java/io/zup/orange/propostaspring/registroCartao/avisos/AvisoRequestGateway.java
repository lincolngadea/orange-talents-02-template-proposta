package io.zup.orange.propostaspring.registroCartao.avisos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.zup.orange.propostaspring.registroCartao.Cartao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequestGateway {

    @NotBlank
    private String destino;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    @Deprecated
    public AvisoRequestGateway() {
    }

    public AvisoRequestGateway(
            @NotBlank String destino,
            @NotNull LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    @Override
    public String toString() {
        return "AvisoRequestGateway{" +
                "destino='" + destino + '\'' +
                ", validoAte=" + validoAte +
                '}';
    }

    public Aviso toModel(Cartao cartao, String requestInfo, String agent) {
        return new Aviso(this,cartao,requestInfo,agent);
    }
}
