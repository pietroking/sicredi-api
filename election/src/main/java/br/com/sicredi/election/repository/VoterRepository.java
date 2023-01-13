package br.com.sicredi.election.repository;

import br.com.sicredi.election.core.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByCpf(Long cpf);
    List<Voter> findByIdSession(Long idSession);
//    Optional<Voter> findByStatusVote()
}
