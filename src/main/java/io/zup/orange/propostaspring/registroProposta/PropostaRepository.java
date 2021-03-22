package io.zup.orange.propostaspring.registroProposta;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    boolean existsByDocumento(String documento);

    /**
     * @param documento verifica documento no banco... usado no teste de integração
     * @return retorna uma Proposta
     */
    Object findByDocumento(String documento);

    Optional<Proposta> findById(UUID id);

    List<Proposta> findByPropostaStatus(PropostaStatus status);

    @Query("SELECT p FROM Proposta p WHERE p.propostaStatus = :status")
    Optional<Proposta> findByProposta(@Param("status") PropostaStatus status);


}
