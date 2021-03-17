package io.zup.orange.propostaspring.registroProposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    boolean existsByDocumento(String documento);

    /**
     * @param documento verifica documento no banco... usado no teste de integração
     * @return retorna uma Proposta
     */
    Object findByDocumento(String documento);
}
