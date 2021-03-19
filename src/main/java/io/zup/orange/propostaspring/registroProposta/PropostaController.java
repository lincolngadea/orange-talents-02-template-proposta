package io.zup.orange.propostaspring.registroProposta;

import feign.FeignException;
import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroProposta.analiseFinanceira.AnaliseFinanceiraDeClientes;
import io.zup.orange.propostaspring.registroProposta.analiseFinanceira.SubmetePropostaAnaliseRequest;
import io.zup.orange.propostaspring.registroProposta.analiseFinanceira.SubmetePropostaAnaliseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnaliseFinanceiraDeClientes analisaCliente;

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(
            @RequestBody @Valid NovaPropostaRequest request,
            UriComponentsBuilder builder){

        //Grava no Banco de dados
        Proposta proposta = request.toModel();
        if(propostaRepository.existsByDocumento(proposta.getDocumento())){
            return ResponseEntity.unprocessableEntity()
                    .body("O Cliente já Possui Proposta! Documento: "+proposta.getDocumento());}
        propostaRepository.save(proposta);

        //Submete para análise
        PropostaStatus status = submetePropostaParaAnalise(proposta);
        proposta.updateStatus(status);

        //loga após salvar no banco
        logger.info("Proposta documento={} salário={} criada com sucesso!",
                proposta.getDocumento(),proposta.getSalario());

        URI location = builder.path("/api/proposta/{id}").build(proposta.getId());
        return ResponseEntity.created(location).body(new NovaPropostaResponse(proposta));
    }

    private PropostaStatus submetePropostaParaAnalise(Proposta proposta) {

        try {
            SubmetePropostaAnaliseRequest requisicao = new SubmetePropostaAnaliseRequest(proposta);
            SubmetePropostaAnaliseResponse resposta = analisaCliente.submeterParaAnalise(requisicao);
            return resposta.toModel();
        }catch (FeignException.UnprocessableEntity e){
            return PropostaStatus.NAO_ELEGIVEL;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Aconteceu um erro inesperado!");
        }
    }
}
