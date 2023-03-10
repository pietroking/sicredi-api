package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.mapper.CollaboratorMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.CollaboratorScenarioFactory;
import br.com.sicredi.election.feature.SessionScenarioFactory;
import br.com.sicredi.election.feature.VoterScenarioFactory;
import br.com.sicredi.election.repository.CollaboratorRepository;
import br.com.sicredi.election.repository.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CollaboratorServiceTest {
    @Mock
    private CollaboratorRepository collaboratorRepository;
    @Mock
    private CollaboratorMapper collaboratorMapper;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private CollaboratorService collaboratorService;

    @Test
    @DisplayName("findAll - Teste para realizar listagem de colaborador")
    public void findAll_WhenExistCollaborator_ThenReturnListOfCollaboratorCreated(){
        when(collaboratorRepository.findAll()).thenReturn(CollaboratorScenarioFactory.LIST_COLLABORATOR);
        when(collaboratorMapper.listEntityToListResponse(any())).thenReturn(CollaboratorScenarioFactory.LIST_COLLABORATOR_RESPONSE);

        ResponseEntity<List<CollaboratorResponse>> list = collaboratorService.findAll();

        assertEquals(CollaboratorScenarioFactory.LIST_COLLABORATOR_RESPONSE, list.getBody());
        verify(collaboratorRepository,times(1)).findAll();
        verify(collaboratorMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("findAll - Teste tentar realizar listagem vazia de colaborador")
    public void findAll_WhenNotExistCollaborator_ThenReturnException(){
        when(collaboratorRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> collaboratorService.findAll());
    }

    @Test
    @DisplayName("findBySession - Teste para realizar listagem dos collaboradores de uma se????o")
    public void findBySessionCollaborator_WhenExistCollaboratorRegisteredInSession_ThenReturnListOfSessionCollaborator(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(collaboratorMapper.listEntityToListResponse(any())).thenReturn(CollaboratorScenarioFactory.LIST_COLLABORATOR_RESPONSE);

        ResponseEntity<List<CollaboratorResponse>> list = collaboratorService.findBySession(SessionScenarioFactory.SESSION_PAYLOAD.getSessionId());

        assertEquals(CollaboratorScenarioFactory.LIST_COLLABORATOR_RESPONSE, list.getBody());
        verify(sessionRepository,times(1)).findById(1L);
        verify(collaboratorMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("findBySession - Teste tentar realizar listagem de colaborador de uma se????o vazia")
    public void findBySessionCollaborator_WhenNotExistCollaboratorRegisteredInSession_ThenReturnException(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(collaboratorMapper.listEntityToListResponse(any())).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> collaboratorService.findBySession(SessionScenarioFactory.SESSION_PAYLOAD.getSessionId()));
    }

    @Test
    @DisplayName("findBySession - Teste tentar realizar listagem de colaborador de uma se????o inexistente")
    public void findBySessionCollaborator_WhenSessionIdInvalid_ThenReturnException(){
        when(sessionRepository.findById(any())).thenThrow(Message.SESSION_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->collaboratorService.findBySession(SessionScenarioFactory.SESSION_PAYLOAD.getSessionId()));
    }

    @Test
    @DisplayName("create - Teste para criar colaborador com sucesso")
    public void create_WhenCollaboratorRequestIsOk_ThenCollaboratorWasCrested(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(collaboratorMapper.requestToEntity(any(),any())).thenReturn(CollaboratorScenarioFactory.COLLABORATOR);
        when(collaboratorRepository.findByCpf(any())).thenReturn(Optional.empty());
        when(collaboratorRepository.save(any())).thenReturn(CollaboratorScenarioFactory.COLLABORATOR);
        when(collaboratorMapper.entityToResposnse(any())).thenReturn(CollaboratorScenarioFactory.COLLABORATOR_RESPONSE);

        ResponseEntity<CollaboratorResponse> response = collaboratorService.save(CollaboratorScenarioFactory.COLLABORATOR_REQUEST);

        assertEquals(CollaboratorScenarioFactory.COLLABORATOR_RESPONSE, response.getBody());
        verify(collaboratorMapper,times(1)).entityToResposnse(any());
        verify(collaboratorMapper,times(1)).requestToEntity(any(),any());
        verify(collaboratorRepository,times(1)).findByCpf(any());
        verify(collaboratorRepository, times(1)).save(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("create - Teste para criar um colaborador com sessionId inexistente")
    public void creat_WhenCollaboratorRequestIsSessionIdInvalid_ThenReturnExption(){
        when(sessionRepository.findById(any())).thenThrow(Message.SESSION_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->collaboratorService.save(CollaboratorScenarioFactory.COLLABORATOR_REQUEST));
    }

    @Test
    @DisplayName("create - Teste tentar cadastrar um colaborador com cpf presente no banco")
    public void create_WhenCpfExistIntoDatabase_ThenReturnException(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(collaboratorMapper.requestToEntity(any(),any())).thenReturn(CollaboratorScenarioFactory.COLLABORATOR);
        when(collaboratorRepository.findByCpf(any())).thenReturn(Optional.of(CollaboratorScenarioFactory.COLLABORATOR));

        assertThrows(BusinessException.class, ()-> collaboratorService.save(CollaboratorScenarioFactory.COLLABORATOR_REQUEST));
        verify(collaboratorMapper,times(1)).requestToEntity(any(),any());
        verify(collaboratorRepository,times(1)).findByCpf(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("update - Teste atualizar colaborador com sucesso")
    public void update_WhenCollaboratorUpdateIsOk_ThenCollaboratorUpdateWasOk(){
        when(collaboratorRepository.findById(any())).thenReturn(Optional.of(CollaboratorScenarioFactory.COLLABORATOR));
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(collaboratorMapper.entityToResposnse(any())).thenReturn(CollaboratorScenarioFactory.COLLABORATOR_RESPONSE);

        ResponseEntity<CollaboratorResponse> response = collaboratorService.update(CollaboratorScenarioFactory.COLLABORATOR_UPDATE_REQUEST, VoterScenarioFactory.VOTER_RESPONSE.getVoterId());

        assertEquals(CollaboratorScenarioFactory.COLLABORATOR_RESPONSE, response.getBody());
        verify(collaboratorRepository,times(1)).findById(any());
        verify(collaboratorMapper, times(1)).entityToResposnse(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar colaborador com id inexistente")
    public void update_WhenCollaboratorIdNotFound_ThenReturnException(){
        when(collaboratorRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,()->collaboratorService.update(CollaboratorScenarioFactory.COLLABORATOR_UPDATE_REQUEST, 99999999999L));
        verify(collaboratorRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar colaborador com sessionId inexistente")
    public void update_WhenCollaboratorRequestIsSessionIdInvalid_ThenReturnException(){
        when(collaboratorRepository.findById(any())).thenReturn(Optional.of(CollaboratorScenarioFactory.COLLABORATOR));
        when(sessionRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,()->collaboratorService.update(CollaboratorScenarioFactory.COLLABORATOR_UPDATE_REQUEST, 99999999999L));
        verify(collaboratorRepository,times(1)).findById(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("delete - Teste deletar colaborador com sucesso")
    public void delete_WhenCollaboratorIdExist_ThenCollaboratorIsDelete(){
        when(collaboratorRepository.findById(any())).thenReturn(Optional.of(CollaboratorScenarioFactory.COLLABORATOR));
        doNothing().when(collaboratorRepository).deleteById(any());

        collaboratorService.delete(334L);

        verify(collaboratorRepository,times(1)).deleteById(any());
    }

    @Test
    @DisplayName("delete - Teste tentar colaborador eleitor com id inexistente")
    public void delete_WhenCollaboratorIdNotExist_ThenReturnException(){
        when(collaboratorRepository.findById(any())).thenThrow(Message.COLLABORATOR_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()-> collaboratorService.delete(334L));
    }
}
