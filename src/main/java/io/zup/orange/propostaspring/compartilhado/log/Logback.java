package io.zup.orange.propostaspring.compartilhado.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logback {

    private final Logger logger = LoggerFactory.getLogger(Logback.class);

    public void log(){
        logger.info("Log de informação");
        logger.warn("Log de aviso, algo pode estar errado ou faltando, Cuidado!");
        logger.error("Log de erro, algo de errado não está certo!");
        logger.debug("Log de depuração, contém informações mais refinadas, que são mais úteis para depurar um app.");
        logger.trace("Log de rastreabilidade, contém informações mais refinadas do que o Debug");
    }
}
