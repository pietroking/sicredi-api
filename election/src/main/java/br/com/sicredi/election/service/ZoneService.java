package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.zone.ZoneRequest;
import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.core.entities.Zone;
import br.com.sicredi.election.core.mapper.ZoneMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.ZoneRepository;
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
public class ZoneService {
    private ZoneRepository zoneRepository;
    private ZoneMapper zoneMapper;

    public List<ZoneResponse> findAll(){
        log.info("findAll");
        return this.zoneMapper.listEntityToListResponse(this.zoneRepository.findAll());
    }

    public ZoneResponse save(@Valid ZoneRequest request){
        log.info("save request = {}", request);
        Zone zone = this.zoneMapper.requestToEntity(request);
        this.zoneRepository.findByNumber(request.getNumber()).ifPresent(p -> {
            throw Message.ZONE_NUMBER_IS_PRESENT.asBusinessException();
        });
        Zone zoneResult = this.zoneRepository.save(zone);
        return this.zoneMapper.entityToResponse(zoneResult);
    }

    @Transactional
    public ZoneResponse update(@Valid ZoneRequest request, Long id){
        log.info(" update request = {}", request);
        Zone zone = this.zoneRepository.findById(id).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        this.zoneRepository.findByNumber(request.getNumber()).ifPresent(p -> {
            throw Message.ZONE_NUMBER_IS_PRESENT.asBusinessException();
        });
        zone.updateNumber(request.getNumber());
        return this.zoneMapper.entityToResponse(zone);
    }

    public void delete(Long id) {
        Zone zone = this.zoneRepository.findById(id).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        this.zoneRepository.deleteById(zone.getId());
        log.info("method = delete by id = {}",id);
    }
}
