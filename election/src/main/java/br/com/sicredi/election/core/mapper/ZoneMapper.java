package br.com.sicredi.election.core.mapper;

import br.com.sicredi.election.core.dto.zone.ZoneRequest;
import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.core.entities.Zone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    List<ZoneResponse> listEntityToListResponse(List<Zone> zone);

    Zone requestToEntity(ZoneRequest request);

    ZoneResponse entityToResponse(Zone zone);
}
