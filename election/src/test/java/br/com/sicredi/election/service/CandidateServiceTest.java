package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateResultResponse;
import br.com.sicredi.election.core.mapper.CandidateMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.CandidateScenarioFactory;
import br.com.sicredi.election.repository.CandidateRepository;
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
public class CandidateServiceTest {
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private CandidateMapper candidateMapper;
    @InjectMocks
    private CandidateService candidateService;

    @Test
    @DisplayName("findAll - Teste para realizar listagem de candidatos")
    public void findAll_WhenExistCandidates_ThenReturnAListOfCandidateCreated(){
        when(candidateRepository.findAll()).thenReturn(CandidateScenarioFactory.LIST_CANDIDATE);
        when(candidateMapper.listEntityToListResponse(any())).thenReturn(CandidateScenarioFactory.LIST_CANDIDATE_RESPONSE);

        ResponseEntity<List<CandidateResponse>> list = candidateService.findAll();

        assertEquals(CandidateScenarioFactory.LIST_CANDIDATE_RESPONSE, list.getBody());
        verify(candidateRepository,times(1)).findAll();
        verify(candidateMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("findAll - Teste tentar realizar listagem vazia de candidatos")
    public void findAll_WhenNotExistCandidates_ThenReturnException(){
        when(candidateRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> candidateService.findAll());
    }

    @Test
    @DisplayName("countVotes - Teste retornar uma listas com a contagem de votos")
    public void countVotes_WhenExistCountVotes_ThenReturnAListOfVotes(){

        when(candidateRepository.countVotesByOrderByVotesDesc()).thenReturn(CandidateScenarioFactory.LIST_CANDIDATE_RESULT_RESPONSE);

        ResponseEntity<List<CandidateResultResponse>> listResponseEntity = candidateService.countVotes();

        assertEquals(CandidateScenarioFactory.LIST_CANDIDATE_RESULT_RESPONSE, listResponseEntity.getBody());
        verify(candidateRepository,times(1)).countVotesByOrderByVotesDesc();
    }

    @Test
    @DisplayName("countVotes - Teste tentar retornar uma lista vazia da contagem de votos")
    public void countVotes_WhenNotExistCountVotes_ThenReturnException(){
        when(candidateRepository.countVotesByOrderByVotesDesc()).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class,()-> candidateService.countVotes());
        verify(candidateRepository,times(1)).countVotesByOrderByVotesDesc();
    }

    @Test
    @DisplayName("findByName - Teste retornar lista de candidatos que contem o nome buscado")
    public void findByName_WhenExistAName_ThenReturnAList(){
        when(candidateRepository.findByNameContainingIgnoreCase(any())).thenReturn(CandidateScenarioFactory.LIST_CANDIDATE);
        when(candidateMapper.listEntityToListResponse(any())).thenReturn(CandidateScenarioFactory.LIST_CANDIDATE_RESPONSE);

        ResponseEntity<List<CandidateResponse>> list = candidateService.findByName("Fulano");

        assertEquals(CandidateScenarioFactory.LIST_CANDIDATE_RESPONSE, list.getBody());
        verify(candidateRepository,times(1)).findByNameContainingIgnoreCase(any());
        verify(candidateMapper,times(1)).listEntityToListResponse(any());
    }

    @Test
    @DisplayName("findByName - Teste tentar retornar uma lista de candidatos com um nome que nenhum contem")
    public void findByName_WhenNotExistAName_ThenReturnException(){
        when(candidateRepository.findByNameContainingIgnoreCase(any())).thenReturn(new ArrayList<>());

        assertThrows(BusinessException.class, ()-> candidateService.findByName("Fulano"));
        verify(candidateRepository,times(1)).findByNameContainingIgnoreCase(any());
    }

    @Test
    @DisplayName("create - Teste para criar candidato com sucesso")
    public void create_WhenCandidateRequestIsOk_ThenCadidateWasCrested(){
        when(candidateMapper.requestToEntity(any())).thenReturn(CandidateScenarioFactory.CANDIDATE);
        when(candidateRepository.findByCpf(any())).thenReturn(Optional.empty());
        when(candidateRepository.save(any())).thenReturn(CandidateScenarioFactory.CANDIDATE);
        when(candidateMapper.entityToResposnse(any())).thenReturn(CandidateScenarioFactory.CANDIDATE_RESPONSE);

        ResponseEntity<CandidateResponse> response = candidateService.save(CandidateScenarioFactory.CANDIDATE_REQUEST);

        assertEquals(CandidateScenarioFactory.CANDIDATE_RESPONSE, response.getBody());
        verify(candidateMapper,times(1)).entityToResposnse(any());
        verify(candidateMapper,times(1)).requestToEntity(any());
        verify(candidateRepository,times(1)).findByCpf(any());
        verify(candidateRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("create - Teste tentar cadastrar um candidato com cpf presente no banco")
    public void create_WhenCpfExistIntoDatabase_ThenReturnException(){
        when(candidateMapper.requestToEntity(any())).thenReturn(CandidateScenarioFactory.CANDIDATE);
        when(candidateRepository.findByCpf(any())).thenReturn(Optional.of(CandidateScenarioFactory.CANDIDATE));

        assertThrows(BusinessException.class,()->candidateService.save(CandidateScenarioFactory.CANDIDATE_REQUEST));
        verify(candidateMapper,times(1)).requestToEntity(any());
        verify(candidateRepository,times(1)).findByCpf(any());
    }

    @Test
    @DisplayName("update - Teste atualizar candidato com sucesso")
    public void update_WhenCandidateUpdateIsOk_ThenCandidateUpdateWasOk(){
        when(candidateRepository.findById(any())).thenReturn(Optional.of(CandidateScenarioFactory.CANDIDATE));
        when(candidateMapper.entityToResposnse(any())).thenReturn(CandidateScenarioFactory.CANDIDATE_RESPONSE);

        ResponseEntity<CandidateResponse> response = candidateService.update(CandidateScenarioFactory.CANDIDATE_UPDATE_REQUEST, 334L);

        assertEquals(CandidateScenarioFactory.CANDIDATE_RESPONSE, response.getBody());
        verify(candidateRepository,times(1)).findById(any());
        verify(candidateMapper, times(1)).entityToResposnse(any());
    }

    @Test
    @DisplayName("update - Teste tentar atualizar candidato com id inexistente")
    public void update_WhenIdNotFound_ThenReturnException(){
        when(candidateRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, ()->candidateService.update(CandidateScenarioFactory.CANDIDATE_UPDATE_REQUEST, 334L));
        verify(candidateRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("delete - Teste deletar candidato com sucesso")
    public void delete_WhenCandidateIdExist_ThenCandidateIsDelete(){
        when(candidateRepository.findById(any())).thenReturn(Optional.of(CandidateScenarioFactory.CANDIDATE));
        doNothing().when(candidateRepository).deleteById(any());

        candidateService.delete(334L);

        verify(candidateRepository,times(1)).deleteById(any());
    }

    @Test
    @DisplayName("delete - Teste tentar deletar candidato com id inexistente")
    public void delete_WhenCandidateIdNotExist_ThenReturnException(){
        when(candidateRepository.findById(any())).thenThrow(Message.CANDIDATE_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class, ()-> candidateService.delete(334L));
    }
}
