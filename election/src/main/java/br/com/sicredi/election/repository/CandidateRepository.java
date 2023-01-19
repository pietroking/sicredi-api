package br.com.sicredi.election.repository;

import br.com.sicredi.election.core.dto.candidate.CandidateResultResponse;
import br.com.sicredi.election.core.entities.Candidate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByNameContainingIgnoreCase(String name);
    Optional<Candidate> findByCpf(String cpf);
    Optional<Candidate> findByNumber(Long number);

    @Query("SELECT NEW br.com.sicredi.election.core.dto.candidate.CandidateResultResponse(c.name,COUNT(v.number)) FROM Vote v inner Join Candidate c " +
            "on c.number=v.number group by c.name ")
    List<CandidateResultResponse> countVotesByOrderByVotesDesc();
}
