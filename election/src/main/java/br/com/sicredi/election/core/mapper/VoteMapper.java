package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.vote.VoteRequest;
import br.com.sicredi.election.core.dto.vote.VoteResponse;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    List<VoteResponse> listEntityToListResponse(List<Vote> vote);

    Vote requestToEntity(VoteRequest request);

    @Mapping(target = "sessionId",source = "sessionId")
    VoteResponse entityToResponse(Vote vote, Long sessionId);

}
