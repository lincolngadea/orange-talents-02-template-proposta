package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registroCartao.avisos.Aviso;
import io.zup.orange.propostaspring.registroCartao.avisos.AvisoResponseGateway;
import io.zup.orange.propostaspring.registroCartao.bloqueios.BloqueioResponseGateway;
import io.zup.orange.propostaspring.registroCartao.carteiras.Carteira;
import io.zup.orange.propostaspring.registroCartao.carteiras.CarteiraResponse;
import io.zup.orange.propostaspring.registroCartao.parcelas.Parcela;
import io.zup.orange.propostaspring.registroCartao.vencimento.VencimentoResponseGateway;
import io.zup.orange.propostaspring.registroProposta.Proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CartaoResponseGateway {

    private String id; //número do cartao
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioResponseGateway> bloqueios;
    private List<AvisoResponseGateway> avisos;
    private List<CarteiraResponse> carteiras;
    private List<Parcela> parcelas;
    private BigDecimal limite;
    private BigDecimal renegociacao;
    private VencimentoResponseGateway vencimento;
    private String idProposta;

    public VencimentoResponseGateway getVencimento() {
        return vencimento;
    }

    public List<AvisoResponseGateway> getAvisos() {
        return avisos;
    }

    public List<CarteiraResponse> getCarteiras() {
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

    public List<BloqueioResponseGateway> getBloqueios() {
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
        return new Cartao(id,titular,limite,proposta.getDocumento(),idProposta, vencimento.toModel(),
                avisos.stream().map(AvisoResponseGateway::toModel).collect(Collectors.toList()),
                carteiras.stream().map(CarteiraResponse::toModel).collect(Collectors.toList()));
    }

    public String toProposta(){
        return id;
    }

    public BloqueioResponseGateway getUltimoBloqueio() {
        return bloqueios.get(bloqueios.size() - 1);

    }
    public CarteiraResponse getUlimaCarteira(){
        return carteiras.get(carteiras.size() - 1);
    }

}
