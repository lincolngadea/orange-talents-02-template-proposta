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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(Logback.class);
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnaliseFinanceiraDeClientes analisaCliente;

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
        return ResponseEntity.created(location).body(new PropostaResponse(proposta));
    }

    @GetMapping("proposta/{id}")
    public ResponseEntity<PropostaResponse> buscaPropostaPorStatus(@PathVariable("id") UUID id){

        Optional<Proposta> byStatus = propostaRepository.findById(id);
        if(byStatus.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PropostaResponse(byStatus.get()));
    }

    @GetMapping("/propostas")
    public ResponseEntity<?> listaPropostas(){

        List<Proposta> propostas = propostaRepository.findAllProposta();
        if(propostas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(propostas);
    }

    //Método para submeter proposta e definir status
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
