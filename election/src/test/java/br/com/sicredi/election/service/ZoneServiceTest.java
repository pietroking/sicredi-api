package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.core.mapper.ZoneMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.ZoneScenarioFactory;
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
public class ZoneServiceTest {
    @Mock
    private ZoneRepository zoneRepository;
    @Mock
    private ZoneMapper zoneMapper;
    @InjectMocks
    private ZoneService zoneService;
    @Test
    @DisplayName("findall - Teste para realizar listagem das zonas")
    public void findAll(){
        when(zoneRepository.findAll()).thenReturn(ZoneScenarioFactory.LIST_ZONE);
        when(zoneMapper.listEntityToListResponse(any())).thenReturn(ZoneScenarioFactory.LIST_ZONE_RESPONSE);

        ResponseEntity<List<ZoneResponse>> list = zoneService.findAll();

        assertEquals(ZoneScenarioFactory.LIST_ZONE_RESPONSE,list.getBody());
    }

    @Test
    @DisplayName("findAll - Teste tentar realizar listagem vazia das zonas")
    public void findAllIsEmpty(){
        when(zoneRepository.findAll()).thenReturn(new ArrayList<>());
        when(zoneMapper.listEntityToListResponse(any())).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> zoneService.findAll());
    }

    @Test
    @DisplayName("create - Teste para criar uma zona com sucesso")
    public void creat_whenPayloadIsOk(){
        when(zoneRepository.findByNumber(any())).thenReturn(Optional.empty());
        when(zoneMapper.requestToEntity(any())).thenReturn(ZoneScenarioFactory.PAYLOAD_ZONE_13);
        when(zoneRepository.save(any())).thenReturn(ZoneScenarioFactory.PAYLOAD_ZONE_13);
        when(zoneMapper.entityToResponse(any())).thenReturn(ZoneScenarioFactory.PAYLOAD_ZONE_13_RESPONSE);

        ResponseEntity<ZoneResponse> response = zoneService.save(ZoneScenarioFactory.PAYLOAD_REQUEST_13);

        assertNotNull(response);
        assertEquals(response.getBody().getNumber(), ZoneScenarioFactory.PAYLOAD_ZONE_13.getNumber());
        assertEquals(response.getBody().getZoneId(), ZoneScenarioFactory.PAYLOAD_ZONE_13.getZoneId());
        verify(zoneRepository,times(1)).findByNumber(any());
        verify(zoneRepository,times(1)).save(any());
        verify(zoneMapper,times(1)).requestToEntity(any());
    }

    @Test
    @DisplayName("create - Teste tentar criar zona com numero ja existente")
    public void creat_whenIsError(){
        when(zoneMapper.requestToEntity(any())).thenReturn(ZoneScenarioFactory.PAYLOAD_ZONE_13);
        when(zoneRepository.findByNumber(any())).thenThrow(Message.ZONE_NUMBER_IS_PRESENT.asBusinessException());

        assertThrows(BusinessException.class,()->zoneService.save(ZoneScenarioFactory.PAYLOAD_REQUEST_13));
    }

    @Test
    @DisplayName("create - Teste para criar uma zona com numero j?? existente")
    public void creat_whenPayloadIsError(){
        when(zoneMapper.requestToEntity(any())).thenReturn(ZoneScenarioFactory.PAYLOAD_ZONE_13);
        when(zoneRepository.findByNumber(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));

        assertThrows(BusinessException.class,()->zoneService.save(ZoneScenarioFactory.PAYLOAD_REQUEST_13));
    }

    @Test
    @DisplayName("update - Teste para atualizar uma zona com sucesso")
    public void update_whenPayloadIsOk(){
        when(zoneMapper.entityToResponse(any())).thenReturn(ZoneScenarioFactory.PAYLOAD_ZONE_13_RESPONSE);
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(zoneRepository.findByNumber(any())).thenReturn(Optional.empty());

        ResponseEntity<ZoneResponse> response = zoneService.update(ZoneScenarioFactory.PAYLOAD_REQUEST_13, ZoneScenarioFactory.PAYLOAD_ZONE_13_RESPONSE.getZoneId());

        assertNotNull(response);
        assertEquals(response.getBody().getNumber(), ZoneScenarioFactory.PAYLOAD_ZONE_13_RESPONSE.getNumber());
        assertEquals(response.getBody().getZoneId(), ZoneScenarioFactory.PAYLOAD_ZONE_13_RESPONSE.getZoneId());
        verify(zoneRepository,times(1)).findByNumber(any());
        verify(zoneRepository,times(1)).findById(any());
        verify(zoneMapper,times(1)).entityToResponse(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar uma zona inexistente")
    public void update_whenIdIsError(){
        when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()->zoneService.update(ZoneScenarioFactory.PAYLOAD_REQUEST_17, 1L));
    }

    @Test
    @DisplayName("update - Teste tentar atualizar uma zona com um numero j?? existente")
    public void update_whenNumberExist(){
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        when(zoneRepository.findByNumber(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));

        assertThrows(BusinessException.class, ()->zoneService.update(ZoneScenarioFactory.PAYLOAD_REQUEST_13, 1L));
    }

    @Test
    @DisplayName("delete - Teste para deletar uma zona com sucesso")
    public void delete_whenIdIsOk(){
        when(zoneRepository.findById(any())).thenReturn(Optional.of(ZoneScenarioFactory.PAYLOAD_ZONE_13));
        doNothing().when(zoneRepository).deleteById(any());

        zoneService.delete(13L);

        verify(zoneRepository,times(1)).deleteById(any());
    }

    @Test
    @DisplayName("delete - Teste para tentar deletar uma zona n??o existente")
    public void delete_whenIdIsError(){
        when(zoneRepository.findById(any())).thenThrow(Message.ZONE_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()->zoneService.delete(1L));
    }
}
