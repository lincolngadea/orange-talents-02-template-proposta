package io.zup.orange.propostaspring.registroCartao.bloqueios;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BloqueioController {

    @PostMapping("/cartao/bloqueio/{idCartao}")
    public void realizaBloqueio(){

    }
}
