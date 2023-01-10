package br.com.sicredi.election.controller;

import br.com.sicredi.election.annotation.ListAllZoneCodeStandard;
import br.com.sicredi.election.annotation.ZoneDeleteStandard;
import br.com.sicredi.election.annotation.ZoneSaveStandard;
import br.com.sicredi.election.annotation.ZoneUpdateStandard;
import br.com.sicredi.election.core.dto.zone.ZoneRequest;
import br.com.sicredi.election.core.dto.zone.ZoneResponse;
import br.com.sicredi.election.service.ZoneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/zone")
@Tag(name = "Zonas")
public class ZoneController {
    private ZoneService zoneService;
    @GetMapping
    @ListAllZoneCodeStandard
    public ResponseEntity<List<ZoneResponse>> listAllZones(){
        return ResponseEntity.ok(this.zoneService.findAll());
    }

    @PostMapping
    @ZoneSaveStandard
    public ResponseEntity<ZoneResponse> save(@RequestBody ZoneRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(zoneService.save(request));
    }

    @DeleteMapping("/{number}")
    @ZoneDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("number") Long number){
        this.zoneService.delete(number);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @ZoneUpdateStandard
    public ResponseEntity<ZoneResponse> update(@RequestBody ZoneRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(zoneService.update(request, id));
    }

}