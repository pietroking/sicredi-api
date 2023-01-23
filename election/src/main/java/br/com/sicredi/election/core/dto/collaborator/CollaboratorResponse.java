package br.com.sicredi.election.core.dto.collaborator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CollaboratorResponse {
    private Long collaboratorId;
    private String name;
    private Long sessionId;
    private String cpf;
}
