package br.com.sicredi.election.core.dto.collaborator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CollaboratorResponse {
    private Long id;
    private String nome;
    private Long idSession;
    private Long cpf;
}
