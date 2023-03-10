package br.com.sicredi.election.controller;

import br.com.sicredi.election.annotation.*;
import br.com.sicredi.election.core.dto.candidate.CandidateRequest;
import br.com.sicredi.election.core.dto.candidate.CandidateResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateResultResponse;
import br.com.sicredi.election.core.dto.candidate.CandidateUpdateRequest;
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
    @ListAllCandidateCodeStandard
    public ResponseEntity<List<CandidateResponse>> listAllCandidates(){
        return this.candidateService.findAll();
    }

    @GetMapping("/find-by-name")
    @CandidateFindByNameCodeStandard
    public ResponseEntity<List<CandidateResponse>> listCandidateForName(String name){
        return this.candidateService.findByName(name);
    }

    @GetMapping("/votes")
    @CountVotesCodeStandard
    public ResponseEntity<List<CandidateResultResponse>> countVotes(){
        return this.candidateService.countVotes();
    }

    @PostMapping
    @CandidateSaveStandard
    public ResponseEntity<CandidateResponse> save(@RequestBody CandidateRequest request){
        return candidateService.save(request);
    }

    @PatchMapping("/{id}")
    @CandidateUpdateCodeStardand
    public ResponseEntity<CandidateResponse> update(@RequestBody CandidateUpdateRequest request, @PathVariable("id") Long id){
        return candidateService.update(request,id);
    }

    @DeleteMapping("/{id}")
    @CandidateDeleteCodeStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.candidateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
