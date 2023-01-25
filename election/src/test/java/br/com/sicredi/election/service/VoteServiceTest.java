package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.vote.VoteResponse;
import br.com.sicredi.election.core.mapper.VoteMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.exception.BusinessException;
import br.com.sicredi.election.feature.CandidateScenarioFactory;
import br.com.sicredi.election.feature.SessionScenarioFactory;
import br.com.sicredi.election.feature.VoteScenarioFactory;
import br.com.sicredi.election.feature.VoterScenarioFactory;
import br.com.sicredi.election.repository.CandidateRepository;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.VoteRepository;
import br.com.sicredi.election.repository.VoterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @Mock
    private VoterRepository voterRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private VoteMapper voteMapper;
    @InjectMocks
    private VoteService voteService;

    @Test
    @DisplayName("create - Teste para criar voto com sucesso")
    public void create_WhenVoteRequestIsOk_ThenVoteWasCrested(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(candidateRepository.findByNumber(any())).thenReturn(Optional.of(CandidateScenarioFactory.CANDIDATE));
        when(voterRepository.findByCpf(any())).thenReturn(Optional.of(VoterScenarioFactory.VOTER));
        when(voteMapper.requestToEntity(any())).thenReturn(VoteScenarioFactory.VOTE);
        when(voteRepository.save(any())).thenReturn(VoteScenarioFactory.VOTE);
        when(voteMapper.entityToResponse(any(),any())).thenReturn(VoteScenarioFactory.VOTE_RESPONSE);

        ResponseEntity<VoteResponse> response = voteService.save(VoteScenarioFactory.VOTE_REQUEST);

        assertEquals(VoteScenarioFactory.VOTE_RESPONSE, response.getBody());
        verify(voteMapper,times(1)).entityToResponse(any(),any());
        verify(voteMapper,times(1)).requestToEntity(any());
        verify(voterRepository,times(1)).findByCpf(any());
        verify(voteRepository, times(1)).save(any());
        verify(sessionRepository,times(1)).findById(any());
        verify(candidateRepository,times(1)).findByNumber(any());
    }

    @Test
    @DisplayName("create - Teste tentar criar voto com sessionId invalida")
    public void create_WhenVoteRequestIsSessionIdInvalid_ThenReturnException(){
        when(sessionRepository.findById(any())).thenThrow(Message.SESSION_IS_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->voteService.save(VoteScenarioFactory.VOTE_REQUEST));
    }

    @Test
    @DisplayName("create - Teste tentar criar voto com numero de candidato invalid")
    public void create_WhenVoteRequestIsCandidateNumberInvalid_ThenReturnException(){
        when(sessionRepository.findById(any())).thenReturn(Optional.of(SessionScenarioFactory.SESSION_PAYLOAD));
        when(candidateRepository.findByNumber(any())).thenThrow(Message.CANDIDATE_NOT_EXIST.asBusinessException());

        assertThrows(BusinessException.class,()->voteService.save(VoteScenarioFactory.VOTE_REQUEST));
    }

    @Test
    @DisplayName("delete - Teste delete voto com sucesso")
    public void delete_WhenVoteRequestIsOk_ThenReturnDeleteVoteSucessfully(){
        doNothing().when(voteRepository).deleteById(any());

        voteService.delete(334L);

        verify(voteRepository,times(1)).deleteById(any());
    }
}
