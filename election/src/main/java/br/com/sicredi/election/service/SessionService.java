package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.dto.session.SessionUpdateRequest;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Zone;
import br.com.sicredi.election.core.mapper.SessionMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.CollaboratorRepository;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class SessionService {
    private SessionRepository sessionRepository;
    private ZoneRepository zoneRepository;
    private SessionMapper sessionMapper;
    private CollaboratorRepository collaboratorRepository;

    public List<SessionResponse> findAll(){
        log.info("findAll");
        List<SessionResponse> sessionResponseList = this.sessionMapper.listEntityToListResponse(this.sessionRepository.findAll());
        if (sessionResponseList.isEmpty()){
            throw Message.SESSION_LIST_IS_EMPTY.asBusinessException();
        }
        return sessionResponseList;
    }

    public List<SessionResponse> findByZone(Long idZone){
        log.info("findByZone");
        Zone zone = this.zoneRepository.findById(idZone).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        List<SessionResponse> sessionResponseList = this.sessionMapper.listEntityToListResponse(zone.getListSession());
        if (sessionResponseList.isEmpty()){
            throw Message.SESSION_ZONE_LIST_IS_EMPTY.asBusinessException();
        }
        return sessionResponseList;
    }

    public SessionResponse save(@Valid SessionRequest request){
        log.info("save request = {}", request);
        Zone zone = this.zoneRepository.findById(request.getIdZone()).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        Session session = this.sessionMapper.requstToEntity(request,zone);
        zone.addSession(session);
        log.info(session.toString());
        sessionRepository.findByNumber(request.getNumber()).ifPresent(p -> {
            throw Message.SESSION_NUMBER_IS_PRESENT.asBusinessException();
        });
        sessionRepository.findByUrnNumber(request.getUrnNumber()).ifPresent(p -> {
            throw Message.SESSION_URN_NUMBER_IS_PRESENT.asBusinessException();
        });
        Session sessionResult = this.sessionRepository.save(session);
        return this.sessionMapper.entityToResponse(sessionResult);
    }

    @Transactional
    public SessionResponse update(@Valid SessionUpdateRequest request, Long id){
        log.info("update request = {}", request);
        Session session = this.sessionRepository.findById(id).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        this.sessionRepository.findByUrnNumber(request.getUrnNumber()).ifPresent(p -> {
            throw Message.SESSION_URN_NUMBER_IS_PRESENT.asBusinessException();
        });
        session.updateUrnNumber(request.getUrnNumber());
        return this.sessionMapper.entityToResponse(session);
    }

//    @Transactional
    public void delete(Long id) {
        Session session = this.sessionRepository.findById(id).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
//        session.getListCollaborator().forEach(session::deleteCollaborator);
        this.sessionRepository.deleteById(session.getSessionId());
        log.info("method = delete by id = {}",id);
    }
}
