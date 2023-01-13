package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.candidate.CandidateRequest;
import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.entities.Candidate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    List<CandidateResponse> listEntityToListResponse(List<Candidate> candidate);
    Candidate requestToEntity(CandidateRequest request);
    CandidateResponse entityToResposnse(Candidate candidate);
}
