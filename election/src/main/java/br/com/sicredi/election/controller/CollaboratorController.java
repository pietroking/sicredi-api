package br.com.sicredi.election.controller;

import br.com.sicredi.election.annotation.CollaboratorDeleteStandard;
import br.com.sicredi.election.annotation.CollaboratorSaveStandard;
import br.com.sicredi.election.annotation.CollaboratorUpdateStandard;
import br.com.sicredi.election.annotation.ListAllCollaboratorCodeStandard;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorRequest;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorResponse;
import br.com.sicredi.election.core.dto.collaborator.CollaboratorUpdateRequest;
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
    @ListAllCollaboratorCodeStandard
    public ResponseEntity<List<CollaboratorResponse>> listAllSession(){
        return this.collaboratorService.findAll();
    }

    @GetMapping("find-by-idsession")
    @ListAllCollaboratorCodeStandard
    public ResponseEntity<List<CollaboratorResponse>> listCollaboratorForSession(Long idSession){
        return this.collaboratorService.findBySession(idSession);
    }

    @PostMapping
    @CollaboratorSaveStandard
    public ResponseEntity<CollaboratorResponse> save(@RequestBody CollaboratorRequest request){
        return this.collaboratorService.save(request);
    }

    @DeleteMapping("/{id}")
    @CollaboratorDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.collaboratorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @CollaboratorUpdateStandard
    public ResponseEntity<CollaboratorResponse> update(@RequestBody CollaboratorUpdateRequest request, @PathVariable("id") Long id){
        return collaboratorService.update(request,id);
    }
}
