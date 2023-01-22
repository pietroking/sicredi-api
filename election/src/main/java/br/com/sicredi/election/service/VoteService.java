package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.vote.VoteRequest;
import br.com.sicredi.election.core.dto.vote.VoteResponse;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Vote;
import br.com.sicredi.election.core.entities.Voter;
import br.com.sicredi.election.core.mapper.VoteMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.CandidateRepository;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.VoteRepository;
import br.com.sicredi.election.repository.VoterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class VoteService {
    private SessionRepository sessionRepository;
    private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private VoterRepository voterRepository;
    private VoteMapper voteMapper;

    public ResponseEntity<List<VoteResponse>> findAll(){
        log.info("findAll");
        List<VoteResponse> voteResponses = this.voteMapper.listEntityToListResponse(this.voteRepository.findAll());
        if (voteResponses.isEmpty()){
            throw Message.VOTE_LIST_IS_EMPTY.asBusinessException();
        }
        return ResponseEntity.ok(voteResponses);
    }

    @Transactional
    public ResponseEntity<VoteResponse> save(@Valid VoteRequest request){
        log.info("save request = {}", request);
        Session session = this.sessionRepository.findById(request.getSessionId()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        session.getListVoter().stream().filter(voter -> voter.getCpf().equals(request.getCpf())).findFirst().orElseThrow(Message.SESSION_VOTER_CPF_IS_NOT_PRESENT::asBusinessException);
        this.candidateRepository.findByNumber(request.getNumber()).orElseThrow(Message.CANDIDATE_NOT_EXIST::asBusinessException);
        Voter voter = this.voterRepository.findByCpf(request.getCpf()).get();
        if(voter.getStatusVote().equals(Boolean.TRUE)){
            throw Message.VOTER_HAS_ALREADY_VOTED.asBusinessException();
        }
        Vote vote = this.voteMapper.requestToEntity(request);
        vote.addSession(session);
        Vote voteResult = this.voteRepository.save(vote);
        voter.setStatusVote(Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.voteMapper.entityToResponse(voteResult, session.getSessionId()));
    }

//    public void delete(Long id){
//        Vote vote = this.voteRepository.findById(id).orElseThrow(Message.CANDIDATE_CPF_IS_NOT_EXIST::asBusinessException);
//        this.voteRepository.deleteById(vote.getVoteId());
//        log.info("method = delete by id = {}",id);
//    }
}
