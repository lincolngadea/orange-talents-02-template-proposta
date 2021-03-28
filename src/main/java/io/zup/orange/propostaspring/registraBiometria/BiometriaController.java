package io.zup.orange.propostaspring.registraBiometria;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroCartao.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BiometriaController {

    @Autowired
    private EntityManager manager;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    /**
     * @param numeroDoCartao no padrão xxxx-xxxx-xxxx-xxxx
     */
    @PostMapping("/cartao/{numeroDoCartao}/biometria")
    @Transactional
    public ResponseEntity<?> criaBiometria(
            @PathVariable String numeroDoCartao,
            @RequestBody @Valid BiometriaRequest request,
            UriComponentsBuilder builder){

        try{
            Cartao cartao = (Cartao) manager.createQuery("SELECT c FROM Cartao c WHERE c.numeroDoCartao = :numeroDoCartao")
                    .setParameter("numeroDoCartao", numeroDoCartao)
                    .getSingleResult();
            logger.info("Dados do Cartão:{}",cartao.getNumeroDoCartao());

            Biometria biometria = request.toModel(cartao);

            logger.info("ID do Cartão:{}",biometria.getCartao());
            logger.info("Dados da Biometria:{}",biometria.getFingerPrint());

            manager.persist(biometria);

            URI location = builder.path("/api/cartao/{id}/biometria").build(biometria.getFingerPrint());

            return ResponseEntity.created(location).body(new BiometriaResponse(biometria));

        }catch (NoResultException e){
            logger.error("ERRO NA CONSULTA, MENSAGEM:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Cartão não foi encontrado!");
        }


    }
}