package io.zup.orange.propostaspring.registroCartao.avisos;


import feign.FeignException;
import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroCartao.Cartao;
import io.zup.orange.propostaspring.registroCartao.CartaoGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AvisoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoGateway cartaoGateway;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    @PostMapping("/avisos/{numeroDoCartao}")
    @Transactional
    public ResponseEntity<?> avisoDeBloqueio(
            @PathVariable String numeroDoCartao,
            @RequestBody @Valid AvisoRequestGateway request,
            HttpServletRequest requestInfo,
            @RequestHeader("user-agent") String agent){

        try{
            Cartao cartao = (Cartao) manager.createQuery("SELECT c FROM Cartao c WHERE c.numeroDoCartao = :numeroDoCartao")
                    .setParameter("numeroDoCartao",numeroDoCartao)
                    .getSingleResult();

            logger.info("Resultado da Consulta==>{}",cartao.toString());

            cartaoGateway.avisoDeBloqueio(cartao.getNumeroDoCartao(), request);

            Aviso aviso = request.toModel(cartao, requestInfo.getRemoteAddr(), agent);

            manager.merge(aviso);
            return ResponseEntity.ok().build();

        }catch (NoResultException n){
            logger.error("(Query)Informação do Erro ==> {}", n.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problemas na consulta do Cartão");
        }catch (FeignException f){
            logger.error("(Feign)Informação do Erro ==> {}", f.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
