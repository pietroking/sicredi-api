package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.mapper.SessionMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.SessionScenarioFactory;
import br.com.sicredi.election.feature.ZoneScenarioFactory;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.ZoneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTeste {
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private ZoneRepository zoneRepository;
    @Mock
    private SessionMapper sessionMapper;
    @InjectMocks
    private SessionService sessionService;

    @Test
    @DisplayName("Teste para realizar listagem das seções")
    public void findAllSession(){
        // MOCKS
        when(sessionRepository.findAll()).thenReturn(SessionScenarioFactory.LIST_SESSION);
        when(sessionMapper.listEntityToListResponse(any())).thenReturn(SessionScenarioFactory.LIST_SESSION_RESPONSE);
        // CHAMADA AO SERVICE A SER TESTADO
        List<SessionResponse> list = sessionService.findAll();
        // COMPARAÇÔES
        assertEquals(SessionScenarioFactory.LIST_SESSION_RESPONSE,list);
        verify(sessionRepository,times(1)).findAll();
        verify(sessionMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("Teste para realizar listagem das seções de uma zona")
    public void findByZone(){
        // MOCKS
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.listEntityToListResponse(any())).thenReturn(SessionScenarioFactory.LIST_SESSION_RESPONSE);
        // CHAMADA AO SERVICE A SER TESTADO
        List<SessionResponse> list = sessionService.findByZone(ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId());
        // COMPARAÇÔES
        assertEquals(SessionScenarioFactory.LIST_SESSION_RESPONSE, list);
        verify(zoneRepository,times(1)).findById(1L);
        verify(sessionMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("Teste tentar realizar listagem das seções de uma zona inexistente")
    public void findByZoneError(){
        // MOCKS
        when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
        assertThrows(BusinessException.class,()->sessionService.findByZone(ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId()));
    }

    @Test
    @DisplayName("Teste para criar uma seção com sucesso")
    public void creat_whenPayloadIsOk(){
        // MOCKS
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.requstToEntity(any(), any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD);
        when(sessionRepository.findByNumber(any())).thenReturn(Optional.empty());
        when(sessionRepository.save(any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD);
        when(sessionMapper.entityToResponse(any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE);
        // CHAMADA AO SERVICE A SER TESTADO
        SessionResponse response = sessionService.save(SessionScenarioFactory.SESSION_PAYLOAD_REQUEST);
        // COMPARAÇÔES
        assertNotNull(response);
        assertEquals(response.getSessionId(), SessionScenarioFactory.SESSION_PAYLOAD.getSessionId());
        assertEquals(response.getNumber(), SessionScenarioFactory.SESSION_PAYLOAD.getNumber());
        assertEquals(response.getZoneId(), SessionScenarioFactory.SESSION_PAYLOAD.getZone().getZoneId());
        verify(zoneRepository,times(1)).findById(any());
        verify(sessionMapper,times(1)).requstToEntity(any(),any());
        verify(sessionRepository,times(1)).findByNumber(any());
        verify(sessionRepository,times(1)).save(any());
        verify(sessionMapper,times(1)).entityToResponse(any());
    }

    @Test
    @DisplayName("Teste para criar uma seção com zoneId inexistente")
    public void creat_whenPayloadIsZoneIdError(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

    @Test
    @DisplayName("Teste para criar uma seção com numero já existente")
    public void creat_whenPayloadIsNumberSessionError(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

    @Test
    @DisplayName("Teste para atualizar uma seção com sucesso")
    public void update_whenPayloadIsOk(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

    @Test
    @DisplayName("Teste tentar atualizar uma seção inexistente")
    public void update_whenSessionIdError(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

    @Test
    @DisplayName("Teste tentar atualizar uma seção com um numero já existente")
    public void update_whenNumberSessionIsError(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

    @Test
    @DisplayName("Teste para deletar uma seção com sucesso")
    public void delete_whenIdIsOk(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

    @Test
    @DisplayName("Teste para tentar deletar uma seção inexistente")
    public void delete_whenIdIsError(){
        // MOCKS
        // CHAMADA AO SERVICE A SER TESTADO
        // COMPARAÇÔES
    }

}
