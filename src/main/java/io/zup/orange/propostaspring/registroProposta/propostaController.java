package io.zup.orange.propostaspring.registroProposta;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class propostaController {

    public String criaProposta(@RequestBody @Valid NovaPropostaRequest request){
        return null;
    }

}
