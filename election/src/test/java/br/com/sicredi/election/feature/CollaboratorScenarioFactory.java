package br.com.sicredi.election.feature;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorUpdateRequest;
import br.com.sicredi.election.core.entities.Collaborator;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorScenarioFactory {
    public static final List<Collaborator> LIST_COLLABORATOR = listCollaborator();
    public static final List<CollaboratorResponse> LIST_COLLABORATOR_RESPONSE = listCollaboratorResponse();
    public static final Collaborator COLLABORATOR = createCollaborator();
    public static final CollaboratorResponse COLLABORATOR_RESPONSE = createCollaboratorResponse();
    public static final CollaboratorRequest COLLABORATOR_REQUEST = createCollaboratorRequest();
    public static final CollaboratorUpdateRequest COLLABORATOR_UPDATE_REQUEST = collaboratorUpdateRequest();

    private static List<Collaborator> listCollaborator() {
        Collaborator collaborator = Collaborator.builder().collaboratorId(1L).name("Fulano").session(SessionScenarioFactory.SESSION_PAYLOAD).cpf("94123018041").build();
        List<Collaborator> list = new ArrayList<>();
        list.add(collaborator);
        return list;
    }

    private static List<CollaboratorResponse> listCollaboratorResponse() {
        CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
        collaboratorResponse.setCollaboratorId(1L);
        collaboratorResponse.setName("Fulano");
        collaboratorResponse.setSessionId(1L);
        collaboratorResponse.setCpf("94123018041");
        List<CollaboratorResponse> list = new ArrayList<>();
        list.add(collaboratorResponse);
        return list;
    }

    private static Collaborator createCollaborator() {
        return Collaborator.builder().collaboratorId(1L).name("Fulano").session(SessionScenarioFactory.SESSION_PAYLOAD).cpf("94123018041").build();
    }

    private static CollaboratorResponse createCollaboratorResponse() {
        CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
        collaboratorResponse.setCollaboratorId(1L);
        collaboratorResponse.setName("Fulano");
        collaboratorResponse.setSessionId(1L);
        collaboratorResponse.setCpf("94123018041");
        return collaboratorResponse;
    }

    private static CollaboratorRequest createCollaboratorRequest() {
        CollaboratorRequest collaboratorRequest = new CollaboratorRequest();
        collaboratorRequest.setName("Fulano");
        collaboratorRequest.setSessionId(1L);
        collaboratorRequest.setCpf("94123018041");
        return collaboratorRequest;
    }

    private static CollaboratorUpdateRequest collaboratorUpdateRequest() {
        CollaboratorUpdateRequest collaboratorUpdateRequest = new CollaboratorUpdateRequest();
        collaboratorUpdateRequest.setSessionId(1L);
        return collaboratorUpdateRequest;
    }
}
