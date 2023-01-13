package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.entities.Collaborator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollaboratorMapper {
    List<CollaboratorResponse> listEntityToListResponse(List<Collaborator> collaborator);
    Collaborator requestToEntity(CollaboratorRequest request);
    CollaboratorResponse entityToResposnse(Collaborator collaborator);
}
