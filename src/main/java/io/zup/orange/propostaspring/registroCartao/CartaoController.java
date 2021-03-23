package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class CartaoController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    CartaoGateway cartaoGateway;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);


    @PostMapping("/cartoes")
    @Transactional
    public ResponseEntity<NovoCartaoResponseGateway> salvaCartao(
            @RequestBody @Valid NovoCartaoRequestGateway request, UriComponentsBuilder builder){

        Cartao cartao = request.toModel(cartaoGateway);
        logger.info("Dados do cartão:{}",cartao);

        cartaoRepository.save(cartao);

        NovoCartaoResponseGateway responseGateway = cartaoGateway.cartaoById(cartao.getIdProposta());

        URI location = builder.path("/api/cartoes/{id}").build(responseGateway.getId());
        return ResponseEntity.created(location).body(responseGateway);

    }

    @GetMapping("/cartoes/{id}")
    public NovoCartaoResponseGateway cartaoById(@PathVariable("id") String id){
        return cartaoGateway.cartaoById(id);
    }
}
