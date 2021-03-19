//package io.zup.orange.propostaspring.registroCartao;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class NovoCartaoRequestGateway {
//
//    private String documento;
//    private String nome;
//    private String idProposta;
//
//    public NovoCartaoRequestGateway(String documento, String nome, String idProposta) {
//        this.documento = documento;
//        this.nome = nome;
//        this.idProposta = idProposta;
//    }
//
//    public String getDocumento() {
//        return documento;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getIdProposta() {
//        return idProposta;
//    }
//
//    public Cartao toModel() {
//        return new Cartao(documento,nome,idProposta);
//    }
//}
