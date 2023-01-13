package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.voter.VoterRequest;
import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.entities.Voter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoterMapper {
    List<VoterResponse> listEntityToListResponse(List<Voter> voter);
    Voter requestToEntity(VoterRequest request);
    VoterResponse entityToResposnse(Voter voter);
}
