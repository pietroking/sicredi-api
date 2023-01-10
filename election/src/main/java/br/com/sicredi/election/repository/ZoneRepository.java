package br.com.sicredi.election.repository;

import br.com.sicredi.election.core.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long> {

    Optional<Zone> findByNumber(Long number);
}
