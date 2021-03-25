package io.zup.orange.propostaspring.registroCartao;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroProposta.Proposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoGateway cartaoGateway;
    @Autowired
    private EntityManager manager;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);


    @PostMapping("/cartoes")
    @Transactional
    public ResponseEntity<NovoCartaoResponse> salvaCartao(
            @RequestBody @Valid NovoCartaoRequest request, UriComponentsBuilder builder){

        String idProposta = request.getIdProposta();

        try{
            Proposta proposta = (Proposta) manager.createQuery(
                    "SELECT p FROM Proposta p WHERE p.id = :idProposta AND p.propostaStatus = 'CONCLUIDA'")
                    .setParameter("idProposta", UUID.fromString(idProposta)).getSingleResult();

            Cartao cartao = request.toModel(proposta.getCartao());

            logger.info("Id da Proposta:{}",cartao.getId());

            cartaoRepository.save(cartao);

            URI location = builder.path("/api/cartoes/{id}").build(cartao.getId());
            return ResponseEntity.created(location).body(new NovoCartaoResponse(cartao));

        }catch (NoResultException e){
            return ResponseEntity.notFound().build();
        }







    }

    @GetMapping("/cartoes/{id}")
    public NovoCartaoResponseGateway cartaoById(@PathVariable("id") String id){
        return cartaoGateway.cartaoById(id);
    }
}
