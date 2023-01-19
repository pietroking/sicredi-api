package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.voter.VoterRequest;
import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.dto.voter.VoterUpdateRequest;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Voter;
import br.com.sicredi.election.core.mapper.VoterMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.VoterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class VoterService {
    private VoterRepository voterRepository;
    private VoterMapper voterMapper;
    private SessionRepository sessionRepository;

    public List<VoterResponse> findAll(){
        log.info("findAll");
        List<VoterResponse> voterResponses = this.voterMapper.listEntityToListResponse(this.voterRepository.findAll());
        if (voterResponses.isEmpty()){
            throw Message.VOTER_LIST_IS_EMPTY.asBusinessException();
        }
        return voterResponses;
    }

    public VoterResponse save(@Valid VoterRequest request){
        log.info("save request = {}", request);
        Session session = this.sessionRepository.findById(request.getSessionId()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        Voter voter = this.voterMapper.requestToEntity(request,session);
        session.addListVoter(voter);
        this.voterRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw Message.VOTER_CPF_IS_PRESENT.asBusinessException();
        });
        Voter voterResult = this.voterRepository.save(voter);
        return this.voterMapper.entityToResposnse(voterResult);
    }

    @Transactional
    public VoterResponse update(@Valid VoterUpdateRequest request, Long id){
        log.info("update request = {}", request);
        Voter voter = this.voterRepository.findById(id).orElseThrow(Message.VOTER_IS_NOT_EXIST::asBusinessException);
        Session session = this.sessionRepository.findById(request.getSessionId()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        voter.update(session);
        session.addListVoter(voter);
        return this.voterMapper.entityToResposnse(voter);
    }

    public void delete(Long id){
        Voter voter = this.voterRepository.findById(id).orElseThrow(Message.VOTER_IS_NOT_EXIST::asBusinessException);
        this.voterRepository.deleteById(voter.getVoterId());
        log.info("method = delete by id = {}",id);
    }

}
