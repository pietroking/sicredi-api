package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.entities.Session;
import br.com.sicredi.election.core.entities.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    List<SessionResponse> listEntityToListResponse(List<Session> session);


    @Mapping(target = "number",source = "request.number")
    @Mapping(target = "zone.zoneId",source = "zone.zoneId")
    Session requstToEntity(SessionRequest request, Zone zone);

    @Mapping(target = "zoneId", source = "session.zone.zoneId")
    SessionResponse entityToResponse(Session session);

}
