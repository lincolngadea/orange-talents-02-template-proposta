package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registroCartao.avisos.AvisoRequestGateway;
import io.zup.orange.propostaspring.registroCartao.avisos.AvisoResponseGateway;
import io.zup.orange.propostaspring.registroCartao.bloqueios.BloqueioRequestGateway;
import io.zup.orange.propostaspring.registroCartao.bloqueios.BloqueioResponseGateway;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url="${uri_cartao}api/cartoes")
public interface CartaoGateway {

    @PostMapping
    CartaoResponseGateway salvarCartao(CartaoRequest requestGateway);

    @GetMapping("/{numeroDoCartao}")
    CartaoResponseGateway buscaCartaoPorId(@PathVariable String numeroDoCartao);

    @PostMapping("/{numeroDoCartao}/bloqueios")
    BloqueioResponseGateway bloqueio(@PathVariable String numeroDoCartao, BloqueioRequestGateway request);

    @PostMapping("/{numeroDoCartao}/avisos")
    AvisoResponseGateway avisoDeBloqueio(@PathVariable String numeroDoCartao, AvisoRequestGateway request);

}
