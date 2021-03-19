//package io.zup.orange.propostaspring.registroCartao;
//
//import io.zup.orange.propostaspring.registroCartao.vencimento.Vencimento;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//public class Cartao {
//
//    @NotNull
//    @Id
//    @Column(name = "id" unique = true, nullable = false)
//    private String id;
//
//    @NotBlank
//    @Column(name = "documento", updatable = false, nullable = false)
//    private String documento;
//
//    @NotBlank
//    @Column(name = "nome",updatable = true, nullable = false)
//    private String nome;
//
//    @NotNull
//    @Column(name = "idProposta", updatable = false, nullable = false)
//    private String idProposta;
//
//    public Cartao(
//            @NotNull String id,
//            @NotBlank String documento,
//            @NotBlank String nome,
//            @NotNull String idProposta) {
//        this.id = id;
//        this.documento = documento;
//        this.nome = nome;
//        this.idProposta = idProposta;
//    }
//}
