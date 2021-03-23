package io.zup.orange.propostaspring.compartilhado.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.codec.binary.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Base64.isBase64(value);
    }
}
