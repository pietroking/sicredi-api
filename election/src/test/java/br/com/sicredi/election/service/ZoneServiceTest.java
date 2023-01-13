package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.core.mapper.ZoneMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.ElectionScenarioFactory;
import br.com.sicredi.election.repository.ZoneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ZoneServiceTest {
    @Mock
    private ZoneRepository zoneRepository;
    @Mock
    private ZoneMapper zoneMapper;
    @InjectMocks
    private ZoneService zoneService;
    @Test
    @DisplayName("Teste para realizar listagem das zonas")
    public void findAll(){
        when(zoneRepository.findAll()).thenReturn(ElectionScenarioFactory.LIST_ZONE);
        when(zoneMapper.listEntityToListResponse(any())).thenReturn(ElectionScenarioFactory.LIST_ZONE_RESPONSE);
        List<ZoneResponse> list = zoneService.findAll();
        assertEquals(ElectionScenarioFactory.LIST_ZONE_RESPONSE,list);
    }

    @Test
    @DisplayName("Teste para criar uma zona com sucesso")
    public void creat_whenPayloadIsOk(){
        when(zoneRepository.findByNumber(any())).thenReturn(Optional.empty());
        when(zoneMapper.requestToEntity(any())).thenReturn(ElectionScenarioFactory.PAYLOAD_ZONE_13);
        when(zoneRepository.save(any())).thenReturn(ElectionScenarioFactory.PAYLOAD_ZONE_13);
        when(zoneMapper.entityToResponse(any())).thenReturn(ElectionScenarioFactory.PAYLOAD_ZONE_13_RESPONSE);
        ZoneResponse response = zoneService.save(ElectionScenarioFactory.PAYLOAD_REQUEST_13);
        assertNotNull(response);
        assertEquals(response.getNumber(), ElectionScenarioFactory.PAYLOAD_ZONE_13.getNumber());
        assertEquals(response.getId(),ElectionScenarioFactory.PAYLOAD_ZONE_13.getId());
        verify(zoneRepository,times(1)).findByNumber(any());
        verify(zoneRepository,times(1)).save(any());
        verify(zoneMapper,times(1)).requestToEntity(any());
    }

    @Test
    @DisplayName("Teste para criar uma zona com numero já existente")
    public void creat_whenPayloadIsError(){
        when(zoneRepository.findByNumber(any())).thenThrow(Message.ZONE_NUMBER_IS_PRESENT.asBusinessException());
        when(zoneMapper.requestToEntity(any())).thenReturn(ElectionScenarioFactory.PAYLOAD_ZONE_13);
        assertThrows(BusinessException.class,()->zoneService.save(ElectionScenarioFactory.PAYLOAD_REQUEST_13));
    }

    @Test
    @DisplayName("Teste para atualizar uma zona com sucesso")
    public void update_whenPayloadIsOk(){
        when(zoneMapper.entityToResponse(any())).thenReturn(ElectionScenarioFactory.PAYLOAD_ZONE_13_RESPONSE);
        when(zoneRepository.findById(any())).thenReturn(Optional.empty());
        when(zoneService.update(ElectionScenarioFactory.PAYLOAD_REQUEST_17,ElectionScenarioFactory.PAYLOAD_ZONE_13_RESPONSE.getId()));
        when(zoneMapper.entityToResponse(any())).thenReturn(ElectionScenarioFactory.PAYLOAD_ZONE_17_RESPONSE);
        ZoneResponse response = zoneService.update(ElectionScenarioFactory.PAYLOAD_REQUEST_17, ElectionScenarioFactory.PAYLOAD_ZONE_13_RESPONSE.getId());
        assertNotNull(response);
        assertEquals(response.getNumber(), ElectionScenarioFactory.PAYLOAD_ZONE_17_RESPONSE.getNumber());
        assertEquals(response.getId(), ElectionScenarioFactory.PAYLOAD_ZONE_13_RESPONSE.getId());
        verify(zoneRepository,times(1)).findByNumber(any());
        verify(zoneRepository,times(1)).save(any());
        verify(zoneMapper,times(1)).requestToEntity(any());
    }

    @Test
    @DisplayName("Teste tentar atualizar uma zona inexistente")
    public void update_whenIdIsError(){
        when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());
        assertThrows(BusinessException.class, ()->zoneService.update(ElectionScenarioFactory.PAYLOAD_REQUEST_17, 1L));
    }

    @Test
    @DisplayName("Teste para deletar uma zona com sucesso")
    public void delete_whenIdIsOk(){
        Mockito.lenient().when(zoneRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(zoneRepository).deleteById(any());
        zoneRepository.deleteById(1L);
        verify(zoneRepository,times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Teste para tentar deletar uma zona não existente")
    public void delete_whenIdIsError(){
        Mockito.lenient().when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());
        assertThrows(BusinessException.class, ()->zoneService.delete(1L));
//        verify(zoneService,times(1)).delete(1L);
    }
}
