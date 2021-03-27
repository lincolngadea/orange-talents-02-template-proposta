package io.zup.orange.propostaspring.registroCartao.vencimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VencimentoRepository extends JpaRepository<Vencimento, Long> {
}
