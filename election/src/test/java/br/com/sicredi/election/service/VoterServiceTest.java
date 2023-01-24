package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.mapper.VoterMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.SessionScenarioFactory;
import br.com.sicredi.election.feature.VoterScenarioFactory;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.VoterRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoterServiceTest {
    @Mock
    private VoterRepository voterRepository;
    @Mock
    private VoterMapper voterMapper;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private VoterService voterService;

    @Test
    @DisplayName("findAll - Teste para realizar listagem de eleitores")
    public void findAll_WhenExistVoter_ThenReturnListOfVoterCreated(){
        // MOCKS
        when(voterRepository.findAll()).thenReturn(VoterScenarioFactory.LIST_VOTER);
        when(voterMapper.listEntityToListResponse(any())).thenReturn(VoterScenarioFactory.LIST_VOTER_RESPONSE);
        // CHAMADA AO SERVICE A SER TESTADO
        ResponseEntity<List<VoterResponse>> list = voterService.findAll();
        // COMPARAÇÔES
        assertEquals(VoterScenarioFactory.LIST_VOTER_RESPONSE, list.getBody());
        verify(voterRepository,times(1)).findAll();
        verify(voterMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("findAll - Teste tentar realizar listagem vazia de eleitores")
    public void findAll_WhenNotExistVoter_ThenReturnException(){
        // MOCKS
        when(voterRepository.findAll()).thenReturn(new ArrayList<>());
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class, ()-> voterService.findAll(),"Não existem eleitores criados.");
    }

    @Test
    @DisplayName("create - Teste para criar eleitor com sucesso")
    public void create_WhenVoterRequestIsOk_ThenVoterWasCrested(){
        // MOCKS
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(voterMapper.requestToEntity(any(),any())).thenReturn(VoterScenarioFactory.VOTER);
        when(voterRepository.findByCpf(any())).thenReturn(Optional.empty());
        when(voterRepository.save(any())).thenReturn(VoterScenarioFactory.VOTER);
        when(voterMapper.entityToResposnse(any())).thenReturn(VoterScenarioFactory.VOTER_RESPONSE);
        // CHAMADA AO SERVICE A SER TESTADO
        ResponseEntity<VoterResponse> response = voterService.save(VoterScenarioFactory.VOTER_REQUEST);
        // COMPARAÇÔES
        assertEquals(VoterScenarioFactory.VOTER_RESPONSE, response.getBody());
        verify(voterMapper,times(1)).entityToResposnse(any());
        verify(voterMapper,times(1)).requestToEntity(any(),any());
        verify(voterRepository,times(1)).findByCpf(any());
        verify(voterRepository, times(1)).save(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("create - Teste para criar um eleitor com sessionId inexistente")
    public void creat_WhenVoterRequestIsSessionIdInvalid_ThenReturnExption(){
        // MOCKS
        when(sessionRepository.findById(any())).thenThrow(Message.SESSION_IS_NOT_EXIST.asBusinessException());
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class,()->voterService.save(VoterScenarioFactory.VOTER_REQUEST));
    }

    @Test
    @DisplayName("create - Teste tentar cadastrar um eleitor com cpf presente no banco")
    public void create_WhenCpfExistIntoDatabase_ThenReturnException(){
        // MOCKS
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(voterMapper.requestToEntity(any(),any())).thenReturn(VoterScenarioFactory.VOTER);
        when(voterRepository.findByCpf(any())).thenReturn(Optional.of(VoterScenarioFactory.VOTER));
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class, ()-> voterService.save(VoterScenarioFactory.VOTER_REQUEST));
        verify(voterMapper,times(1)).requestToEntity(any(),any());
        verify(voterRepository,times(1)).findByCpf(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("update - Teste atualizar eleitor com sucesso")
    public void update_WhenVoterUpdateIsOk_ThenVoterUpdateWasOk(){
        // MOCKS
        when(voterRepository.findById(any())).thenReturn(Optional.of(VoterScenarioFactory.VOTER));
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(voterMapper.entityToResposnse(any())).thenReturn(VoterScenarioFactory.VOTER_RESPONSE);
        // CHAMADA AO SERVICE A SER TESTADO
        ResponseEntity<VoterResponse> response = voterService.update(VoterScenarioFactory.VOTER_UPDATE_REQUEST, VoterScenarioFactory.VOTER_RESPONSE.getVoterId());
        // COMPARAÇÔES
        assertEquals(VoterScenarioFactory.VOTER_RESPONSE, response.getBody());
        verify(voterRepository,times(1)).findById(any());
        verify(voterMapper, times(1)).entityToResposnse(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar eleitor com id inexistente")
    public void update_WhenVoterIdNotFound_ThenReturnException(){
        // MOCKS
        when(voterRepository.findById(any())).thenReturn(Optional.empty());
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class,()->voterService.update(VoterScenarioFactory.VOTER_UPDATE_REQUEST, 99999999999L));
        verify(voterRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar eleitor com sessionId inexistente")
    public void update_WhenVoterRequestIsSessionIdInvalid_ThenReturnException(){
        // MOCKS
        when(voterRepository.findById(any())).thenReturn(Optional.of(VoterScenarioFactory.VOTER));
        when(sessionRepository.findById(any())).thenReturn(Optional.empty());
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class,()->voterService.update(VoterScenarioFactory.VOTER_UPDATE_REQUEST, 99999999999L));
        verify(voterRepository,times(1)).findById(any());
        verify(sessionRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("delete - Teste deletar eleitor com sucesso")
    public void delete_WhenVoterIdExist_ThenVoterIsDelete(){
        // MOCKS
        when(voterRepository.findById(any())).thenReturn(Optional.of(VoterScenarioFactory.VOTER));
        doNothing().when(voterRepository).deleteById(any());
        // CHAMADA AO SERVICE A SER TESTADO
        voterService.delete(334L);
        // COMPARAÇÔES
        verify(voterRepository,times(1)).deleteById(any());
    }

    @Test
    @DisplayName("delete - Teste tentar deletar eleitor com id inexistente")
    public void delete_WhenVoterIdNotExist_ThenReturnException(){
        // MOCKS
        when(voterRepository.findById(any())).thenThrow(Message.VOTER_IS_NOT_EXIST.asBusinessException());
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class, ()-> voterService.delete(334L));
    }
}
