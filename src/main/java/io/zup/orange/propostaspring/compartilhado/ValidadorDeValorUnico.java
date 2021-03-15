package io.zup.orange.propostaspring.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidadorDeValorUnico implements ConstraintValidator<ValorUnico, Object> {

    private String atributoDoDominio;
    private Class<?> classe;

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(ValorUnico parametro) {
        atributoDoDominio = parametro.nomeDoCampo();
        classe = parametro.classeDoDominio();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext contexto) {
        Query query = manager.createQuery(
                "select 1 from "
                    + classe.getName() +
                    " where "
                    + atributoDoDominio +
                    "=:valor"
        );

        query.setParameter("valor",valor);
        List<?> list = query.getResultList();
        Assert.state(
                list.size() <= 1,
                "Foi encontrado mais de "
                        +classe+" com o atributo "
                        +atributoDoDominio+"= "
                        +valor
        );

        return list.isEmpty();
    }
}
