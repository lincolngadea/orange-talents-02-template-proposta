package io.zup.orange.propostaspring.registroCartao.carteiras;

import feign.FeignException;
import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroCartao.Cartao;
import io.zup.orange.propostaspring.registroCartao.CartaoGateway;
import io.zup.orange.propostaspring.registroCartao.CartaoResponseGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class CarteiraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoGateway cartaoGateway;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    @PostMapping("/carteira/{numeroDoCartao}")
    @Transactional
    public ResponseEntity<?> associaCarteira(
            @PathVariable String numeroDoCartao,
            @RequestBody @Valid CarteiraRequestGateway request,
            UriComponentsBuilder builder){

        try{
            Cartao cartao = (Cartao) manager.createQuery("SELECT c FROM Cartao c WHERE c.numeroDoCartao = :numeroDoCartao")
                    .setParameter("numeroDoCartao",numeroDoCartao)
                    .getSingleResult();

//            logger.info("Cartão ==> {}",cartao.getNumeroDoCartao());

            cartaoGateway.associaCarteira(cartao.getNumeroDoCartao(), request);

            CartaoResponseGateway cartaoResponse = cartaoGateway.buscaCartaoPorId(numeroDoCartao);

            Carteira carteira = cartaoResponse.getUlimaCarteira().toModel(cartao);

            cartao.addCarteira(carteira);

            manager.merge(cartao);

            URI uri = builder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();

            return ResponseEntity.created(uri).build();

        }catch (NoResultException n){
            logger.error("(Query)Informações do Erro ==> {}",n.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problemas na consulta do Cartão");
        }catch (FeignException f){
            logger.error("(Feign)Informações do Erro ==> {}",f.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }

    }
}
