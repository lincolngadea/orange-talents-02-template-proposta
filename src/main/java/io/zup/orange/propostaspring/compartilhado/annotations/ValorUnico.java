package io.zup.orange.propostaspring.compartilhado.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidadorDeValorUnico.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {

        String message() default "Documento duplicado é problema blz!";

        Class<?>[] groups() default { };

        Class<? extends Payload>[] payload() default { };

        String nomeDoCampo();

        Class<?> classeDoDominio();
}
