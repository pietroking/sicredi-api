package br.com.sicredi.election.controller;

import br.com.sicredi.election.core.dto.candidate.CandidateRequest;
import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateResultResponse;
import br.com.sicredi.election.service.CandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidate")
@Tag(name = "Candidato")
public class CandidateController {
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> listAllCandidates(){
        return ResponseEntity.ok(this.candidateService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<CandidateResponse> listCandidateForName(String name){
        return ResponseEntity.ok(this.candidateService.findByName(name));
    }

    @GetMapping("/votes")
    public ResponseEntity<List<CandidateResultResponse>> countVotos(){
        return ResponseEntity.ok(this.candidateService.countVotes());
    }

    @PostMapping
    public ResponseEntity<CandidateResponse> save (@RequestBody CandidateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.candidateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
