package br.com.sicredi.election.controller;

import br.com.sicredi.election.annotation.ListAllSessionCodeStandard;
import br.com.sicredi.election.annotation.SessionDeleteStandard;
import br.com.sicredi.election.annotation.SessionSaveStandard;
import br.com.sicredi.election.annotation.SessionUpdateStandard;
import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
import br.com.sicredi.election.core.dto.session.SessionUpdateRequest;
import br.com.sicredi.election.service.SessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/session")
@Tag(name = "Seções")
public class SessionController {

    private SessionService sessionService;

    @GetMapping
    @ListAllSessionCodeStandard
    public ResponseEntity<List<SessionResponse>> listAllSession(){
        return this.sessionService.findAll();
    }

    @GetMapping("/find-by-idzone")
    @ListAllSessionCodeStandard
    public ResponseEntity<List<SessionResponse>> listSessionForZone(Long idZone){
        return this.sessionService.findByZone(idZone);
    }

    @PostMapping
    @SessionSaveStandard
    public ResponseEntity<SessionResponse> save(@RequestBody SessionRequest request){
        return sessionService.save(request);
    }

    @DeleteMapping("/{id}")
    @SessionDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.sessionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @SessionUpdateStandard
    public ResponseEntity<SessionResponse> update(@RequestBody SessionUpdateRequest request, @PathVariable("id") Long id){
        return sessionService.update(request, id);
    }
}
