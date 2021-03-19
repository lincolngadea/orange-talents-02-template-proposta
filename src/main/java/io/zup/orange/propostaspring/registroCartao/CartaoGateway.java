package io.zup.orange.propostaspring.registroCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartoes", url = "${uri_cartao}")
public interface CartaoGateway {
//
//    @PostMapping("/api/cartoes")
//    public NovoCartaoResponseGateway salvarCartao(NovoCartaoRequestGateway requestGateway);

    @GetMapping("/api/cartoes?idProposta={id}")
    public NovoCartaoNaProposta cartaoById(@PathVariable("id") String id);
}
