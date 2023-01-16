package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Zone;
import br.com.sicredi.election.core.mapper.SessionMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.SessionRepository;
import br.com.sicredi.election.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class SessionService {
    private SessionRepository sessionRepository;
    private ZoneRepository zoneRepository;
    private SessionMapper sessionMapper;

    public List<SessionResponse> findAll(){
        log.info("findAll");
        return this.sessionMapper.listEntityToListResponse(this.sessionRepository.findAll());
    }

    public List<SessionResponse> findByZone(Long idZone){
        log.info("findByZone");
        Zone zone = this.zoneRepository.findById(idZone).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        return this.sessionMapper.listEntityToListResponse(zone.getListSession());
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
        Session sessionResult = this.sessionRepository.save(session);
        return this.sessionMapper.entityToResponse(sessionResult);
    }

    @Transactional
    public SessionResponse update(@Valid SessionRequest request, Long id){
        log.info("update request = {}", request);
        Session session = this.sessionRepository.findById(id).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        this.sessionRepository.findByNumber(request.getNumber()).ifPresent(p -> {
            throw Message.SESSION_NUMBER_IS_PRESENT.asBusinessException();
        });
        session.updateNumber(request.getNumber());
        return this.sessionMapper.entityToResponse(session);
    }

    public void delete(Long id) {
        Session session = this.sessionRepository.findById(id).orElseThrow(Message.SESSION_IS_NOT_EXIST::asBusinessException);
        this.sessionRepository.deleteById(session.getSessionId());
        log.info("method = delete by id = {}",id);
    }
}
