package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.voter.VoterRequest;
import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.entities.Collaborator;
import br.com.sicredi.election.core.entities.Voter;
import br.com.sicredi.election.core.mapper.VoterMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.VoterRepository;
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
public class VoterService {
    private VoterRepository voterRepository;
    private VoterMapper voterMapper;
    private SessionRepository sessionRepository;

    public List<VoterResponse> findAll(){
        log.info("findAll");
        return this.voterMapper.listEntityToListResponse(this.voterRepository.findAll());
    }

    public List<VoterResponse> findBySession(Long idSession){
        log.info("findBySession");
        return this.voterMapper.listEntityToListResponse(this.voterRepository.findByIdSession(idSession));
    }

    public VoterResponse save(@Valid VoterRequest request){
        log.info("save request = {}", request);
        Voter voter = this.voterMapper.requestToEntity(request);
        this.voterRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw Message.VOTER_CPF_IS_PRESENT.asBusinessException();
        });
        this.sessionRepository.findById(request.getIdSession()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        Voter voterResult = this.voterRepository.save(voter);
        return this.voterMapper.entityToResposnse(voterResult);
    }

    public void delete(Long id){
        Voter voter = this.voterRepository.findById(id).orElseThrow(Message.VOTER_CPF_IS_NOT_EXIST::asBusinessException);
        this.voterRepository.deleteById(voter.getId());
        log.info("method = delete by id = {}",id);
    }


}
