package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.registroCartao.bloqueios.BloqueioRequestGateway;
import io.zup.orange.propostaspring.registroCartao.bloqueios.BloqueioResponseGateway;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url="${uri_cartao}api/cartoes")
public interface CartaoGateway {

    @PostMapping
    public CartaoResponseGateway salvarCartao(CartaoRequest requestGateway);

    @GetMapping("/{id}")
    public CartaoResponseGateway buscaCartaoPorId(@PathVariable String id);

    @PostMapping("/{id}/bloqueios")
    public BloqueioResponseGateway bloqueio(@PathVariable String id, BloqueioRequestGateway request);
}
