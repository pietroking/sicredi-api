package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.voter.VoterRequest;
import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Voter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoterMapper {
    @Mapping(target = "sessionId",source = "voter.session.sessionId")
    List<VoterResponse> listEntityToListResponse(List<Voter> voter);

    @Mapping(target = "session.sessionId",source = "session.sessionId")
    Voter requestToEntity(VoterRequest request, Session session);

    @Mapping(target = "sessionId",source = "voter.session.sessionId")
    VoterResponse entityToResposnse(Voter voter);
}
