package br.com.sicredi.election.controller;

import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.service.CollaboratorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/collaborator")
@Tag(name = "Colaborador")
public class CollaboratorController {
    private CollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<List<CollaboratorResponse>> listAllSession(){
        return ResponseEntity.ok(this.collaboratorService.findAll());
    }

    @GetMapping("/{idSession}")
    public ResponseEntity<List<CollaboratorResponse>> listCollaboratorForSession(Long idSession){
        return ResponseEntity.ok(this.collaboratorService.findBySession(idSession));
    }

    @PostMapping
    public ResponseEntity<CollaboratorResponse> save (@RequestBody CollaboratorRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(collaboratorService.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.collaboratorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CollaboratorResponse> update(@RequestBody CollaboratorRequest request, @PathVariable("id") Long id){
//        return ResponseEntity.status(HttpStatus.OK).body(collaboratorService.update(request,id));
//    }
}
