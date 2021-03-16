package io.zup.orange.propostaspring.registroProposta;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class PropostaController {

    @PersistenceContext
    private EntityManager manager;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder){

        Proposta proposta = request.toModel();
        manager.persist(proposta);

        logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getDocumento(),proposta.getSalario());

        URI location = builder.path("/proposta/{id}").build(proposta.getId());
        return ResponseEntity.created(location).body(new NovaPropostaResponse(proposta));

    }

}
