package br.com.sicredi.election.core.dto.collaborator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CollaboratorRequest {
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;
    @NotNull(message = "O idSession não pode ser nulo")
    private Long idSession;
    @NotNull(message = "O cpf não pode ser nulo")
    private Long cpf;
}
