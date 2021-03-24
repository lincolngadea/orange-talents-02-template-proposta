package io.zup.orange.propostaspring.config;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroCartao.Cartao;
import io.zup.orange.propostaspring.registroCartao.CartaoGateway;
import io.zup.orange.propostaspring.registroCartao.NovoCartaoRequest;
import io.zup.orange.propostaspring.registroCartao.NovoCartaoResponse;
import io.zup.orange.propostaspring.registroProposta.Proposta;
import io.zup.orange.propostaspring.registroProposta.PropostaRepository;
import io.zup.orange.propostaspring.registroProposta.PropostaStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component
public class GeradorDeCartao {

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    private final List<Proposta> propostasPendenteCartao = new ArrayList<>();
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartaoGateway clienteCartao;

    @Scheduled(fixedDelay = 10000)//atualiza a cada 30 minutos
    @Transactional
    public void solicitaCartao() {
        propostasPendenteCartao.addAll(propostaRepository.findByPropostaStatus(PropostaStatus.ELEGIVEL));
        propostasPendenteCartao.forEach(proposta -> {
            logger.info("Proposta - {}", proposta.toString());
            NovoCartaoRequest cartaoRequest = new NovoCartaoRequest(
                    proposta.getDocumento(), proposta.getNome(), proposta.getId().toString());
            try {
                NovoCartaoResponse resposta = clienteCartao.salvarCartao(cartaoRequest);
                Cartao cartao = resposta.toModel(proposta);
                logger.info("Cartao - {}", cartao.toString());
                proposta.adicionaCartao(cartao);
                propostaRepository.save(proposta);
                logger.info("Cartao adicionado a proposta");
            } catch (Exception e) {
                logger.error("Proposta - {}", proposta.toString());
                logger.error(e.getMessage());
                logger.error(e.getCause().toString());
            }
        });
    }
}
