package io.zup.orange.propostaspring.registroCartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartaoController {
//
//    @Autowired
//    CartaoRepository cartaoRepository;

    @Autowired
    CartaoGateway cartaoGateway;

//    @PostMapping("/cartoes")
//    public ResponseEntity<Cartao> salvaCartao(
//            @RequestBody @Valid NovoCartaoRequestGateway request, UriComponentsBuilder builder){
//
//        Cartao cartao = request.toModel();
//        cartaoRepository.save(cartao);
//
//        URI location = builder.path("/api/cartoes/{id}").build(cartao.getId());
//        return ResponseEntity.created(location).body(cartao);
//
//    }

    @GetMapping("/cartoes/{id}")
    public NovoCartaoNaProposta cartaoById(@PathVariable("id") String id){
        NovoCartaoNaProposta novoCartaoNaProposta = cartaoGateway.cartaoById(id);
        return novoCartaoNaProposta;
    }
}
