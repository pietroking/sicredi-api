package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.candidate.CandidateRequest;
import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.entities.Candidate;
import br.com.sicredi.election.core.mapper.CandidateMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public List<CandidateResponse> findAll(){
        log.info("findAll");
        return this.candidateMapper.listEntityToListResponse(this.candidateRepository.findAll());
    }

    public CandidateResponse findByName(String name){
        log.info("findCpf");
        return this.candidateMapper.entityToResposnse(this.candidateRepository.findByName(name));
    }

    public CandidateResponse save(@Valid CandidateRequest request){
        log.info("save request = {}", request);
        Candidate candidate = this.candidateMapper.requestToEntity(request);
        this.candidateRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw Message.CANDIDATE_CPF_IS_PRESENT.asBusinessException();
        });
        Candidate candidateResult = this.candidateRepository.save(candidate);
        return this.candidateMapper.entityToResposnse(candidateResult);
    }

    public void delete(Long id){
        Candidate candidate = this.candidateRepository.findById(id).orElseThrow(Message.CANDIDATE_CPF_IS_NOT_EXIST::asBusinessException);
        this.candidateRepository.deleteById(candidate.getId());
        log.info("method = delete by id = {}",id);
    }
}
