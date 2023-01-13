package br.com.sicredi.election.controller;

import br.com.sicredi.election.annotation.*;
import br.com.sicredi.election.core.dto.session.SessionRequest;
import br.com.sicredi.election.core.dto.session.SessionResponse;
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
        return ResponseEntity.ok(this.sessionService.findAll());
    }

    @GetMapping("/{idZone}")
    @ListAllSessionCodeStandard
    public ResponseEntity<List<SessionResponse>> listSessionForZone(Long idZone){
        return ResponseEntity.ok(this.sessionService.findByZone(idZone));
    }

    @PostMapping
    @SessionSaveStandard
    public ResponseEntity<SessionResponse> save(@RequestBody SessionRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.save(request));
    }

    @DeleteMapping("/{id}")
    @SessionDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.sessionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @SessionUpdateStandard
    public ResponseEntity<SessionResponse> update(@RequestBody SessionRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.update(request, id));
    }
}
