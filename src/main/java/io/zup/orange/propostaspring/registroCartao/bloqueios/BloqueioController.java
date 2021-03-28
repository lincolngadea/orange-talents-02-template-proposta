package io.zup.orange.propostaspring.registroCartao.bloqueios;

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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartaoGateway cartaoGateway;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    @PostMapping("/{numeroDoCartao}")
    @Transactional
    public ResponseEntity<?> bloqueia(@PathVariable String numeroDoCartao,
                                      HttpServletRequest requestInfos,
                                      @RequestHeader("user-agent") String agent) {

        try {

            Cartao cartao = (Cartao) manager.createQuery("SELECT c FROM Cartao c WHERE c.numeroDoCartao = :numeroDoCartao")
                    .setParameter("numeroDoCartao",numeroDoCartao)
                    .getSingleResult();

            cartaoGateway.bloqueio(cartao.getNumeroDoCartao(), new BloqueioRequestGateway("Proposta"));

            CartaoResponseGateway respostaCartao = cartaoGateway.buscaCartaoPorId(cartao.getNumeroDoCartao());
            Bloqueio bloqueio = respostaCartao.getUltimoBloqueio().toModel();
            logger.info("Informações de Bloqueio ==>{}",bloqueio.toString());
            bloqueio.setInformacoesDeRequest(requestInfos.getRemoteAddr(), agent, cartao);
            cartao.addBloqueio(bloqueio);
            manager.merge(cartao);
            return ResponseEntity.ok().build();

        } catch (FeignException f) {

            logger.error("ERRO DO FEIGN, MENSAGEM:{}",f.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        } catch (NoResultException n){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartao não encontrado...");
        }
    }
}
