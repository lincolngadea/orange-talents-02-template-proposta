package io.zup.orange.propostaspring.registroProposta.analiseFinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name ="analiseFinanceiraDeClientes", url="${uri_analise_financeira}")
public interface AnaliseFinanceiraDeClientes {

    @PostMapping("/api/solicitacao")
    public SubmetePropostaAnaliseResponse submeterParaAnalise(
            @RequestBody @Valid SubmetePropostaAnaliseRequest request);
}
