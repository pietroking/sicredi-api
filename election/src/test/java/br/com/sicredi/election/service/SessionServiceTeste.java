package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.mapper.SessionMapper;
import br.com.sicredi.election.feature.SessionScenarioFactory;
import br.com.sicredi.election.repository.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTeste {
    @Mock
    private SessionRepository sessionRepository;
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

    public void findByZone(){

    }
}
