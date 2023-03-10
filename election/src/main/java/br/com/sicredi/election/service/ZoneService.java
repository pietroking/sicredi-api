package br.com.sicredi.election.service;

import br.com.sicredi.election.core.dto.zone.ZoneRequest;
import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.core.entities.Zone;
import br.com.sicredi.election.core.mapper.ZoneMapper;
import br.com.sicredi.election.enums.Message;
import br.com.sicredi.election.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<ZoneResponse>> findAll(){
        log.info("findAll");
        List<ZoneResponse> zoneResponseList = this.zoneMapper.listEntityToListResponse(this.zoneRepository.findAll());
        if (zoneResponseList.isEmpty()){
            throw Message.ZONE_LIST_IS_EMPTY.asBusinessException();
        }
        return ResponseEntity.ok(zoneResponseList);
    }

    public ResponseEntity<ZoneResponse> save(@Valid ZoneRequest request){
        log.info("save request = {}", request);
        Zone zone = this.zoneMapper.requestToEntity(request);
        this.zoneRepository.findByNumber(request.getNumber()).ifPresent(p -> {
            throw Message.ZONE_NUMBER_IS_PRESENT.asBusinessException();
        });
        Zone zoneResult = this.zoneRepository.save(zone);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.zoneMapper.entityToResponse(zoneResult));
    }

    @Transactional
    public ResponseEntity<ZoneResponse> update(@Valid ZoneRequest request, Long id){
        log.info(" update request = {}", request);
        Zone zone = this.zoneRepository.findById(id).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        this.zoneRepository.findByNumber(request.getNumber()).ifPresent(p -> {
            throw Message.ZONE_NUMBER_IS_PRESENT.asBusinessException();
        });
        zone.updateNumber(request.getNumber());
        return ResponseEntity.status(HttpStatus.OK).body(this.zoneMapper.entityToResponse(zone));
    }

    public void delete(Long id) {
        Zone zone = this.zoneRepository.findById(id).orElseThrow(Message.ZONE_IS_NOT_EXIST::asBusinessException);
        this.zoneRepository.deleteById(zone.getZoneId());
        log.info("method = delete by id = {}",id);
    }
}
