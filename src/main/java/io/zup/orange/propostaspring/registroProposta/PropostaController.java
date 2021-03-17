package io.zup.orange.propostaspring.registroProposta;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(
            @RequestBody @Valid NovaPropostaRequest request,
            UriComponentsBuilder builder){

        Proposta proposta = request.toModel();

        if(propostaRepository.existsByDocumento(proposta.getDocumento())){
            return ResponseEntity.unprocessableEntity()
                    .body("O Cliente já Possui Proposta! Documento"+proposta.getDocumento());
        }

        propostaRepository.save(proposta);

        logger.info(
                "Proposta documento={} salário={} criada com sucesso!",
                proposta.getDocumento(),proposta.getSalario()
        );

        URI location = builder.path("/api/proposta/{id}").build(proposta.getId());
        return ResponseEntity.created(location).body(new NovaPropostaResponse(proposta));

    }

}
