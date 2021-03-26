package io.zup.orange.propostaspring.registroProposta.analiseDeProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name ="analiseFinanceiraDeClientes", url="${uri_analise_financeira}")
public interface AnaliseDeProposta {

    @PostMapping("/api/solicitacao")
    public AnaliseDePropostaResponse submeterParaAnalise(
            @RequestBody @Valid AnaliseDePropostaRequest request);
}
