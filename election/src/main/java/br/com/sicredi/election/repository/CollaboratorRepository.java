package br.com.sicredi.election.repository;

import br.com.sicredi.election.core.entities.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
    Optional<Collaborator> findByCpf(Long cpf);
    List<Collaborator> findByIdSession(Long idSession);
}
