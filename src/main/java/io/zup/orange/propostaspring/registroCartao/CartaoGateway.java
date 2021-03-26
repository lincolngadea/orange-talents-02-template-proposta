package io.zup.orange.propostaspring.registroCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url="${uri_cartao}")
public interface CartaoGateway {

    @PostMapping("/api/cartoes")
    public CartaoResponseGateway salvarCartao(CartaoRequest requestGateway);

    @GetMapping("/api/cartoes?idProposta={id}")
    public CartaoResponseGateway cartaoById(@PathVariable("id") String id);
}
