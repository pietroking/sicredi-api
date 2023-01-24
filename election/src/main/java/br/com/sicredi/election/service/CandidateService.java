package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.candidate.CandidateRequest;
import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateResultResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateUpdateRequest;
import br.com.sicredi.election.core.entities.Candidate;
import br.com.sicredi.election.core.mapper.CandidateMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@Service
@Validated
@Slf4j
public class CandidateService {
    private CandidateRepository candidateRepository;
    private CandidateMapper candidateMapper;

    public ResponseEntity<List<CandidateResponse>> findAll(){
        log.info("findAll");
        List<CandidateResponse> candidateResponses = this.candidateMapper.listEntityToListResponse(this.candidateRepository.findAll());
        if (candidateResponses.isEmpty()){
            throw Message.CANDIDATE_LIST_IS_EMPTY.asBusinessException();
        }
        return ResponseEntity.ok(candidateResponses);
    }

    public ResponseEntity<List<CandidateResultResponse>> countVotes(){
        List<CandidateResultResponse> candidateResultResponses = this.candidateRepository.countVotesByOrderByVotesDesc();
        if (candidateResultResponses.isEmpty()){
            throw Message.CANDIDATE_COUNT_LIST_IS_EMPTY.asBusinessException();
        }
        return ResponseEntity.ok(candidateResultResponses);
    }

    public ResponseEntity<List<CandidateResponse>> findByName(String name){
        log.info("findCpf");
        List<CandidateResponse> candidateResponses = this.candidateMapper.listEntityToListResponse(this.candidateRepository.findByNameContainingIgnoreCase(name));
        if (candidateResponses.isEmpty()){
            throw Message.CANDIDATE_LIST_IS_EMPTY.asBusinessException();
        }
        return ResponseEntity.ok(candidateResponses);
    }

    public ResponseEntity<CandidateResponse> save(@Valid CandidateRequest request){
        log.info("save request = {}", request);
        Candidate candidate = this.candidateMapper.requestToEntity(request);
        this.candidateRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw Message.CANDIDATE_CPF_IS_PRESENT.asBusinessException();
        });
        Candidate candidateResult = this.candidateRepository.save(candidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.candidateMapper.entityToResposnse(candidateResult));
    }

    @Transactional
    public ResponseEntity<CandidateResponse> update(@Valid CandidateUpdateRequest request, Long id){
        log.info("update request = {}", request);
        Candidate candidate = this.candidateRepository.findById(id).orElseThrow(Message.CANDIDATE_IS_NOT_EXIST::asBusinessException);
        candidate.update(request.getNumber(),request.getParty());
        return ResponseEntity.status(HttpStatus.OK).body(this.candidateMapper.entityToResposnse(candidate));
    }

    public void delete(Long id){
        Candidate candidate = this.candidateRepository.findById(id).orElseThrow(Message.CANDIDATE_IS_NOT_EXIST::asBusinessException);
        this.candidateRepository.deleteById(candidate.getCandidateId());
        log.info("method = delete by id = {}",id);
    }
}
