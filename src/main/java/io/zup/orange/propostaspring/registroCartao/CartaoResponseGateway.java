package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registroCartao.avisos.Aviso;
import io.zup.orange.propostaspring.registroCartao.bloqueios.Bloqueio;
import io.zup.orange.propostaspring.registroCartao.carteiras.Carteira;
import io.zup.orange.propostaspring.registroCartao.parcelas.Parcela;
import io.zup.orange.propostaspring.registroCartao.vencimento.Vencimento;
import io.zup.orange.propostaspring.registroProposta.Proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponseGateway {

    private String id; //número do cartao
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueio> bloqueios;
    private List<Aviso> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    private BigDecimal limite;
    private BigDecimal renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public Vencimento getVencimento() {
        return vencimento;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getRenegociacao() {
        return renegociacao;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "NovoCartaoResponseGateway{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(proposta.getDocumento(),titular,idProposta);
    }

    public String toProposta(){
        return id;
    }
}
