package br.com.sicredi.election.service;

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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
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
        when(sessionRepository.findAll()).thenReturn(SessionScenarioFactory.LIST_SESSION);
        when(sessionMapper.listEntityToListResponse(any())).thenReturn(SessionScenarioFactory.LIST_SESSION_RESPONSE);

        ResponseEntity<List<SessionResponse>> list = sessionService.findAll();

        assertEquals(SessionScenarioFactory.LIST_SESSION_RESPONSE,list.getBody());
        verify(sessionRepository,times(1)).findAll();
        verify(sessionMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("Teste para realizar listagem das seções vazias")
    public void findAllSessionIsEmpty(){
        when(sessionRepository.findAll()).thenReturn(new ArrayList<>());
        when(sessionMapper.listEntityToListResponse(any())).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> sessionService.findAll());
    }

    @Test
    @DisplayName("Teste para realizar listagem das seções de uma zona")
    public void findByZone(){
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.listEntityToListResponse(any())).thenReturn(SessionScenarioFactory.LIST_SESSION_RESPONSE);

        ResponseEntity<List<SessionResponse>> list = sessionService.findByZone(ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId());

        assertEquals(SessionScenarioFactory.LIST_SESSION_RESPONSE, list.getBody());
        verify(zoneRepository,times(1)).findById(1L);
        verify(sessionMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("Teste para realizar listagem das seções de uma zona vazia")
    public void findByZoneIsEmpty(){
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.listEntityToListResponse(any())).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> sessionService.findByZone(ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId()));
    }

    @Test
    @DisplayName("Teste tentar realizar listagem das seções de uma zona inexistente")
    public void findByZoneError(){
        when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->sessionService.findByZone(ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId()));
    }

    @Test
    @DisplayName("Teste para criar uma seção com sucesso")
    public void creat_whenPayloadIsOk(){

        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.requstToEntity(any(), any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD);
        when(sessionRepository.findByNumber(any())).thenReturn(Optional.empty());
        when(sessionRepository.findByUrnNumber(any())).thenReturn(Optional.empty());
        when(sessionRepository.save(any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD);
        when(sessionMapper.entityToResponse(any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE);

        ResponseEntity<SessionResponse> response = sessionService.save(SessionScenarioFactory.SESSION_PAYLOAD_REQUEST);

        assertNotNull(response);
        assertEquals(response.getBody().getSessionId(), SessionScenarioFactory.SESSION_PAYLOAD.getSessionId());
        assertEquals(response.getBody().getNumber(), SessionScenarioFactory.SESSION_PAYLOAD.getNumber());
        assertEquals(response.getBody().getZoneId(), SessionScenarioFactory.SESSION_PAYLOAD.getZone().getZoneId());
        verify(zoneRepository,times(1)).findById(any());
        verify(sessionMapper,times(1)).requstToEntity(any(),any());
        verify(sessionRepository,times(1)).findByNumber(any());
        verify(sessionRepository,times(1)).findByUrnNumber(any());
        verify(sessionRepository,times(1)).save(any());
        verify(sessionMapper,times(1)).entityToResponse(any());
    }

    @Test
    @DisplayName("create - Teste para criar uma seção com zoneId inexistente")
    public void creat_whenPayloadIsZoneIdError(){
        when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->sessionService.save(SessionScenarioFactory.SESSION_PAYLOAD_REQUEST));
    }

    @Test
    @DisplayName("create - Teste para criar uma seção com numero já existente")
    public void creat_whenPayloadIsNumberSessionError(){
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.requstToEntity(any(), any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD);
        when(sessionRepository.findByNumber(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));

        assertThrows(BusinessException.class,()->sessionService.save(SessionScenarioFactory.SESSION_PAYLOAD_REQUEST));
    }

    @Test
    @DisplayName("create - Teste para criar uma seção com numero de urna já existente")
    public void creat_whenPayloadIsUrnNumberSessionError(){
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(sessionMapper.requstToEntity(any(), any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD);
        when(sessionRepository.findByNumber(any())).thenReturn(Optional.empty());
        when(sessionRepository.findByUrnNumber(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));

        assertThrows(BusinessException.class,()->sessionService.save(SessionScenarioFactory.SESSION_PAYLOAD_REQUEST));
    }

    @Test
    @DisplayName("update - Teste para atualizar uma seção com sucesso")
    public void update_whenPayloadIsOk(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(sessionRepository.findByUrnNumber(any())).thenReturn(Optional.empty());
        when(sessionMapper.entityToResponse(any())).thenReturn(SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE);

        ResponseEntity<SessionResponse> response = sessionService.update(SessionScenarioFactory.SESSION_UPDATE_REQUEST, SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getSessionId());

        assertNotNull(response);
        assertEquals(response.getBody().getZoneId(),SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getZoneId());
        assertEquals(response.getBody().getNumber(),SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getNumber());
        assertEquals(response.getBody().getUrnNumber(),SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getUrnNumber());
        assertEquals(response.getBody().getSessionId(),SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getSessionId());
        verify(sessionRepository,times(1)).findById(any());
        verify(sessionRepository,times(1)).findByUrnNumber(any());
        verify(sessionMapper,times(1)).entityToResponse(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar uma seção inexistente")
    public void update_whenSessionIdError(){
        when(sessionRepository.findById(any())).thenThrow(Message.SESSION_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->sessionService.update(SessionScenarioFactory.SESSION_UPDATE_REQUEST, SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getSessionId()));
    }

    @Test
    @DisplayName("update - Teste tentar atualizar uma seção com um numero já existente")
    public void update_whenNumberSessionIsError(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(sessionRepository.findByUrnNumber(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));

        assertThrows(BusinessException.class,()->sessionService.update(SessionScenarioFactory.SESSION_UPDATE_REQUEST, SessionScenarioFactory.SESSION_PAYLOAD_RESPONSE.getSessionId()));
    }

    @Test
    @DisplayName("delete - Teste para deletar uma seção com sucesso")
    public void delete_whenIdIsOk(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        doNothing().when(sessionRepository).deleteById(any());

        sessionService.delete(1L);

        verify(sessionRepository,times(1)).deleteById(any());
    }

    @Test
    @DisplayName("delete - Teste para tentar deletar uma seção inexistente")
    public void delete_whenIdIsError(){
        when(sessionRepository.findById(any())).thenThrow(Message.SESSION_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()->sessionService.delete(1L));
    }

}
