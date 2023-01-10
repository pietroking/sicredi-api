package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.entities.Session;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    List<SessionResponse> listEntityToLinsResponse(List<Session> session);
    Session requstToEntity(SessionRequest request);
    SessionResponse entityToResponse(Session session);
}
