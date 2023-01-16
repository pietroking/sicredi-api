package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.entities.Collaborator;
import br.com.sicredi.election.core.entities.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollaboratorMapper {
    @Mapping(target = "sessionId",source = "collaborator.session.sessionId")
    List<CollaboratorResponse> listEntityToListResponse(List<Collaborator> collaborator);

    @Mapping(target = "session.sessionId",source = "session.sessionId")
    Collaborator requestToEntity(CollaboratorRequest request, Session session);

    @Mapping(target = "sessionId", source = "collaborator.session.sessionId")
    CollaboratorResponse entityToResposnse(Collaborator collaborator);
}
