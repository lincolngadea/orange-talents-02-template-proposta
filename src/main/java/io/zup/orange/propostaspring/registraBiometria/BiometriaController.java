package io.zup.orange.propostaspring.registraBiometria;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroCartao.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
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

    @PostMapping("/cartao/{idCartao}/biometria")
    @Transactional
    public ResponseEntity<BiometriaResponse> criaBiometria(
            @PathVariable("idCartao") String idCartao,
            @RequestBody @Valid NovaBiometriaRequest request,
            UriComponentsBuilder builder){

        Optional<Cartao> cartao = Optional.ofNullable(manager.find(Cartao.class, idCartao));

        logger.info("Dados do Cartão:{}",cartao.get());

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = request.toModel(cartao.get());

        logger.info("ID do Cartão:{}",biometria.getCartao());
        logger.info("Dados da Biometria:{}",biometria.getFingerPrint());

        manager.persist(biometria);

        URI location = builder.path("/api/cartao/{id}/biometria").build(biometria.getFingerPrint());

        return ResponseEntity.created(location).body(new BiometriaResponse(biometria));
    }
}