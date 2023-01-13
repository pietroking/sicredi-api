package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.entities.Collaborator;
import br.com.sicredi.election.core.mapper.CollaboratorMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.CollaboratorRepository;
import br.com.sicredi.election.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class CollaboratorService {
    private CollaboratorRepository collaboratorRepository;
    private CollaboratorMapper collaboratorMapper;
    private SessionRepository sessionRepository;

    public List<CollaboratorResponse> findAll(){
        log.info("findAll");
        return this.collaboratorMapper.listEntityToListResponse(this.collaboratorRepository.findAll());
    }

    public List<CollaboratorResponse> findBySession(Long idSession){
        log.info("findBySession");
        return this.collaboratorMapper.listEntityToListResponse(this.collaboratorRepository.findByIdSession(idSession));
    }

    public CollaboratorResponse save(@Valid CollaboratorRequest request){
        log.info("save request = {}", request);
        Collaborator collaborator = this.collaboratorMapper.requestToEntity(request);
        this.collaboratorRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw Message.COLLABORATOR_CPF_IS_PRESENT.asBusinessException();
        });
        this.sessionRepository.findById(request.getIdSession()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        Collaborator collaboratorResult = this.collaboratorRepository.save(collaborator);
        return this.collaboratorMapper.entityToResposnse(collaboratorResult);
    }

//    @Transactional
//    public CollaboratorResponse update(@Valid CollaboratorRequest request, Long id){
//        log.info("update request = {}", request);
//        Collaborator collaborator = this.collaboratorRepository.findById(id).orElseThrow(Message.COLLABORATOR_CPF_IS_NOT_EXIST::asBusinessException);
//        this.collaboratorRepository.
//
//    }

    public void delete(Long id){
        Collaborator collaborator = this.collaboratorRepository.findById(id).orElseThrow(Message.COLLABORATOR_CPF_IS_NOT_EXIST::asBusinessException);
        this.collaboratorRepository.deleteById(collaborator.getId());
        log.info("method = delete by id = {}",id);
    }
}
