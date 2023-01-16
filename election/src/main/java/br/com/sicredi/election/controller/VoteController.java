package br.com.sicredi.election.controller;

import br.com.sicredi.election.core.dto.vote.VoteRequest;
import br.com.sicredi.election.core.dto.vote.VoteResponse;
import br.com.sicredi.election.service.VoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vote")
@Tag(name = "Voto")
public class VoteController {
    private VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteResponse>> listAllVotes(){
        return ResponseEntity.ok(this.voteService.findAll());
    }

    @PostMapping
    public ResponseEntity<VoteResponse> save(@RequestBody VoteRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.voteService.save(request));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
//        this.voteService.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
