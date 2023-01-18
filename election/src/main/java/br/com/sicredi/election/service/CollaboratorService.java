package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorUpdateRequest;
import br.com.sicredi.election.core.entities.Collaborator;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.mapper.CollaboratorMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.CollaboratorRepository;
import br.com.sicredi.election.repository.SessionRepository;
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
public class CollaboratorService {
    private CollaboratorRepository collaboratorRepository;
    private CollaboratorMapper collaboratorMapper;
    private SessionRepository sessionRepository;

    public List<CollaboratorResponse> findAll(){
        log.info("findAll");
        List<CollaboratorResponse> collaboratorResponses = this.collaboratorMapper.listEntityToListResponse(this.collaboratorRepository.findAll());
        if (collaboratorResponses.isEmpty()){
            throw Message.COLLABORATOR_LIST_IS_EMPTY.asBusinessException();        }
        return collaboratorResponses;
    }

    public List<CollaboratorResponse> findBySession(Long idSession){
        log.info("findBySession={}",idSession);
        List<CollaboratorResponse> collaboratorResponses = this.collaboratorMapper.listEntityToListResponse(this.collaboratorRepository.findBySessionSessionId(idSession));
        if (collaboratorResponses.isEmpty()){
            throw Message.COLLABORATOR_LIST_IS_EMPTY.asBusinessException();
        }
        return collaboratorResponses;
    }

    public CollaboratorResponse save(@Valid CollaboratorRequest request){
        log.info("save request = {}", request);
        Session session = this.sessionRepository.findById(request.getSessionId()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        Collaborator collaborator = this.collaboratorMapper.requestToEntity(request,session);
        session.addCollaborator(collaborator);
        this.collaboratorRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw Message.COLLABORATOR_CPF_IS_PRESENT.asBusinessException();
        });
        Collaborator collaboratorResult = this.collaboratorRepository.save(collaborator);
        return this.collaboratorMapper.entityToResposnse(collaboratorResult);
    }

    @Transactional
    public CollaboratorResponse update(@Valid CollaboratorUpdateRequest request, Long id){
        log.info("update request = {}", request);
        Collaborator collaborator = this.collaboratorRepository.findById(id).orElseThrow(Message.COLLABORATOR_IS_NOT_EXIST::asBusinessException);
        Session session = this.sessionRepository.findById(request.getSessionId()).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        collaborator.update(session);
        session.addCollaborator(collaborator);
        return this.collaboratorMapper.entityToResposnse(collaborator);

    }

    public void delete(Long id){
        Collaborator collaborator = this.collaboratorRepository.findById(id).orElseThrow(Message.COLLABORATOR_IS_NOT_EXIST::asBusinessException);
        this.collaboratorRepository.deleteById(collaborator.getCollaboratorId());
        log.info("method = delete by id = {}",id);
    }
}
