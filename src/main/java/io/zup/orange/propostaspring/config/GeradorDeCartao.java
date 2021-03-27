package io.zup.orange.propostaspring.config;

import io.zup.orange.propostaspring.compartilhado.log.Logback;
import io.zup.orange.propostaspring.registroCartao.*;
import io.zup.orange.propostaspring.registroCartao.vencimento.Vencimento;
import io.zup.orange.propostaspring.registroCartao.vencimento.VencimentoRepository;
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
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoGateway clienteCartao;
    @Autowired
    private VencimentoRepository vencimentoRepository;

    @Scheduled(fixedDelay = 10000)//atualiza a cada 30 minutos
    @Transactional
    public void solicitaCartao() {

        List<Proposta> byPropostaStatus = propostaRepository.findByPropostaStatus(PropostaStatus.ELEGIVEL);

        //Acessa o bloco só se a lista de Propostas não estiver vazia
        if(!byPropostaStatus.isEmpty()) {
            propostasPendenteCartao.clear();
            propostasPendenteCartao.addAll(byPropostaStatus);
            propostasPendenteCartao.forEach(proposta -> {

//              logger.info("Proposta - {}", proposta.toString());
                CartaoRequest cartaoRequest = new CartaoRequest(
                        proposta.getDocumento(), proposta.getNome(), proposta.getId().toString());

//                logger.info("Cartão Request:{}",cartaoRequest.toString());
                try {
                    CartaoResponseGateway cartaoResponseGateway = clienteCartao.salvarCartao(cartaoRequest);
                    logger.info("Cartao Response Gateway:{}",cartaoResponseGateway.toString());

                    Cartao cartao = cartaoResponseGateway.toModel(proposta);
                    String numeroDoCartao = cartaoResponseGateway.toProposta();

                    logger.info("Dados do Cartao ==> {}", cartao.toString());
                    proposta.adicionaCartao(numeroDoCartao);

                    propostaRepository.save(proposta);
                    logger.info("Iniciou gravação da proposta");

                    cartaoRepository.save(cartao);

                    logger.info("Iniciou gravação do cartão");

                } catch (Exception e) {
                    logger.error("Proposta - {}", proposta.toString());
                    logger.error(e.getMessage());
                    logger.error(e.getCause().toString());
                }
            });
        }
    }
}
