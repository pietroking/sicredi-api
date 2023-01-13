package br.com.sicredi.election.repository;

import br.com.sicredi.election.core.entities.Candidate;
import br.com.sicredi.election.core.entities.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByName(String name);
    Optional<Collaborator> findByCpf(Long cpf);
    List<Candidate> findByParty(String party);
}
