package br.com.sicredi.election.controller;

import br.com.sicredi.election.annotation.ListAllVoterCodeStandard;
import br.com.sicredi.election.annotation.VoterDeleteStandard;
import br.com.sicredi.election.annotation.VoterSaveStandard;
import br.com.sicredi.election.annotation.VoterUpdateStandard;
import br.com.sicredi.election.core.dto.voter.VoterRequest;
import br.com.sicredi.election.core.dto.voter.VoterResponse;
import br.com.sicredi.election.core.dto.voter.VoterUpdateRequest;
import br.com.sicredi.election.service.VoterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/voter")
@Tag(name = "Eleitor")
public class VoterController {
    private VoterService voterService;

    @GetMapping
    @ListAllVoterCodeStandard
    public ResponseEntity<List<VoterResponse>> listAllSession(){
        return this.voterService.findAll();
    }

//    @GetMapping("/{idSession}")
//    @ListAllVoterCodeStandard
//    public ResponseEntity<List<VoterResponse>> listCollaboratorForSession(Long idSession){
//        return ResponseEntity.ok(this.voterService.findBySession(idSession));
//    }

    @PostMapping
    @VoterSaveStandard
    public ResponseEntity<VoterResponse> save(@RequestBody VoterRequest request){
        return voterService.save(request);
    }

    @PatchMapping("/{id}")
    @VoterUpdateStandard
    public ResponseEntity<VoterResponse> update(@RequestBody VoterUpdateRequest request, @PathVariable("id") Long id){
        return voterService.update(request,id);
    }

    @DeleteMapping("/{id}")
    @VoterDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.voterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
